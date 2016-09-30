package org.trinity.yqyl.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.yqyl.common.message.dto.domain.UserRealnameDto;
import org.trinity.yqyl.common.message.dto.domain.UserRealnameSearchingDto;
import org.trinity.yqyl.common.message.dto.request.UserRealnameRequest;
import org.trinity.yqyl.common.message.dto.response.UserRealnameResponse;
import org.trinity.yqyl.process.controller.base.IUserRealnameProcessController;

@RestController
@RequestMapping("/user/realname")
public class UserRealnameRestController extends
        AbstractApplicationAwareCrudRestController<UserRealnameDto, UserRealnameSearchingDto, IUserRealnameProcessController, UserRealnameRequest, UserRealnameResponse> {

    @Override
    protected UserRealnameResponse createResponseInstance() {
        return new UserRealnameResponse();
    }
}
