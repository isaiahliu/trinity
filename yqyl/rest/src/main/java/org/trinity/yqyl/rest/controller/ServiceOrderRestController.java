package org.trinity.yqyl.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.accessright.Authorize;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderSearchingDto;
import org.trinity.yqyl.common.message.dto.request.OrderRequest;
import org.trinity.yqyl.common.message.dto.request.ServiceOrderRequest;
import org.trinity.yqyl.common.message.dto.response.ServiceOrderResponse;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.OrderStatus;
import org.trinity.yqyl.process.controller.base.IServiceOrderProcessController;

@RestController
@RequestMapping("/user/order")
public class ServiceOrderRestController extends
        AbstractApplicationAwareCrudRestController<ServiceOrderDto, ServiceOrderSearchingDto, IServiceOrderProcessController, OrderRequest, ServiceOrderResponse> {

    @RequestMapping(value = "/processed", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<ServiceOrderResponse> getAllProcessedOrders(final ServiceOrderSearchingDto request)
            throws IException {
        request.getStatus().add(OrderStatus.AWAITING_APPRAISE.getMessageCode());
        request.getStatus().add(OrderStatus.SETTLED.getMessageCode());
        request.getStatus().add(OrderStatus.REQUEST_FAILED.getMessageCode());

        return getAll(request);
    }

    @RequestMapping(value = "/processing", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<ServiceOrderResponse> getAllProcessingOrders(final ServiceOrderSearchingDto request)
            throws IException {
        request.getStatus().add(OrderStatus.IN_PROGRESS.getMessageCode());
        request.getStatus().add(OrderStatus.AWAITING_PAYMENT.getMessageCode());

        return getAll(request);
    }

    @RequestMapping(value = "/unprocessed", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<ServiceOrderResponse> getAllUnprocessedOrders(final ServiceOrderSearchingDto request)
            throws IException {
        request.getStatus().add(OrderStatus.REQUEST_GRABBED.getMessageCode());
        request.getStatus().add(OrderStatus.UNPROCESSED.getMessageCode());

        return getAll(request);
    }

    @RequestMapping(value = "/proposal", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<ServiceOrderResponse> proposeOrder(@RequestBody final ServiceOrderRequest request)
            throws IException {
        final ServiceOrderResponse response = new ServiceOrderResponse();
        final ServiceOrderDto serviceOrderDto = request.getData().get(0);
        serviceOrderDto.setId(null);
        response.addData(getDomainProcessController().proposeOrder(serviceOrderDto));

        return createResponseEntity(response);
    }

    @RequestMapping(value = "/release", method = RequestMethod.POST)
    @Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
    public @ResponseBody ResponseEntity<DefaultResponse> releaseOrder(@RequestBody final ServiceOrderRequest request) throws IException {
        getDomainProcessController().releaseOrder(request.getData());

        return createResponseEntity(new DefaultResponse());
    }

    @RequestMapping(value = "/receipt", method = RequestMethod.PUT)
    @Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
    public @ResponseBody ResponseEntity<DefaultResponse> uploadReceipt(@RequestBody final ServiceOrderRequest request) throws IException {
        final DefaultResponse response = new DefaultResponse();
        final ServiceOrderDto serviceOrderDto = request.getData().get(0);
        response.addData(getDomainProcessController().uploadReceipt(serviceOrderDto));

        return createResponseEntity(response);
    }

    @Override
    protected ServiceOrderResponse createResponseInstance() {
        return new ServiceOrderResponse();
    }

}
