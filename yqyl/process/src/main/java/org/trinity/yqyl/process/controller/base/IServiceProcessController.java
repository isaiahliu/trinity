package org.trinity.yqyl.process.controller.base;

import org.trinity.process.controller.ICrudProcessController;
import org.trinity.yqyl.common.message.dto.domain.ServiceDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSearchingDto;

public interface IServiceProcessController extends ICrudProcessController<ServiceDto, ServiceSearchingDto> {
}
