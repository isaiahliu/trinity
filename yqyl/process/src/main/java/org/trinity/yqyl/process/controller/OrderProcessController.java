package org.trinity.yqyl.process.controller;

import org.springframework.stereotype.Service;
import org.trinity.yqyl.common.message.dto.domain.OrderDto;
import org.trinity.yqyl.common.message.dto.domain.OrderSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IOrderProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IOrderRepository;
import org.trinity.yqyl.repository.business.entity.Order;

@Service
public class OrderProcessController extends AbstractAutowiredCrudProcessController<Order, OrderDto, OrderSearchingDto, IOrderRepository>
        implements IOrderProcessController {
    public OrderProcessController() {
        super(Order.class, ErrorMessage.UNABLE_TO_FIND_ORDER);
    }
}
