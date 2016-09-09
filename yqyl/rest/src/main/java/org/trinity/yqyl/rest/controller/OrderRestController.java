package org.trinity.yqyl.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.yqyl.common.message.dto.domain.OrderDto;
import org.trinity.yqyl.common.message.dto.domain.OrderSearchingDto;
import org.trinity.yqyl.common.message.dto.request.OrderRequest;
import org.trinity.yqyl.common.message.dto.response.OrderResponse;
import org.trinity.yqyl.process.controller.base.IOrderProcessController;

@RestController
@RequestMapping("/transaction/order")
public class OrderRestController extends
        AbstractApplicationAwareCrudRestController<OrderDto, OrderSearchingDto, IOrderProcessController, OrderRequest, OrderResponse> {

    @Override
    protected OrderResponse createResponseInstance() {
        return new OrderResponse();
    }
}
