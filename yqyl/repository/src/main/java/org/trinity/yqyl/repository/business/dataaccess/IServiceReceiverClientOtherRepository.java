package org.trinity.yqyl.repository.business.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.trinity.repository.repository.IJpaRepository;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientOtherSearchingDto;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientOther;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientOther_;

public interface IServiceReceiverClientOtherRepository
        extends IJpaRepository<ServiceReceiverClientOther, ServiceReceiverClientOtherSearchingDto> {
    @Override
    default Page<ServiceReceiverClientOther> query(final ServiceReceiverClientOtherSearchingDto searchingDto, final Pageable pagable) {
        final Specification<ServiceReceiverClientOther> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if (!searchingDto.isSearchAll()) {
            }
            if (searchingDto.getId() != null) {
                predicates.add(cb.equal(root.get(ServiceReceiverClientOther_.serviceReceiverClientId), searchingDto.getId()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return findAll(specification, pagable);
    }
}
