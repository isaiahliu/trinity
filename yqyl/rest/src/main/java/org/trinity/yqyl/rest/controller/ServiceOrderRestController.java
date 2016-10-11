package org.trinity.yqyl.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderSearchingDto;
import org.trinity.yqyl.common.message.dto.request.OrderRequest;
import org.trinity.yqyl.common.message.dto.request.ServiceOrderRequest;
import org.trinity.yqyl.common.message.dto.response.ServiceOrderResponse;
import org.trinity.yqyl.common.message.lookup.OrderStatus;
import org.trinity.yqyl.process.controller.base.IServiceOrderProcessController;

@RestController
@RequestMapping("/user/order")
public class ServiceOrderRestController extends
        AbstractApplicationAwareCrudRestController<ServiceOrderDto, ServiceOrderSearchingDto, IServiceOrderProcessController, OrderRequest, ServiceOrderResponse> {

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<DefaultResponse> editOrder(@RequestBody final ServiceOrderRequest request) throws IException {
        getDomainProcessController().editOrder(request.getData().get(0));

        return createResponseEntity(new DefaultResponse());
    }

    @RequestMapping(value = "/processed", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<ServiceOrderResponse> getAllProcessedOrders(final ServiceOrderSearchingDto request)
            throws IException {
        request.getStatus().add(OrderStatus.SETTLED.getMessageCode());
        request.getStatus().add(OrderStatus.AWAITING_APPRAISE.getMessageCode());

        return getAll(request);
    }

    @RequestMapping(value = "/processing", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<ServiceOrderResponse> getAllProcessingOrders(final ServiceOrderSearchingDto request)
            throws IException {
        request.getStatus().add(OrderStatus.IN_PROGRESS.getMessageCode());

        return getAll(request);
    }

    @RequestMapping(value = "/unprocessed", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<ServiceOrderResponse> getAllUnprocessedOrders(final ServiceOrderSearchingDto request)
            throws IException {
        request.getStatus().add(OrderStatus.AWAITING_PAYMENT.getMessageCode());
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

    @Override
    protected ServiceOrderResponse createResponseInstance() {
        return new ServiceOrderResponse();
    }

}
