package org.trinity.yqyl.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierStaffDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierStaffSearchingDto;
import org.trinity.yqyl.common.message.dto.request.ServiceSupplierStaffRequest;
import org.trinity.yqyl.common.message.dto.response.ServiceSupplierStaffResponse;
import org.trinity.yqyl.process.controller.base.IServiceSupplierStaffProcessController;

@RestController
@RequestMapping("/client/supplier/staff")
public class ServiceSupplierStaffRestController extends
        AbstractApplicationAwareCrudRestController<ServiceSupplierStaffDto, ServiceSupplierStaffSearchingDto, IServiceSupplierStaffProcessController, ServiceSupplierStaffRequest, ServiceSupplierStaffResponse> {

    @Override
    protected ServiceSupplierStaffResponse createResponseInstance() {
        return new ServiceSupplierStaffResponse();
    }
}
