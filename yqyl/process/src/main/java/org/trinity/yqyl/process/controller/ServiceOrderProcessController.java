package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Predicate;

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
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.OrderStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IOrderProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceOrderRepository;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.entity.ServiceCategory;
import org.trinity.yqyl.repository.business.entity.ServiceInfo;
import org.trinity.yqyl.repository.business.entity.ServiceOrder;
import org.trinity.yqyl.repository.business.entity.ServiceOrder_;
import org.trinity.yqyl.repository.business.entity.User;

@Service
public class ServiceOrderProcessController
        extends AbstractAutowiredCrudProcessController<ServiceOrder, ServiceOrderDto, ServiceOrderSearchingDto, IServiceOrderRepository>
        implements IOrderProcessController {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IObjectConverter<ServiceInfo, ServiceInfoDto> serviceInfoConverter;

    @Autowired
    private IObjectConverter<ServiceCategory, ServiceCategoryDto> serviceCategoryConverter;

    public ServiceOrderProcessController() {
        super(ServiceOrder.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_ORDER);
    }

    @Override
    public Page<ServiceOrderDto> getAll(final ServiceOrderSearchingDto dto) throws IException {
        final Pageable pagable = pagingConverter.convert(dto);

        final Specification<ServiceOrder> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(dto.getReceiverUserName())) {
                final User user = userRepository.findOneByUsername(dto.getReceiverUserName());
                predicates.add(cb.equal(root.get(ServiceOrder_.user), user));
            }

            if (!dto.getStatus().isEmpty()) {
                final In<OrderStatus> in = cb.in(root.get(ServiceOrder_.status));
                dto.getStatus().forEach(item -> in.value(LookupParser.parse(OrderStatus.class, item)));
                predicates.add(in);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        final Page<ServiceOrder> findAll = getDomainEntityRepository().findAll(specification, pagable);

        return findAll.map(item -> {
            final ServiceOrderDto serviceOrderDto = getDomainObjectConverter().convert(item);

            final ServiceInfo service = item.getService();
            final ServiceInfoDto serviceInfoDto = serviceInfoConverter.convert(service);

            final ServiceCategory serviceCategory = service.getServiceCategory();
            final ServiceCategoryDto serviceCategoryDto = serviceCategoryConverter.convert(serviceCategory);

            serviceInfoDto.setCategory(serviceCategoryDto);
            serviceOrderDto.setService(serviceInfoDto);

            return serviceOrderDto;
        });
    }
}
