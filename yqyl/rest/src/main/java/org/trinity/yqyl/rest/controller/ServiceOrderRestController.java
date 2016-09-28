package org.trinity.yqyl.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderSearchingDto;
import org.trinity.yqyl.common.message.dto.request.OrderRequest;
import org.trinity.yqyl.common.message.dto.response.OrderResponse;
import org.trinity.yqyl.common.message.lookup.OrderStatus;
import org.trinity.yqyl.process.controller.base.IOrderProcessController;

@RestController
@RequestMapping("/user/order")
public class ServiceOrderRestController extends
		AbstractApplicationAwareCrudRestController<ServiceOrderDto, ServiceOrderSearchingDto, IOrderProcessController, OrderRequest, OrderResponse> {

	@RequestMapping(value = "/processed", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<OrderResponse> getAllProcessedOrders(final ServiceOrderSearchingDto request) throws IException {
		request.getStatus().add(OrderStatus.SETTLED.getMessageCode());

		return getAll(request);
	}

	@RequestMapping(value = "/processing", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<OrderResponse> getAllProcessingOrders(final ServiceOrderSearchingDto request) throws IException {
		request.getStatus().add(OrderStatus.IN_PROGRESS.getMessageCode());
		request.getStatus().add(OrderStatus.AWAITING_PAYMENT.getMessageCode());

		return getAll(request);
	}

	@RequestMapping(value = "/unprocessed", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<OrderResponse> getAllUnprocessedOrders(final ServiceOrderSearchingDto request) throws IException {
		request.getStatus().add(OrderStatus.UNPROCESSED.getMessageCode());

		return getAll(request);
	}

	@Override
	protected OrderResponse createResponseInstance() {
		return new OrderResponse();
	}

}
