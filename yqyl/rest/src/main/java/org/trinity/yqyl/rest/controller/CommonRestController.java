package org.trinity.yqyl.rest.controller;

import javax.annotation.security.PermitAll;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.dto.IResponse;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;
import org.trinity.rest.AbstractRestController;

@RestController
@RequestMapping("/common/*")
public class CommonRestController extends AbstractRestController {

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    @PreAuthorize("permitAll()")
    @PostAuthorize("permitAll()")
    @PermitAll
    public ResponseEntity<IResponse> ping() throws IException {
        final DefaultResponse response = new DefaultResponse();

        response.addData("Ping Successfully!");

        return createResponseEntity(response);
    }
}
