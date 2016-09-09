package org.trinity.yqyl.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.yqyl.common.message.dto.domain.RoleDto;
import org.trinity.yqyl.common.message.dto.domain.RoleSearchingDto;
import org.trinity.yqyl.common.message.dto.request.RoleRequest;
import org.trinity.yqyl.common.message.dto.response.RoleResponse;
import org.trinity.yqyl.process.controller.base.IRoleProcessController;

@RestController
@RequestMapping("/security/role")
public class RoleRestController
        extends AbstractApplicationAwareCrudRestController<RoleDto, RoleSearchingDto, IRoleProcessController, RoleRequest, RoleResponse> {

    @Override
    protected RoleResponse createResponseInstance() {
        return new RoleResponse();
    }
}
