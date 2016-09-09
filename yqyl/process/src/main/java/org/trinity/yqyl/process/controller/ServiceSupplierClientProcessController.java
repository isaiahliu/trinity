package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.trinity.common.accessright.ISecurityUtil;
import org.trinity.common.exception.IException;
import org.trinity.message.LookupParser;
import org.trinity.message.MessageUtils;
import org.trinity.process.converter.IObjectConverter.CopyPolicy;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.PersonalType;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.common.message.lookup.ServiceSupplierClientStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceSupplierClientProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IContentRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceSupplierClientRepository;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.entity.Content;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient_;
import org.trinity.yqyl.repository.business.entity.User;

@Service
public class ServiceSupplierClientProcessController extends
        AbstractAutowiredCrudProcessController<ServiceSupplierClient, ServiceSupplierClientDto, ServiceSupplierClientSearchingDto, IServiceSupplierClientRepository>
        implements IServiceSupplierClientProcessController {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ISecurityUtil<AccessRight> securityUtil;

    @Autowired
    private IContentRepository contentRepository;

    public ServiceSupplierClientProcessController() {
        super(ServiceSupplierClient.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_SUPPLIER_CLIENT);
    }

    @Override
    @Transactional
    public void audit(final Long id) throws IException {
        final ServiceSupplierClient client = getDomainEntityRepository().findOne(id);
        if (client == null) {
            throw getExceptionFactory().createException(ErrorMessage.UNABLE_TO_FIND_SERVICE_SUPPLIER_CLIENT);
        }
        if (client.getStatus() != ServiceSupplierClientStatus.PROPOSAL) {
            throw getExceptionFactory().createException(ErrorMessage.SERVICE_SUPPLIER_CLIENT_MUST_BE_PROPOSAL);
        }

        client.setStatus(ServiceSupplierClientStatus.ACTIVE);

        getDomainEntityRepository().save(client);
    }

    @Override
    public Page<ServiceSupplierClientDto> getAll(final ServiceSupplierClientSearchingDto dto) throws IException {
        final Pageable pagable = pagingConverter.convert(dto);

        final Specification<ServiceSupplierClient> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<Predicate>();

            if (!StringUtils.isEmpty(dto.getId())) {
                predicates.add(cb.equal(root.get(ServiceSupplierClient_.id), dto.getId()));
            }

            if (!StringUtils.isEmpty(dto.getName())) {
                predicates.add(cb.like(root.get(ServiceSupplierClient_.name), "%" + dto.getName() + "%"));
            }

            if (!StringUtils.isEmpty(dto.getIdentity())) {
                predicates.add(cb.like(root.get(ServiceSupplierClient_.identity), "%" + dto.getIdentity() + "%"));
            }

            if (!StringUtils.isEmpty(dto.getEmail())) {
                predicates.add(cb.like(root.get(ServiceSupplierClient_.email), "%" + dto.getEmail() + "%"));
            }

            final ServiceSupplierClientStatus statusEnum = LookupParser.parse(ServiceSupplierClientStatus.class, dto.getStatus());
            if (statusEnum != null) {
                predicates.add(cb.equal(root.get(ServiceSupplierClient_.status), statusEnum));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        final Page<ServiceSupplierClient> findAll = getDomainEntityRepository().findAll(specification, pagable);

        return findAll.map(item -> getDomainObjectConverter().convert(item));
    }

    @Override
    @Transactional
    public List<ServiceSupplierClientDto> getMe() throws IException {
        final User user = userRepository.findOneByUsername(securityUtil.getCurrentToken().getUsername());

        if (user.getServiceSupplierClients().isEmpty()) {
            final ServiceSupplierClient createOne = createOne(user);
            final List<ServiceSupplierClientDto> result = new ArrayList<>();

            result.add(getDomainObjectConverter().convert(createOne));

            return result;
        } else {
            return getDomainObjectConverter().convert(user.getServiceSupplierClients());
        }
    }

    @Override
    @Transactional
    public void proposal(final ServiceSupplierClientDto serviceSupplierDto) throws IException {
        final User user = userRepository.findOneByUsername(securityUtil.getCurrentToken().getUsername());

        ServiceSupplierClient serviceSupplierClient;
        if (user.getServiceSupplierClients().isEmpty()) {
            serviceSupplierClient = createOne(user);
        } else {
            serviceSupplierClient = user.getServiceSupplierClients().get(0);

            if (!MessageUtils.in(serviceSupplierClient.getStatus(), ServiceSupplierClientStatus.INACTIVE,
                    ServiceSupplierClientStatus.PROPOSAL)) {
                throw getExceptionFactory().createException(ErrorMessage.SUPPLIER_CLIENT_EXISTS);
            }
        }

        serviceSupplierClient.setStatus(ServiceSupplierClientStatus.PROPOSAL);
        getDomainEntityRepository().save(serviceSupplierClient);

        serviceSupplierDto.setId(null);
        serviceSupplierDto.setStatus(null);
        serviceSupplierDto.setType(null);
        getDomainObjectConverter().convertBack(serviceSupplierDto, serviceSupplierClient, CopyPolicy.SOURCE_IS_NOT_NULL);
    }

    private ServiceSupplierClient createOne(final User user) {
        final ServiceSupplierClient serviceSupplierClient = new ServiceSupplierClient();
        serviceSupplierClient.setStatus(ServiceSupplierClientStatus.INACTIVE);
        serviceSupplierClient.setType(PersonalType.BUSINESS);
        serviceSupplierClient.setUser(user);

        final Content identityCopy = new Content();
        identityCopy.setContent(new byte[0]);
        identityCopy.setStatus(RecordStatus.ACTIVE);
        identityCopy.setUuid(UUID.randomUUID().toString());
        contentRepository.save(identityCopy);

        serviceSupplierClient.setIdentityCopy(identityCopy.getUuid());

        final Content licenseCopy = new Content();
        licenseCopy.setContent(new byte[0]);
        licenseCopy.setStatus(RecordStatus.ACTIVE);
        licenseCopy.setUuid(UUID.randomUUID().toString());
        contentRepository.save(licenseCopy);

        serviceSupplierClient.setLicenseCopy(licenseCopy.getUuid());

        getDomainEntityRepository().save(serviceSupplierClient);
        return serviceSupplierClient;
    }
}
