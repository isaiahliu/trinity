package org.trinity.yqyl.process.controller.base;

import java.util.List;

import org.trinity.common.exception.IException;
import org.trinity.process.controller.ICrudProcessController;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderSearchingDto;

public interface IServiceOrderProcessController extends ICrudProcessController<ServiceOrderDto, ServiceOrderSearchingDto> {
    ServiceOrderDto proposeOrder(ServiceOrderDto serviceOrderDto) throws IException;

    void releaseOrder(List<ServiceOrderDto> data) throws IException;

    String uploadReceipt(ServiceOrderDto serviceOrderDto) throws IException;
}
