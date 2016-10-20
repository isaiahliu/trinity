package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.trinity.common.exception.IException;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.ServiceCategoryDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceInfoDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceInfoSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceInfoRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceOrderRepository;
import org.trinity.yqyl.repository.business.entity.ServiceCategory;
import org.trinity.yqyl.repository.business.entity.ServiceInfo;
import org.trinity.yqyl.repository.business.entity.ServiceInfo_;
import org.trinity.yqyl.repository.business.entity.ServiceOrder;
import org.trinity.yqyl.repository.business.entity.ServiceOrder_;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient_;
import org.trinity.yqyl.repository.business.entity.User_;

@Service
public class ServiceInfoProcessController
        extends AbstractAutowiredCrudProcessController<ServiceInfo, ServiceInfoDto, ServiceInfoSearchingDto, IServiceInfoRepository>
        implements IServiceProcessController {

    @Autowired
    private IObjectConverter<ServiceCategory, ServiceCategoryDto> serviceCategoryConverter;

    @Autowired
    private IServiceOrderRepository serviceOrderRepository;

    public ServiceInfoProcessController() {
        super(ServiceInfo.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_INFO);
    }

    @Override
    public Page<ServiceInfoDto> getAll(final ServiceInfoSearchingDto dto) throws IException {
        final Pageable pagable = getPagingConverter().convert(dto);

        final Specification<ServiceInfo> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (dto.getServiceSupplierClientId() != null) {
                predicates.add(cb.equal(root.join(ServiceInfo_.serviceSupplierClient).get(ServiceSupplierClient_.userId),
                        dto.getServiceSupplierClientId()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        final Page<ServiceInfo> serviceInfos = getDomainEntityRepository().findAll(specification, pagable);

        return serviceInfos.map(item -> {
            final ServiceInfoDto serviceInfoDto = getDomainObjectConverter().convert(item);

            serviceInfoDto.setServiceCategory(serviceCategoryConverter.convert(item.getServiceCategory()));
            return serviceInfoDto;
        });
    }

    @Override
    @Transactional
    public List<ServiceInfoDto> getMe(final ServiceInfoSearchingDto dto) throws IException {
        final String username = getSecurityUtil().getCurrentToken().getUsername();
        final Specification<ServiceInfo> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.join(ServiceInfo_.serviceSupplierClient).join(ServiceSupplierClient_.user).get(User_.username),
                    username));

            if (dto.getName() != null) {
                predicates.add(cb.like(root.get(ServiceInfo_.name), "%" + dto.getName() + "%"));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        final Date fromDate = calendar.getTime();

        return getDomainEntityRepository().findAll(specification).stream().map(item -> {
            final ServiceInfoDto result = getDomainObjectConverter().convert(item);

            final Specification<ServiceOrder> appraiseSpecification = (root, query, cb) -> {
                final List<Predicate> predicates = new ArrayList<>();

                predicates.add(cb.equal(root.join(ServiceOrder_.serviceInfo).get(ServiceInfo_.id), item.getId()));

                predicates.add(cb.greaterThanOrEqualTo(root.get(ServiceOrder_.proposalTime), fromDate));

                return cb.and(predicates.toArray(new Predicate[0]));
            };

            final List<ServiceOrder> orders = serviceOrderRepository.findAll(appraiseSpecification);

            result.setMonthlyProposalOrderCount(orders.size());

            result.setMonthlyRate(orders.stream().map(order -> (order.getAppraise() == null) ? null : order.getAppraise().getAttitudeRate())
                    .filter(rate -> rate != null).collect(Collectors.averagingDouble(rate -> rate)));

            return result;
        }).collect(Collectors.toList());
    }
}
