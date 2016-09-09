package org.trinity.yqyl.process.controller.base;

import org.trinity.process.controller.ICrudProcessController;
import org.trinity.yqyl.common.message.dto.domain.OrderDto;
import org.trinity.yqyl.common.message.dto.domain.OrderSearchingDto;

public interface IOrderProcessController extends ICrudProcessController<OrderDto, OrderSearchingDto> {
}

