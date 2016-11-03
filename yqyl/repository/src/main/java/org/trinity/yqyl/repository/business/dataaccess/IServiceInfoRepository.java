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
import org.trinity.yqyl.common.message.dto.domain.ServiceInfoSearchingDto;
import org.trinity.yqyl.common.message.lookup.ServiceStatus;
import org.trinity.yqyl.repository.business.entity.ServiceInfo;
import org.trinity.yqyl.repository.business.entity.ServiceInfo_;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient_;

public interface IServiceInfoRepository extends IJpaRepository<ServiceInfo, ServiceInfoSearchingDto> {
    @Override
    default Page<ServiceInfo> query(final ServiceInfoSearchingDto searchingDto, final Pageable pagable) {
        final Specification<ServiceInfo> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (searchingDto.getServiceSupplierClientId() != null) {
                predicates.add(cb.equal(root.join(ServiceInfo_.serviceSupplierClient).get(ServiceSupplierClient_.userId),
                        searchingDto.getServiceSupplierClientId()));
            }

            if (!searchingDto.getStatus().isEmpty()) {
                final In<ServiceStatus> in = cb.in(root.get(ServiceInfo_.status));
                searchingDto.getStatus().forEach(item -> in.value(LookupParser.parse(ServiceStatus.class, item)));
                predicates.add(in);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return findAll(specification, pagable);
    }
}
