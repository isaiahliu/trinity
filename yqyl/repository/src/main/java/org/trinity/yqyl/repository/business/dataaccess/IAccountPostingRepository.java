package org.trinity.yqyl.repository.business.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.trinity.message.LookupParser;
import org.trinity.repository.repository.IJpaRepository;
import org.trinity.yqyl.common.message.dto.domain.AccountPostingSearchingDto;
import org.trinity.yqyl.common.message.lookup.AccountPostingStatus;
import org.trinity.yqyl.repository.business.entity.AccountPosting;
import org.trinity.yqyl.repository.business.entity.AccountPosting_;

public interface IAccountPostingRepository extends IJpaRepository<AccountPosting, AccountPostingSearchingDto> {
	@Override
	default Page<AccountPosting> query(final AccountPostingSearchingDto searchingDto, final Pageable pagable) {
		final Specification<AccountPosting> specification = (root, query, cb) -> {
			final List<Predicate> predicates = new ArrayList<>();
			if (!searchingDto.isSearchAll()) {
			}

			if (searchingDto.getId() != null) {
				predicates.add(cb.equal(root.get(AccountPosting_.id), searchingDto.getId()));
			}

			if (searchingDto.getStatus().isEmpty()) {
				if (!searchingDto.isSearchAllStatus()) {
					predicates.add(cb.equal(root.get(AccountPosting_.status), AccountPostingStatus.ACTIVE));
				}
			} else {
				final In<AccountPostingStatus> in = cb.in(root.get(AccountPosting_.status));
				searchingDto.getStatus().stream().map(item -> LookupParser.parse(AccountPostingStatus.class, item))
						.forEach(item -> in.value(item));
				predicates.add(in);
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};
		return findAll(specification, pagable);
	}
}
