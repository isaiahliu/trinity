package org.trinity.yqyl.rest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientYiquanDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientYiquanSearchingDto;
import org.trinity.yqyl.common.message.dto.request.ServiceReceiverClientYiquanRequest;
import org.trinity.yqyl.common.message.dto.response.ServiceReceiverClientYiquanResponse;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.process.controller.base.IServiceReceiverClientYiquanProcessController;

@RestController
@RequestMapping("/yiquan")
public class ServiceReceiverClientYiquanRestController extends
        AbstractApplicationAwareCrudRestController<ServiceReceiverClientYiquanDto, ServiceReceiverClientYiquanSearchingDto, IServiceReceiverClientYiquanProcessController, ServiceReceiverClientYiquanRequest, ServiceReceiverClientYiquanResponse> {

    @RequestMapping(value = "/bind", method = RequestMethod.PUT)
    public ResponseEntity<DefaultResponse> ajaxBindMe(@RequestBody final ServiceReceiverClientYiquanRequest request) throws IException {
        getDomainProcessController().bindMe(request.getData().get(0));

        return createResponseEntity(new DefaultResponse());
    }

    @RequestMapping(value = "/topup", method = RequestMethod.POST)
    public ResponseEntity<DefaultResponse> ajaxTopupMe(@RequestBody final ServiceReceiverClientYiquanRequest request) throws IException {
        getDomainProcessController().topupMe(request.getData().get(0));

        return createResponseEntity(new DefaultResponse());
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<ServiceReceiverClientYiquanResponse> getMe(final ServiceReceiverClientYiquanSearchingDto request)
            throws IException {
        final ServiceReceiverClientYiquanResponse response = createResponseInstance();

        final List<ServiceReceiverClientYiquanDto> data = getDomainProcessController().getAll(request).getContent();

        if (data.isEmpty()) {
            throw getExceptionFactory().createException(ErrorMessage.NO_YIQUAN_BINDED);
        }

        response.addData(data);

        return createResponseEntity(response);
    }

    @Override
    protected ServiceReceiverClientYiquanResponse createResponseInstance() {
        return new ServiceReceiverClientYiquanResponse();
    }
}
