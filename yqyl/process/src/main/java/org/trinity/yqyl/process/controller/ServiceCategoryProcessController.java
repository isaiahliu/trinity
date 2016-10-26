package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.trinity.common.exception.IException;
import org.trinity.message.LookupParser;
import org.trinity.yqyl.common.message.dto.domain.ServiceCategoryDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceCategorySearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceCategoryProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceCategoryRepository;
import org.trinity.yqyl.repository.business.entity.ServiceCategory;
import org.trinity.yqyl.repository.business.entity.ServiceCategory_;

@Service
public class ServiceCategoryProcessController extends
        AbstractAutowiredCrudProcessController<ServiceCategory, ServiceCategoryDto, ServiceCategorySearchingDto, IServiceCategoryRepository>
        implements IServiceCategoryProcessController {
    public ServiceCategoryProcessController() {
        super(ServiceCategory.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_CATEGORY);
    }

    @Override
    public Page<ServiceCategory> queryAll(final ServiceCategorySearchingDto data) throws IException {
        final Pageable pagable = getPagingConverter().convert(data);

        final Specification<ServiceCategory> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(data.getName())) {
                predicates.add(cb.like(root.get(ServiceCategory_.name), "%" + data.getName() + "%"));
            }

            if (data.getId() != null && data.getId() > 0) {
                predicates.add(cb.equal(root.get(ServiceCategory_.id), data.getId()));
            } else if (data.getParentId() == null || data.getParentId() == 0) {
                predicates.add(cb.isNull(root.get(ServiceCategory_.parent)));
            } else {
                predicates.add(cb.equal(root.join(ServiceCategory_.parent).get(ServiceCategory_.id), data.getParentId()));
            }
            if (!data.getStatus().isEmpty()) {
                final In<RecordStatus> in = cb.in(root.get(ServiceCategory_.status));
                data.getStatus().forEach(item -> in.value(LookupParser.parse(RecordStatus.class, item)));
                predicates.add(in);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return getDomainEntityRepository().findAll(specification, pagable);
    }

    @Override
    protected void addRelationship(final ServiceCategory entity, final ServiceCategoryDto dto) throws IException {
        if (dto.getParent() != null && dto.getParent().getId() != null && dto.getParent().getId() != 0) {
            entity.setParent(getDomainEntityRepository().findOne(dto.getParent().getId()));
        }
    }
}
