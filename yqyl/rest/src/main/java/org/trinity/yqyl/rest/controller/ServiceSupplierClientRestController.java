package org.trinity.yqyl.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientSearchingDto;
import org.trinity.yqyl.common.message.dto.request.ServiceSupplierClientRequest;
import org.trinity.yqyl.common.message.dto.response.ServiceSupplierClientResponse;
import org.trinity.yqyl.process.controller.base.IServiceSupplierClientProcessController;

@RestController
@RequestMapping("/client/supplier")
public class ServiceSupplierClientRestController extends
        AbstractApplicationAwareCrudRestController<ServiceSupplierClientDto, ServiceSupplierClientSearchingDto, IServiceSupplierClientProcessController, ServiceSupplierClientRequest, ServiceSupplierClientResponse> {

    @RequestMapping(value = "/audit/{id}", method = RequestMethod.PUT)
    public ResponseEntity<DefaultResponse> auditSupplier(@PathVariable("id") final Long id) throws IException {

        getDomainProcessController().audit(id);
        return createResponseEntity(new DefaultResponse());
    }

    @RequestMapping(value = "/proposal", method = RequestMethod.PUT)
    public ResponseEntity<DefaultResponse> proposalSupplier(@RequestBody final ServiceSupplierClientRequest request) throws IException {
        final ServiceSupplierClientDto serviceSupplier = request.getData().get(0);

        getDomainProcessController().proposal(serviceSupplier);
        return createResponseEntity(new DefaultResponse());
    }

    @Override
    protected ServiceSupplierClientResponse createResponseInstance() {
        return new ServiceSupplierClientResponse();
    }
}
