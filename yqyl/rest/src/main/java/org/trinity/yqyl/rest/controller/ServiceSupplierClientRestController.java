package org.trinity.yqyl.rest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<ServiceSupplierClientResponse> getMe(final ServiceSupplierClientSearchingDto request)
            throws IException {
        final ServiceSupplierClientResponse response = createResponseInstance();

        final List<ServiceSupplierClientDto> data = getDomainProcessController().getMe(request);

        response.addData(data);

        return createResponseEntity(response);
    }

    @Override
    protected ServiceSupplierClientResponse createResponseInstance() {
        return new ServiceSupplierClientResponse();
    }

}
