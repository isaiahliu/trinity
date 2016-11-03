package org.trinity.yqyl.repository.business.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.trinity.repository.repository.IJpaRepository;
import org.trinity.yqyl.common.message.dto.domain.YiquanSearchingDto;
import org.trinity.yqyl.repository.business.entity.Yiquan;
import org.trinity.yqyl.repository.business.entity.Yiquan_;

public interface IYiquanRepository extends IJpaRepository<Yiquan, YiquanSearchingDto> {
    Yiquan findOneByCode(String code);

    @Override
    default Page<Yiquan> query(final YiquanSearchingDto searchingDto, final Pageable pagable) {
        final Specification<Yiquan> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if (!searchingDto.isSearchAll()) {
            }
            if (searchingDto.getId() != null) {
                predicates.add(cb.equal(root.get(Yiquan_.id), searchingDto.getId()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return findAll(specification, pagable);
    }
}
