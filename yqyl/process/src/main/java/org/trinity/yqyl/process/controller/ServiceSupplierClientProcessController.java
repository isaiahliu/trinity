package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.trinity.common.exception.IException;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.ServiceCategoryDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceSupplierClientProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceCategoryRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceSupplierClientRepository;
import org.trinity.yqyl.repository.business.entity.ServiceCategory;
import org.trinity.yqyl.repository.business.entity.ServiceCategory_;
import org.trinity.yqyl.repository.business.entity.ServiceInfo;
import org.trinity.yqyl.repository.business.entity.ServiceInfo_;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient_;
import org.trinity.yqyl.repository.business.entity.User_;

@Service
public class ServiceSupplierClientProcessController extends
        AbstractAutowiredCrudProcessController<ServiceSupplierClient, ServiceSupplierClientDto, ServiceSupplierClientSearchingDto, IServiceSupplierClientRepository>
        implements IServiceSupplierClientProcessController {
    @Autowired
    private IServiceCategoryRepository serviceCategoryRepository;

    @Autowired
    private IObjectConverter<ServiceCategory, ServiceCategoryDto> serviceCategoryConverter;

    public ServiceSupplierClientProcessController() {
        super(ServiceSupplierClient.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_SUPPLIER_CLIENT);
    }

    @Override
    public Page<ServiceSupplierClientDto> getAll(final ServiceSupplierClientSearchingDto searchingData) throws IException {
        final List<Long> categoryIds = new ArrayList<>();
        final Pageable pagable = getPagingConverter().convert(searchingData);

        if (searchingData.getCategoryChildren().isEmpty()) {
            if (searchingData.getCategoryParent() != null) {
                final ServiceCategory category = serviceCategoryRepository.findOne(searchingData.getCategoryParent());
                if (category != null) {
                    categoryIds.addAll(serviceCategoryRepository.findAllByParent(category).stream().map(item -> item.getId())
                            .collect(Collectors.toList()));
                }
            }
        } else {
            serviceCategoryRepository.findAll(searchingData.getCategoryChildren()).forEach(item -> categoryIds.add(item.getId()));
        }
        final String username = getSecurityUtil().getCurrentToken().getUsername();

        final Specification<ServiceSupplierClient> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (!categoryIds.isEmpty()) {
                predicates.add(root.join(ServiceSupplierClient_.serviceInfos).join(ServiceInfo_.serviceCategory).get(ServiceCategory_.id)
                        .in(categoryIds));
                query.distinct(true);
            }

            if (!searchingData.isSearchAll()) {
                predicates.add(cb.equal(root.join(ServiceSupplierClient_.user).get(User_.username), username));
            }

            if (!StringUtils.isEmpty(searchingData.getName())) {
                predicates.add(cb.like(root.get(ServiceSupplierClient_.name), "%" + searchingData.getName() + "%"));
            }

            if (searchingData.getId() != null && searchingData.getId() != 0) {
                predicates.add(cb.equal(root.get(ServiceSupplierClient_.userId), searchingData.getId()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return getDomainEntityRepository().findAll(specification, pagable).map(item -> {
            final ServiceSupplierClientDto dto = getDomainObjectConverter().convert(item);

            final List<ServiceInfo> serviceInfos = item.getServiceInfos();
            final Map<Long, ServiceCategory> map = new HashMap<>();
            double expectedPrice = 0;
            for (final ServiceInfo serviceInfo : serviceInfos) {
                final ServiceCategory serviceCategory = serviceInfo.getServiceCategory();
                if (!map.containsKey(serviceCategory.getId())) {
                    map.put(serviceCategory.getId(), serviceCategory);
                }

                if (categoryIds.contains(serviceCategory.getId())) {
                    expectedPrice += serviceInfo.getPrice();
                }
            }
            dto.setExpectedPrice(expectedPrice);
            dto.setServiceCategories(serviceCategoryConverter.convert(map.values()));

            return dto;
        });
    }

}
