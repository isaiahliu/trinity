package org.trinity.yqyl.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientSearchingDto;
import org.trinity.yqyl.common.message.dto.request.ServiceSupplierClientRequest;
import org.trinity.yqyl.common.message.dto.response.ServiceSupplierClientResponse;
import org.trinity.yqyl.process.controller.base.IServiceSupplierClientProcessController;

@RestController
@RequestMapping("/client/supplier")
public class ServiceSupplierClientRestController extends
        AbstractApplicationAwareCrudRestController<ServiceSupplierClientDto, ServiceSupplierClientSearchingDto, IServiceSupplierClientProcessController, ServiceSupplierClientRequest, ServiceSupplierClientResponse> {
    @Override
    protected ServiceSupplierClientResponse createResponseInstance() {
        return new ServiceSupplierClientResponse();
    }
}
