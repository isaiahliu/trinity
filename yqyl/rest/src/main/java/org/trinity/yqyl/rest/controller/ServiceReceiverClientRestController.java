package org.trinity.yqyl.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientSearchingDto;
import org.trinity.yqyl.common.message.dto.request.ServiceReceiverClientRequest;
import org.trinity.yqyl.common.message.dto.response.ServiceReceiverClientResponse;
import org.trinity.yqyl.process.controller.base.IServiceReceiverClientProcessController;

@RestController
@RequestMapping("/client/receiver")
public class ServiceReceiverClientRestController extends
        AbstractApplicationAwareCrudRestController<ServiceReceiverClientDto, ServiceReceiverClientSearchingDto, IServiceReceiverClientProcessController, ServiceReceiverClientRequest, ServiceReceiverClientResponse> {

    @RequestMapping(value = "/audit/{id}", method = RequestMethod.PUT)
    public ResponseEntity<DefaultResponse> auditReceiver(@PathVariable("id") final Long id) throws IException {

        getDomainProcessController().audit(id);
        return createResponseEntity(new DefaultResponse());
    }

    @RequestMapping(value = "/cancel/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DefaultResponse> cancelReceiver(@PathVariable("id") final Long id) throws IException {
        getDomainProcessController().cancel(id);
        return createResponseEntity(new DefaultResponse());
    }

    @Override
    protected ServiceReceiverClientResponse createResponseInstance() {
        return new ServiceReceiverClientResponse();
    }
}
