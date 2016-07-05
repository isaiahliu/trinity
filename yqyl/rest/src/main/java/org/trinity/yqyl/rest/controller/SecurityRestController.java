package org.trinity.yqyl.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.dto.IResponse;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;
import org.trinity.rest.AbstractRestController;
import org.trinity.yqyl.common.message.dto.domain.TokenDto;
import org.trinity.yqyl.common.message.dto.domain.UserDto;
import org.trinity.yqyl.common.message.dto.request.GetTokenRequest;
import org.trinity.yqyl.common.message.dto.request.PutAuthenticateRequest;
import org.trinity.yqyl.common.message.dto.response.TokenResponse;
import org.trinity.yqyl.common.message.dto.response.UserResponse;
import org.trinity.yqyl.process.controller.ISecurityProcessController;

@RestController
@RequestMapping("/security/*")
public class SecurityRestController extends AbstractRestController {
    @Autowired
    private ISecurityProcessController securityProcessController;

    @RequestMapping(value = "/authenticate", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<UserResponse> authenticate(@RequestBody final PutAuthenticateRequest request)
            throws IException {
        final UserResponse response = new UserResponse();
        final UserDto user = securityProcessController.authenticate(getToken(), request.getUser().getUsername(),
                request.getUser().getPassword());

        response.addData(user);

        return createResponseEntity(response);
    }

    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<TokenResponse> getToken(final GetTokenRequest request) throws IException {
        final TokenResponse response = new TokenResponse();

        final TokenDto token = securityProcessController.getToken(request.getIdentity());

        response.addData(token);

        return createResponseEntity(response);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<IResponse> test() throws IException {
        final DefaultResponse response = new DefaultResponse();

        return createResponseEntity(response);
    }
}
