package org.trinity.yqyl.process.controller.base;

import java.util.List;

import org.trinity.common.exception.IException;
import org.trinity.process.controller.ICrudProcessController;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientSearchingDto;

public interface IServiceSupplierClientProcessController
        extends ICrudProcessController<ServiceSupplierClientDto, ServiceSupplierClientSearchingDto> {

    List<ServiceSupplierClientDto> getMe(ServiceSupplierClientSearchingDto dto) throws IException;
}
