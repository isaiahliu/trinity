package org.trinity.yqyl.process.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trinity.common.exception.IException;
import org.trinity.message.MessageUtils;
import org.trinity.message.exception.GeneralErrorMessage;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.ServiceReceiverClientStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceReceiverClientProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceReceiverClientRepository;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClient;
import org.trinity.yqyl.repository.business.entity.User;

@Service
public class ServiceReceiverClientProcessController extends
        AbstractAutowiredCrudProcessController<ServiceReceiverClient, ServiceReceiverClientDto, ServiceReceiverClientSearchingDto, IServiceReceiverClientRepository>
        implements IServiceReceiverClientProcessController {
    @Autowired
    private IUserRepository userRepository;

    @Override
    @Transactional
    public void audit(final Long id) throws IException {
        final ServiceReceiverClient client = getDomainEntityRepository().findOne(id);
        if (client == null) {
            throw getExceptionFactory().createException(GeneralErrorMessage.UNABLE_TO_FIND_INSTANCE);
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
            throw getExceptionFactory().createException(GeneralErrorMessage.UNABLE_TO_FIND_INSTANCE, String.valueOf(id));
        }

        if (!MessageUtils.in(entity.getStatus(), ServiceReceiverClientStatus.INACTIVE, ServiceReceiverClientStatus.PROPOSAL)) {
            throw getExceptionFactory().createException(ErrorMessage.ONLY_PROPOSAL_CLIENT_CAN_BE_CANCELLED);
        }
        getDomainEntityRepository().delete(entity);
    }

    @Override
    @Transactional
    public List<ServiceReceiverClientDto> getMe(final ServiceReceiverClientSearchingDto dto) throws IException {
        final User user = userRepository.findOneByUsername(getSecurityUtil().getCurrentToken().getUsername());

        return getDomainObjectConverter().convert(user.getServiceReceiverClients());
    }

    @Override
    protected void addRelationshipFields(final ServiceReceiverClient entity, final ServiceReceiverClientDto dto) throws IException {
        final User user = userRepository.findOneByUsername(getSecurityUtil().getCurrentToken().getUsername());
        entity.setUser(user);
    }
}
