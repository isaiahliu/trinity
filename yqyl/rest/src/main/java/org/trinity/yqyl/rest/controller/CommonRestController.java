package org.trinity.yqyl.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.dto.IResponse;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;
import org.trinity.rest.controller.AbstractRestController;

@RestController
@RequestMapping("/common")
public class CommonRestController extends AbstractRestController {
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public ResponseEntity<IResponse> ping() throws IException {
        final DefaultResponse response = new DefaultResponse();

        response.addData("Ping Successfully!");

        return createResponseEntity(response);
    }
}
