package org.trinity.yqyl.process.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.trinity.common.exception.IException;
import org.trinity.message.LookupParser;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.ServiceCategoryDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceInfoDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderAppraiseDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderSearchingDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.OrderStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceOrderProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceInfoRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceOrderRepository;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.entity.ServiceCategory;
import org.trinity.yqyl.repository.business.entity.ServiceInfo;
import org.trinity.yqyl.repository.business.entity.ServiceInfo_;
import org.trinity.yqyl.repository.business.entity.ServiceOrder;
import org.trinity.yqyl.repository.business.entity.ServiceOrderAppraise;
import org.trinity.yqyl.repository.business.entity.ServiceOrder_;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient_;
import org.trinity.yqyl.repository.business.entity.User;
import org.trinity.yqyl.repository.business.entity.User_;

@Service
public class ServiceOrderProcessController
        extends AbstractAutowiredCrudProcessController<ServiceOrder, ServiceOrderDto, ServiceOrderSearchingDto, IServiceOrderRepository>
        implements IServiceOrderProcessController {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IServiceInfoRepository serviceInfoRepository;

    @Autowired
    private IObjectConverter<ServiceInfo, ServiceInfoDto> serviceInfoConverter;

    @Autowired
    private IObjectConverter<ServiceOrderAppraise, ServiceOrderAppraiseDto> serviceOrderAppraiseConverter;

    @Autowired
    private IObjectConverter<ServiceCategory, ServiceCategoryDto> serviceCategoryConverter;

    @Autowired
    private IObjectConverter<ServiceSupplierClient, ServiceSupplierClientDto> serviceSupplierClientConverter;

    public ServiceOrderProcessController() {
        super(ServiceOrder.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_ORDER);
    }

    @Override
    public Page<ServiceOrderDto> getAll(final ServiceOrderSearchingDto dto) throws IException {
        final Pageable pagable = getPagingConverter().convert(dto);

        final Specification<ServiceOrder> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(dto.getReceiverUserName())) {
                predicates.add(cb.like(root.join(ServiceOrder_.user).get(User_.username), "%" + dto.getReceiverUserName() + "%"));
            }

            if (!dto.getStatus().isEmpty()) {
                final In<OrderStatus> in = cb.in(root.get(ServiceOrder_.status));
                dto.getStatus().forEach(item -> in.value(LookupParser.parse(OrderStatus.class, item)));
                predicates.add(in);
            }

            if (dto.getServiceSupplierClientId() != null) {
                predicates.add(cb.equal(
                        root.join(ServiceOrder_.serviceInfo).join(ServiceInfo_.serviceSupplierClient).get(ServiceSupplierClient_.userId),
                        dto.getServiceSupplierClientId()));

                query.distinct(true);
            }

            if (dto.getServiceOrderId() != null) {
                predicates.add(cb.equal(root.get(ServiceOrder_.id), dto.getServiceOrderId()));
            }

            if (!StringUtils.isEmpty(dto.getServiceDate())) {
                final DateFormat format = new SimpleDateFormat("yyyyMMdd");
                try {
                    final Calendar calendar = Calendar.getInstance();
                    calendar.setTime(format.parse(dto.getServiceDate()));
                    predicates.add(cb.greaterThanOrEqualTo(root.get(ServiceOrder_.serviceTime), calendar.getTime()));

                    calendar.add(Calendar.DATE, 1);
                    predicates.add(cb.lessThan(root.get(ServiceOrder_.serviceTime), calendar.getTime()));
                } catch (final ParseException e) {
                }
            }

            if (!StringUtils.isEmpty(dto.getSettledDate())) {
                final DateFormat format = new SimpleDateFormat("yyyyMMdd");
                try {
                    final Calendar calendar = Calendar.getInstance();
                    calendar.setTime(format.parse(dto.getSettledDate()));
                    predicates.add(cb.greaterThanOrEqualTo(root.get(ServiceOrder_.settledTime), calendar.getTime()));

                    calendar.add(Calendar.DATE, 1);
                    predicates.add(cb.lessThan(root.get(ServiceOrder_.settledTime), calendar.getTime()));
                } catch (final ParseException e) {
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        final Page<ServiceOrder> findAll = getDomainEntityRepository().findAll(specification, pagable);

        return findAll.map(item -> {
            final ServiceOrderDto serviceOrderDto = getDomainObjectConverter().convert(item);
            getRelationship(item, dto, serviceOrderDto);
            return serviceOrderDto;
        });
    }

    @Override
    @Transactional
    public ServiceOrderDto proposeOrder(final ServiceOrderDto serviceOrderDto) throws IException {
        final User user = userRepository.findOneByUsername(getSecurityUtil().getCurrentToken().getUsername());

        final ServiceOrder serviceOrder = getDomainObjectConverter().convertBack(serviceOrderDto);
        serviceOrder.setId(null);
        serviceOrder.setPrice(0d);
        serviceOrder.setProposalTime(new Date());
        serviceOrder.setStatus(OrderStatus.UNPROCESSED);
        serviceOrder.setUser(user);

        final ServiceInfo serviceInfo = serviceInfoRepository.findOne(serviceOrderDto.getServiceInfo().getId());

        serviceOrder.setPrice(serviceInfo.getPrice());
        serviceOrder.setServiceInfo(serviceInfo);

        getDomainEntityRepository().save(serviceOrder);

        return getDomainObjectConverter().convert(serviceOrder);
    }

    @Override
    protected void getRelationship(final ServiceOrder entity, final ServiceOrderSearchingDto searchingDto,
            final ServiceOrderDto serviceOrderDto) {
        final ServiceInfo serviceInfo = entity.getServiceInfo();
        final ServiceInfoDto serviceInfoDto = serviceInfoConverter.convert(serviceInfo);

        final ServiceCategory serviceCategory = serviceInfo.getServiceCategory();
        final ServiceCategoryDto serviceCategoryDto = serviceCategoryConverter.convert(serviceCategory);

        final ServiceSupplierClient serviceSupplierClient = serviceInfo.getServiceSupplierClient();
        final ServiceSupplierClientDto serviceSupplierDto = serviceSupplierClientConverter.convert(serviceSupplierClient);

        ServiceOrderAppraiseDto serviceOrderAppraiseDto = null;
        final ServiceOrderAppraise serviceOrderAppraise = entity.getAppraise();
        if (serviceOrderAppraise == null) {
            serviceOrderAppraiseDto = new ServiceOrderAppraiseDto();
            serviceOrderAppraiseDto.setId(entity.getId());
        } else {
            serviceOrderAppraiseDto = serviceOrderAppraiseConverter.convert(serviceOrderAppraise);
        }

        serviceInfoDto.setServiceCategory(serviceCategoryDto);
        serviceInfoDto.setServiceSupplierClient(serviceSupplierDto);
        serviceOrderDto.setServiceInfo(serviceInfoDto);
        serviceOrderDto.setUsername(entity.getUser().getUsername());
        serviceOrderDto.setAppraise(serviceOrderAppraiseDto);
    }
}
