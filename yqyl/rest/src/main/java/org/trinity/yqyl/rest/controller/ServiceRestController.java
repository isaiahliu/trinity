package org.trinity.yqyl.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.yqyl.common.message.dto.domain.ServiceDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSearchingDto;
import org.trinity.yqyl.common.message.dto.request.ServiceRequest;
import org.trinity.yqyl.common.message.dto.response.ServiceResponse;
import org.trinity.yqyl.process.controller.base.IServiceProcessController;

@RestController
@RequestMapping("/transaction/service")
public class ServiceRestController extends
        AbstractApplicationAwareCrudRestController<ServiceDto, ServiceSearchingDto, IServiceProcessController, ServiceRequest, ServiceResponse> {

    @Override
    protected ServiceResponse createResponseInstance() {
        return new ServiceResponse();
    }
}
