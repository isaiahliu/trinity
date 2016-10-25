package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.trinity.common.exception.IException;
import org.trinity.message.LookupParser;
import org.trinity.yqyl.common.message.dto.domain.OperatorClientDto;
import org.trinity.yqyl.common.message.dto.domain.OperatorClientSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.OperatorClientStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IOperatorClientProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IOperatorClientRepository;
import org.trinity.yqyl.repository.business.entity.OperatorClient;
import org.trinity.yqyl.repository.business.entity.OperatorClient_;
import org.trinity.yqyl.repository.business.entity.User_;

@Service
public class OperatorClientProcessController extends
        AbstractAutowiredCrudProcessController<OperatorClient, OperatorClientDto, OperatorClientSearchingDto, IOperatorClientRepository>
        implements IOperatorClientProcessController {
    public OperatorClientProcessController() {
        super(OperatorClient.class, ErrorMessage.UNABLE_TO_FIND_OPERATOR_CLIENT);
    }

    @Override
    public Page<OperatorClientDto> getAll(final OperatorClientSearchingDto dto) throws IException {
        final Specification<OperatorClient> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(dto.getUsername())) {
                predicates.add(cb.like(root.join(OperatorClient_.user).get(User_.username), "%" + dto.getUsername() + "%"));
            }

            if (!StringUtils.isEmpty(dto.getName())) {
                predicates.add(cb.like(root.get(OperatorClient_.name), "%" + dto.getName() + "%"));
            }

            if (!StringUtils.isEmpty(dto.getStaffNo())) {
                predicates.add(cb.like(root.get(OperatorClient_.staffNo), "%" + dto.getStaffNo() + "%"));
            }

            if (!dto.getStatus().isEmpty()) {
                final In<OperatorClientStatus> in = cb.in(root.get(OperatorClient_.status));
                dto.getStatus().forEach(item -> in.value(LookupParser.parse(OperatorClientStatus.class, item)));
                predicates.add(in);
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
        final Pageable pagable = getPagingConverter().convert(dto);

        final Page<OperatorClient> findAll = getDomainEntityRepository().findAll(specification, pagable);

        return findAll.map(item -> getDomainObjectConverter().convert(item));
    }
}
