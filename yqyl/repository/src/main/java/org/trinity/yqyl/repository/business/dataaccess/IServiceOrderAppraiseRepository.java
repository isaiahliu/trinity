package org.trinity.yqyl.repository.business.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.trinity.repository.repository.IJpaRepository;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderAppraiseSearchingDto;
import org.trinity.yqyl.repository.business.entity.ServiceOrderAppraise;
import org.trinity.yqyl.repository.business.entity.ServiceOrderAppraise_;

public interface IServiceOrderAppraiseRepository extends IJpaRepository<ServiceOrderAppraise, ServiceOrderAppraiseSearchingDto> {
    @Override
    default Page<ServiceOrderAppraise> query(final ServiceOrderAppraiseSearchingDto searchingDto, final Pageable pagable) {
        final Specification<ServiceOrderAppraise> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if (!searchingDto.isSearchAll()) {
            }
            if (searchingDto.getId() != null) {
                predicates.add(cb.equal(root.get(ServiceOrderAppraise_.serviceOrderId), searchingDto.getId()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return findAll(specification, pagable);
    }
}
