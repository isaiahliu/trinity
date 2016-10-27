package org.trinity.yqyl.process.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderRequirementDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderRequirementSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceOrderRequirementProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceOrderRequirementRepository;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.entity.ServiceOrderRequirement;
import org.trinity.yqyl.repository.business.entity.User;

@Service
public class ServiceOrderRequirementProcessController extends
        AbstractAutowiredCrudProcessController<ServiceOrderRequirement, ServiceOrderRequirementDto, ServiceOrderRequirementSearchingDto, IServiceOrderRequirementRepository>
        implements IServiceOrderRequirementProcessController {
    @Autowired
    private IUserRepository userRepository;

    public ServiceOrderRequirementProcessController() {
        super(ServiceOrderRequirement.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_ORDER_REQUIREMENT);
    }

    @Override
    protected void addRelationship(final ServiceOrderRequirement entity, final ServiceOrderRequirementDto dto) throws IException {
        final String username = getSecurityUtil().getCurrentToken().getUsername();
        User user;
        if (dto.getUser() == null || dto.getUser().getId() == null) {
            user = userRepository.findOneByUsername(username);
        } else {
            user = userRepository.findOne(dto.getUser().getId());
        }

        entity.setUser(user);
    }
}