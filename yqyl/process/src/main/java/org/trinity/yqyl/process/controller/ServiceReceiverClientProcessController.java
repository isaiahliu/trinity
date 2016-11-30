package org.trinity.yqyl.process.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trinity.common.exception.IException;
import org.trinity.message.LookupParser;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientSearchingDto;
import org.trinity.yqyl.common.message.lookup.FamilyRelationship;
import org.trinity.yqyl.common.message.lookup.ServiceReceiverClientStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IAccountProcessController;
import org.trinity.yqyl.process.controller.base.IContentProcessController;
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

    @Autowired
    private IAccountProcessController accountProcessController;

    @Autowired
    private IContentProcessController contentProcessController;

    @Override
    @Transactional(rollbackOn = IException.class)
    public List<ServiceReceiverClientDto> addAll(final List<ServiceReceiverClientDto> dtos) throws IException {
        final User user = userRepository.findOneByUsername(getCurrentUsername());

        final List<ServiceReceiverClient> entities = dtos.stream().map(item -> {
            final ServiceReceiverClient entity = new ServiceReceiverClient();

            entity.setUser(user);
            entity.setName(item.getName());
            entity.setCellphoneNo(item.getCellphoneNo());
            entity.setFamilyRelationship(LookupParser.parse(FamilyRelationship.class, item.getFamilyRelationship().getCode()));
            entity.setStatus(ServiceReceiverClientStatus.PROPOSAL);

            entity.setIdentityCardCopy(contentProcessController.create());

            return entity;
        }).collect(Collectors.toList());

        return getDomainObjectConverter().convert(getDomainEntityRepository().save(entities));
    }

    @Override
    public void realname(final List<ServiceReceiverClientDto> data) throws IException {
        final List<ServiceReceiverClient> entities = data.stream().map(item -> {
            final ServiceReceiverClient serviceReceiverClient = getDomainEntityRepository().findOne(item.getId());

            if (!serviceReceiverClient.getUser().getUsername().equals(getCurrentUsername())) {
                return serviceReceiverClient;
            }

            serviceReceiverClient.setName(item.getName());
            serviceReceiverClient.setIdentityCard(item.getIdentityCard());
            serviceReceiverClient.setStatus(ServiceReceiverClientStatus.REALNAME);

            if (serviceReceiverClient.getAccount() == null) {
                serviceReceiverClient.setAccount(accountProcessController.createAccount());
            }
            return serviceReceiverClient;
        }).collect(Collectors.toList());

        getDomainEntityRepository().save(entities);
    }

    @Override
    protected void addRelationshipFields(final ServiceReceiverClient entity, final ServiceReceiverClientDto dto) throws IException {
        final User user = userRepository.findOneByUsername(getSecurityUtil().getCurrentToken().getUsername());
        entity.setUser(user);
    }
}
