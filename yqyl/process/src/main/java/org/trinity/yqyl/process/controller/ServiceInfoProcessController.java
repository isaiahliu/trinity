package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
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
import org.trinity.yqyl.repository.business.dataaccess.IServiceCategoryRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceInfoRepository;
import org.trinity.yqyl.repository.business.entity.ServiceCategory;
import org.trinity.yqyl.repository.business.entity.ServiceCategory_;
import org.trinity.yqyl.repository.business.entity.ServiceInfo;
import org.trinity.yqyl.repository.business.entity.ServiceInfo_;

@Service
public class ServiceInfoProcessController
        extends AbstractAutowiredCrudProcessController<ServiceInfo, ServiceInfoDto, ServiceInfoSearchingDto, IServiceInfoRepository>
        implements IServiceProcessController {
    @Autowired
    private IServiceCategoryRepository serviceCategoryRepository;

    @Autowired
    private IObjectConverter<ServiceCategory, ServiceCategoryDto> serviceCategoryConverter;

    public ServiceInfoProcessController() {
        super(ServiceInfo.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_INFO);
    }

    @Override
    @Transactional
    public Page<ServiceInfoDto> getAll(final ServiceInfoSearchingDto searchingData) throws IException {
        final List<Long> categoryIds = new ArrayList<>();
        final Pageable pagable = pagingConverter.convert(searchingData);

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

        final Specification<ServiceInfo> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (!categoryIds.isEmpty()) {
                predicates.add(root.join(ServiceInfo_.serviceCategories).get(ServiceCategory_.id).in(categoryIds));
                query.distinct(true);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return getDomainEntityRepository().findAll(specification, pagable).map(item -> {
            final ServiceInfoDto dto = getDomainObjectConverter().convert(item);

            dto.setServiceCategories(serviceCategoryConverter.convert(item.getServiceCategories()));

            return dto;
        });
    }
}
