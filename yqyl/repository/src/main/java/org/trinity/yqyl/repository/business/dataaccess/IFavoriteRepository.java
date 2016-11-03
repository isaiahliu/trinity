package org.trinity.yqyl.repository.business.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.trinity.repository.repository.IJpaRepository;
import org.trinity.yqyl.common.message.dto.domain.FavoriteSearchingDto;
import org.trinity.yqyl.repository.business.entity.Favorite;
import org.trinity.yqyl.repository.business.entity.Favorite_;

public interface IFavoriteRepository extends IJpaRepository<Favorite, FavoriteSearchingDto> {
    @Override
    default Page<Favorite> query(final FavoriteSearchingDto searchingDto, final Pageable pagable) {
        final Specification<Favorite> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if (!searchingDto.isSearchAll()) {
            }
            if (searchingDto.getId() != null) {
                predicates.add(cb.equal(root.get(Favorite_.id), searchingDto.getId()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return findAll(specification, pagable);
    }
}
