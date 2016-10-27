package org.trinity.yqyl.process.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.trinity.common.dto.object.ISearchingDto;
import org.trinity.common.exception.IException;
import org.trinity.message.LookupParser;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.OrderStatus;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.common.message.lookup.ServiceOrderRequirementStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceOrderProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IContentRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceInfoRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceOrderRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceOrderRequirementRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceSupplierStaffRepository;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.entity.Content;
import org.trinity.yqyl.repository.business.entity.ServiceCategory_;
import org.trinity.yqyl.repository.business.entity.ServiceInfo;
import org.trinity.yqyl.repository.business.entity.ServiceInfo_;
import org.trinity.yqyl.repository.business.entity.ServiceOrder;
import org.trinity.yqyl.repository.business.entity.ServiceOrderRequirement;
import org.trinity.yqyl.repository.business.entity.ServiceOrder_;
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
    private IContentRepository contentRepository;

    @Autowired
    private IServiceOrderRequirementRepository serviceOrderRequirementRepository;

    @Autowired
    private IServiceSupplierStaffRepository serviceSupplierStaffRepository;

    public ServiceOrderProcessController() {
        super(ServiceOrder.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_ORDER);
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
    public Page<ServiceOrder> queryAll(final ServiceOrderSearchingDto dto) throws IException {
        final Pageable pagable = getPagingConverter().convert(dto);

        final String username = getSecurityUtil().getCurrentToken().getUsername();

        final Specification<ServiceOrder> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            switch (dto.getSearchScope()) {
            case ISearchingDto.SEARCH_ALL:
                break;
            case "supplier":
                predicates.add(cb.equal(root.join(ServiceOrder_.serviceInfo).join(ServiceInfo_.serviceSupplierClient)
                        .join(ServiceSupplierClient_.user).get(User_.username), username));
                break;
            case "me":
            default:
                predicates.add(cb.equal(root.join(ServiceOrder_.user).get(User_.username), username));
                break;
            }

            if (!StringUtils.isEmpty(dto.getReceiverUserName())) {
                predicates.add(cb.like(root.join(ServiceOrder_.user).get(User_.username), "%" + dto.getReceiverUserName() + "%"));
            }

            if (dto.getId() != null && dto.getId() > 0) {
                predicates.add(cb.equal(root.get(ServiceOrder_.id), dto.getId()));
            }

            if (!StringUtils.isEmpty(dto.getCategory())) {
                predicates.add(cb.equal(root.join(ServiceOrder_.serviceInfo).join(ServiceInfo_.serviceCategory).get(ServiceCategory_.name),
                        dto.getCategory()));
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

            if (!StringUtils.isEmpty(dto.getSupplierUserName())) {
                predicates.add(cb.like(root.join(ServiceOrder_.serviceInfo).join(ServiceInfo_.serviceSupplierClient)
                        .join(ServiceSupplierClient_.user).get(User_.username), "%" + dto.getSupplierUserName() + "%"));
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

        return getDomainEntityRepository().findAll(specification, pagable);
    }

    @Override
    @Transactional
    public void releaseOrder(final List<ServiceOrderDto> data) throws IException {
        for (final ServiceOrderDto serviceOrderDto : data) {
            final ServiceOrder serviceOrder = getDomainEntityRepository().findOne(serviceOrderDto.getId());

            if (serviceOrder.getStatus() != OrderStatus.REQUEST_GRABBED) {
                throw getExceptionFactory().createException(ErrorMessage.INCORRECT_SERVICE_ORDER_STATUS);
            }
            final ServiceOrderRequirement serviceOrderRequirement = serviceOrder.getServiceOrderRequirement();

            serviceOrder.setStatus(OrderStatus.REQUEST_FAILED);
            if (serviceOrderRequirement != null) {
                serviceOrderRequirement.setStatus(ServiceOrderRequirementStatus.ACTIVE);

                serviceOrderRequirementRepository.save(serviceOrderRequirement);
            }

            serviceOrder.setServiceOrderRequirement(null);

            getDomainEntityRepository().save(serviceOrder);
        }
    }

    @Override
    @Transactional
    public String uploadReceipt(final ServiceOrderDto serviceOrderDto) throws IException {
        final ServiceOrder order = getDomainEntityRepository().findOne(serviceOrderDto.getId());
        if (order == null) {
            throw getExceptionFactory().createException(ErrorMessage.UNABLE_TO_FIND_SERVICE_ORDER);
        }

        Content content;
        if (StringUtils.isEmpty(order.getReceipt())) {
            content = new Content();
            content.setUuid(UUID.randomUUID().toString());
            content.setStatus(RecordStatus.ACTIVE);
            order.setReceipt(content.getUuid());
        } else {
            content = contentRepository.findOneByUuid(order.getReceipt());
        }

        content.setContent(serviceOrderDto.getReceiptContent());

        contentRepository.save(content);
        getDomainEntityRepository().save(order);

        return order.getReceipt();
    }

    @Override
    protected void updateRelationship(final ServiceOrder entity, final ServiceOrderDto dto) {
        if (dto.getStaff() != null && dto.getStaff().getId() > 0) {
            entity.setServiceSupplierStaff(serviceSupplierStaffRepository.findOne(dto.getStaff().getId()));
        }
        super.updateRelationship(entity, dto);
    }
}
