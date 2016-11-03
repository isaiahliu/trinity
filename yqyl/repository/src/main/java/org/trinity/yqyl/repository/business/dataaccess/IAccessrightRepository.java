package org.trinity.yqyl.repository.business.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.trinity.repository.repository.IJpaRepository;
import org.trinity.yqyl.common.message.dto.domain.AccessrightSearchingDto;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.repository.business.entity.Accessright;
import org.trinity.yqyl.repository.business.entity.Accessright_;

public interface IAccessrightRepository extends IJpaRepository<Accessright, AccessrightSearchingDto> {
    Accessright findOneByName(AccessRight name);

    @Override
    default Page<Accessright> query(final AccessrightSearchingDto searchingDto, final Pageable pagable) {
        final Specification<Accessright> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (searchingDto.isIncludeSuper()) {
                predicates.add(cb.equal(root.get(Accessright_.name), AccessRight.SUPER_USER));
            } else {
                predicates.add(cb.equal(root.join(Accessright_.parent).get(Accessright_.name), AccessRight.SUPER_USER));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return findAll(specification, pagable);
    }
}
