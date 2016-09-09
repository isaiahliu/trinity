package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.trinity.common.accessright.ISecurityUtil;
import org.trinity.common.exception.IException;
import org.trinity.message.LookupParser;
import org.trinity.message.MessageUtils;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.ServiceReceiverClientStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceReceiverClientProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceReceiverClientRepository;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClient;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClient_;
import org.trinity.yqyl.repository.business.entity.User;

@Service
public class ServiceReceiverClientProcessController extends
        AbstractAutowiredCrudProcessController<ServiceReceiverClient, ServiceReceiverClientDto, ServiceReceiverClientSearchingDto, IServiceReceiverClientRepository>
        implements IServiceReceiverClientProcessController {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ISecurityUtil<AccessRight> securityUtil;

    public ServiceReceiverClientProcessController() {
        super(ServiceReceiverClient.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_RECEIVER_CLIENT);
    }

    @Override
    @Transactional
    public void audit(final Long id) throws IException {
        final ServiceReceiverClient client = getDomainEntityRepository().findOne(id);
        if (client == null) {
            throw getExceptionFactory().createException(ErrorMessage.UNABLE_TO_FIND_SERVICE_RECEIVER_CLIENT);
        }
        if (client.getStatus() != ServiceReceiverClientStatus.PROPOSAL) {
            throw getExceptionFactory().createException(ErrorMessage.SERVICE_RECEIVER_CLIENT_MUST_BE_PROPOSAL);
        }

        client.setStatus(ServiceReceiverClientStatus.ACTIVE);

        getDomainEntityRepository().save(client);
    }

    @Override
    public void cancel(final Long id) throws IException {
        final ServiceReceiverClient entity = getDomainEntityRepository().findOne(id);
        if (entity == null) {
            throw getExceptionFactory().createException(getNoInstanceFoundError(), String.valueOf(id));
        }

        if (!MessageUtils.in(entity.getStatus(), ServiceReceiverClientStatus.INACTIVE, ServiceReceiverClientStatus.PROPOSAL)) {
            throw getExceptionFactory().createException(ErrorMessage.ONLY_PROPOSAL_CLIENT_CAN_BE_CANCELLED);
        }
        getDomainEntityRepository().delete(entity);
    }

    @Override
    public Page<ServiceReceiverClientDto> getAll(final ServiceReceiverClientSearchingDto dto) throws IException {
        final Pageable pagable = pagingConverter.convert(dto);

        final Specification<ServiceReceiverClient> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<Predicate>();

            if (!StringUtils.isEmpty(dto.getId())) {
                predicates.add(cb.equal(root.get(ServiceReceiverClient_.id), dto.getId()));
            }

            if (!StringUtils.isEmpty(dto.getName())) {
                predicates.add(cb.like(root.get(ServiceReceiverClient_.name), "%" + dto.getName() + "%"));
            }

            if (!StringUtils.isEmpty(dto.getIdentity())) {
                predicates.add(cb.like(root.get(ServiceReceiverClient_.identityCard), "%" + dto.getIdentity() + "%"));
            }

            if (!StringUtils.isEmpty(dto.getYijincode())) {
                predicates.add(cb.like(root.get(ServiceReceiverClient_.yijinCode), "%" + dto.getYijincode() + "%"));
            }

            final ServiceReceiverClientStatus statusEnum = LookupParser.parse(ServiceReceiverClientStatus.class, dto.getStatus());
            if (statusEnum != null) {
                predicates.add(cb.equal(root.get(ServiceReceiverClient_.status), statusEnum));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        final Page<ServiceReceiverClient> findAll = getDomainEntityRepository().findAll(specification, pagable);

        return findAll.map(item -> getDomainObjectConverter().convert(item));
    }

    @Override
    @Transactional
    public List<ServiceReceiverClientDto> getMe() throws IException {
        final User user = userRepository.findOneByUsername(securityUtil.getCurrentToken().getUsername());

        return getDomainObjectConverter().convert(user.getServiceReceiverClients());
    }

    @Override
    protected void addRelationship(final ServiceReceiverClient entity, final ServiceReceiverClientDto dto) throws IException {
        final User user = userRepository.findOneByUsername(securityUtil.getCurrentToken().getUsername());
        entity.setUser(user);
    }
}
