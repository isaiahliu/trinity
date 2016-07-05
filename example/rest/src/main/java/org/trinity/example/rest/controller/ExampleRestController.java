package org.trinity.example.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.dto.IResponse;
import org.trinity.common.exception.IException;
import org.trinity.example.common.dto.request.GetDataRequest;
import org.trinity.example.common.dto.response.GetDataResponse;
import org.trinity.example.process.IExampleProcessController;
import org.trinity.rest.AbstractRestController;

@RestController
@RequestMapping("/example/*")
public class ExampleRestController extends AbstractRestController {
    @Autowired
    private IExampleProcessController exampleProcessController;

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public ResponseEntity<IResponse> data(final GetDataRequest request) throws IException {
        final GetDataResponse response = new GetDataResponse();
        response.getData().add(exampleProcessController.process(request.getData()));
        return createResponseEntity(response);
    }
}
