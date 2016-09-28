package org.trinity.yqyl.process.controller.base;

import org.trinity.process.controller.ICrudProcessController;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderSearchingDto;

public interface IOrderProcessController extends ICrudProcessController<ServiceOrderDto, ServiceOrderSearchingDto> {
}

