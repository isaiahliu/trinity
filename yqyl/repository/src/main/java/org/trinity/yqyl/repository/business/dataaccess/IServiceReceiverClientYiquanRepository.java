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
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientYiquanSearchingDto;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientYiquan;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientYiquan_;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClient_;
import org.trinity.yqyl.repository.business.entity.User_;

public interface IServiceReceiverClientYiquanRepository
        extends IJpaRepository<ServiceReceiverClientYiquan, ServiceReceiverClientYiquanSearchingDto> {
    ServiceReceiverClientYiquan findOneByCode(String code);

    @Override
    default Page<ServiceReceiverClientYiquan> query(final ServiceReceiverClientYiquanSearchingDto searchingDto, final Pageable pagable) {
        final Specification<ServiceReceiverClientYiquan> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();
            if (!searchingDto.isSearchAll()) {
                predicates.add(cb.equal(
                        root.join(ServiceReceiverClientYiquan_.serviceReceiverClient).join(ServiceReceiverClient_.user).get(User_.username),
                        searchingDto.getCurrentUsername()));
            }

            if (searchingDto.getId() != null) {
                predicates.add(cb.equal(root.get(ServiceReceiverClientYiquan_.serviceReceiverClientId), searchingDto.getId()));
            }

            if (searchingDto.getStatus().isEmpty()) {
                if (!searchingDto.isSearchAllStatus()) {
                    predicates.add(cb.equal(root.get(ServiceReceiverClientYiquan_.status), RecordStatus.ACTIVE));
                }
            } else {
                final In<RecordStatus> in = cb.in(root.get(ServiceReceiverClientYiquan_.status));
                searchingDto.getStatus().stream().map(item -> LookupParser.parse(RecordStatus.class, item)).forEach(item -> in.value(item));
                predicates.add(in);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return findAll(specification, pagable);
    }
}
