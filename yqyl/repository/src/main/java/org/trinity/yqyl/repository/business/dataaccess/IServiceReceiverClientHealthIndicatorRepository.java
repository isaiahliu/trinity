package org.trinity.yqyl.repository.business.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.trinity.repository.repository.IJpaRepository;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientHealthIndicatorSearchingDto;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientHealthIndicator;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientHealthIndicator_;

public interface IServiceReceiverClientHealthIndicatorRepository
        extends IJpaRepository<ServiceReceiverClientHealthIndicator, ServiceReceiverClientHealthIndicatorSearchingDto> {
    @Override
    default Page<ServiceReceiverClientHealthIndicator> query(final ServiceReceiverClientHealthIndicatorSearchingDto searchingDto,
            final Pageable pagable) {
        final Specification<ServiceReceiverClientHealthIndicator> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if (!searchingDto.isSearchAll()) {
            }
            if (searchingDto.getId() != null) {
                predicates.add(cb.equal(root.get(ServiceReceiverClientHealthIndicator_.serviceReceiverClientId), searchingDto.getId()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return findAll(specification, pagable);
    }
}
