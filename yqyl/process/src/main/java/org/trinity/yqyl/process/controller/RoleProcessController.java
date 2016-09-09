package org.trinity.yqyl.process.controller;

import org.springframework.stereotype.Service;
import org.trinity.yqyl.common.message.dto.domain.RoleDto;
import org.trinity.yqyl.common.message.dto.domain.RoleSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IRoleProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IRoleRepository;
import org.trinity.yqyl.repository.business.entity.Role;

@Service
public class RoleProcessController extends AbstractAutowiredCrudProcessController<Role, RoleDto, RoleSearchingDto, IRoleRepository>
        implements IRoleProcessController {
    public RoleProcessController() {
        super(Role.class, ErrorMessage.UNABLE_TO_FIND_ROLE);
    }
}
