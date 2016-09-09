package org.trinity.yqyl.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.yqyl.common.message.dto.domain.UserGroupDto;
import org.trinity.yqyl.common.message.dto.domain.UserGroupSearchingDto;
import org.trinity.yqyl.common.message.dto.request.UserGroupRequest;
import org.trinity.yqyl.common.message.dto.response.UserGroupResponse;
import org.trinity.yqyl.process.controller.base.IUserGroupProcessController;

@RestController
@RequestMapping("/security/usergroup")
public class UserGroupRestController extends
        AbstractApplicationAwareCrudRestController<UserGroupDto, UserGroupSearchingDto, IUserGroupProcessController, UserGroupRequest, UserGroupResponse> {

    @Override
    protected UserGroupResponse createResponseInstance() {
        return new UserGroupResponse();
    }
}
