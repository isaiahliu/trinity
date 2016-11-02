package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.trinity.common.accessright.ISecurityUtil.CheckMode;
import org.trinity.common.exception.IException;
import org.trinity.message.IMessageResolverChain;
import org.trinity.yqyl.common.message.dto.domain.AccessrightDto;
import org.trinity.yqyl.common.message.dto.domain.AccessrightSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IAccessrightProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IAccessrightRepository;
import org.trinity.yqyl.repository.business.entity.Accessright;
import org.trinity.yqyl.repository.business.entity.Accessright_;

@Service
public class AccessrightProcessController
        extends AbstractAutowiredCrudProcessController<Accessright, AccessrightDto, AccessrightSearchingDto, IAccessrightRepository>
        implements IAccessrightProcessController {
    @Autowired
    private IMessageResolverChain messageResolver;

    public AccessrightProcessController() {
        super(Accessright.class, ErrorMessage.UNABLE_TO_FIND_ACCESSRIGHT);
    }

    @Override
    public Page<Accessright> queryAll(final AccessrightSearchingDto data) throws IException {
        if (data.isIncludeSuper() && !getSecurityUtil().hasAccessRight(CheckMode.ANY, AccessRight.SUPER_USER)) {
            data.setIncludeSuper(false);
        }
        final Specification<Accessright> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (data.isIncludeSuper()) {
                predicates.add(cb.equal(root.get(Accessright_.name), AccessRight.SUPER_USER));
            } else {
                predicates.add(cb.equal(root.join(Accessright_.parent).get(Accessright_.name), AccessRight.SUPER_USER));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return getDomainEntityRepository().findAll(specification, getPagingConverter().convert(data));
    }

    @Override
    @Transactional
    public void refreshAll() throws IException {
        final List<AccessRight> allAccessrights = Arrays.stream(AccessRight.values()).collect(Collectors.toList());
        final Map<AccessRight, Accessright> allExistingEntities = StreamSupport
                .stream(getDomainEntityRepository().findAll().spliterator(), false).filter(item -> item.getName() != null)
                .collect(Collectors.toMap(Accessright::getName, item -> item));
        for (final AccessRight item : allAccessrights.stream().filter(item -> item.getParentAccessRight() == null)
                .collect(Collectors.toList())) {
            processAccessRight(null, item, allAccessrights, allExistingEntities);
        }

    }

    private void processAccessRight(final Accessright parent, final AccessRight accessright, final List<AccessRight> allAccessrights,
            final Map<AccessRight, Accessright> allExistingEntities) {
        allAccessrights.remove(accessright);

        Accessright entity = allExistingEntities.get(accessright);
        if (entity == null) {
            entity = new Accessright();
            entity.setName(accessright);
            entity.setDescription(messageResolver.getMessage(accessright));
        } else {
            allExistingEntities.remove(entity);
        }
        entity.setParent(parent);
        entity.setStatus(RecordStatus.ACTIVE);

        getDomainEntityRepository().save(entity);

        final List<AccessRight> children = allAccessrights.stream().filter(item -> item.getParentAccessRight() == accessright)
                .collect(Collectors.toList());

        for (final AccessRight child : children) {
            processAccessRight(entity, child, allAccessrights, allExistingEntities);
        }
    }
}
