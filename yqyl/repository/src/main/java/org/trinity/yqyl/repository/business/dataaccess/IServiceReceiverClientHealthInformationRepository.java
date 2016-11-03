package org.trinity.yqyl.repository.business.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.trinity.repository.repository.IJpaRepository;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientHealthInformationSearchingDto;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientHealthInformation;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientHealthInformation_;

public interface IServiceReceiverClientHealthInformationRepository
        extends IJpaRepository<ServiceReceiverClientHealthInformation, ServiceReceiverClientHealthInformationSearchingDto> {
    @Override
    default Page<ServiceReceiverClientHealthInformation> query(final ServiceReceiverClientHealthInformationSearchingDto searchingDto,
            final Pageable pagable) {
        final Specification<ServiceReceiverClientHealthInformation> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if (!searchingDto.isSearchAll()) {
            }
            if (searchingDto.getId() != null) {
                predicates.add(cb.equal(root.get(ServiceReceiverClientHealthInformation_.serviceReceiverClientId), searchingDto.getId()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return findAll(specification, pagable);
    }
}
