package org.trinity.yqyl.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.yqyl.common.message.dto.domain.OperatorClientDto;
import org.trinity.yqyl.common.message.dto.domain.OperatorClientSearchingDto;
import org.trinity.yqyl.common.message.dto.request.OperatorClientRequest;
import org.trinity.yqyl.common.message.dto.response.OperatorClientResponse;
import org.trinity.yqyl.process.controller.base.IOperatorClientProcessController;

@RestController
@RequestMapping("/client/operator")
public class OperatorClientRestController extends
        AbstractApplicationAwareCrudRestController<OperatorClientDto, OperatorClientSearchingDto, IOperatorClientProcessController, OperatorClientRequest, OperatorClientResponse> {

    @Override
    protected OperatorClientResponse createResponseInstance() {
        return new OperatorClientResponse();
    }
}
