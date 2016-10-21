package org.trinity.yqyl.rest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.message.dto.domain.UserRealnameDto;
import org.trinity.yqyl.common.message.dto.domain.UserRealnameSearchingDto;
import org.trinity.yqyl.common.message.dto.request.UserRealnameRequest;
import org.trinity.yqyl.common.message.dto.response.UserRealnameResponse;
import org.trinity.yqyl.process.controller.base.IUserRealnameProcessController;

@RestController
@RequestMapping("/user/realname")
public class UserRealnameRestController extends
        AbstractApplicationAwareCrudRestController<UserRealnameDto, UserRealnameSearchingDto, IUserRealnameProcessController, UserRealnameRequest, UserRealnameResponse> {

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<UserRealnameResponse> getMe(final UserRealnameSearchingDto request) throws IException {
        final UserRealnameResponse response = createResponseInstance();

        final List<UserRealnameDto> data = getDomainProcessController().getMe(request);

        response.addData(data);

        return createResponseEntity(response);
    }

    @Override
    protected UserRealnameResponse createResponseInstance() {
        return new UserRealnameResponse();
    }
}
