package org.trinity.yqyl.rest.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.accessright.ISecurityUtil;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.dto.validator.OnValid;
import org.trinity.common.exception.IException;
import org.trinity.rest.controller.AbstractRestController;
import org.trinity.yqyl.common.message.dto.domain.SecurityDto;
import org.trinity.yqyl.common.message.dto.request.AuthenticateRequest;
import org.trinity.yqyl.common.message.dto.response.SecurityResponse;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.process.controller.base.ISecurityProcessController;

@RestController
@RequestMapping("/security")
public class SecurityRestController extends AbstractRestController {
    @Autowired
    private ISecurityProcessController securityProcessController;

    @Autowired
    private ISecurityUtil<AccessRight> securityUtil;

    @RequestMapping(value = "/authenticate", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<SecurityResponse> authenticate(@RequestBody @OnValid final AuthenticateRequest request)
            throws IException {
        final SecurityResponse response = new SecurityResponse();
        final SecurityDto user = securityProcessController.authenticate(securityUtil.getCurrentToken().getToken(),
                request.getUser().getUsername(), request.getUser().getPassword());

        response.addData(user);

        return createResponseEntity(response);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.PUT)
    public ResponseEntity<SecurityResponse> logout() throws IException {
        final SecurityResponse response = new SecurityResponse();

        final SecurityDto token = securityProcessController.logout(securityUtil.getCurrentToken().getToken());

        response.addData(token);

        return createResponseEntity(response);
    }

    @RequestMapping(value = "/authorities", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<DefaultResponse> register() throws IException {
        final DefaultResponse response = new DefaultResponse();

        response.addData(securityUtil.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList()));

        return createResponseEntity(response);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<DefaultResponse> register(@RequestBody @OnValid final AuthenticateRequest request)
            throws IException {
        final DefaultResponse response = new DefaultResponse();
        securityProcessController.register(request.getUser().getUsername(), request.getUser().getPassword());

        return createResponseEntity(response);
    }
}
