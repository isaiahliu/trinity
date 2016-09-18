package org.trinity.yqyl.process.controller.base;

import org.trinity.common.exception.IException;
import org.trinity.process.controller.ICrudProcessController;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientSearchingDto;

public interface IServiceSupplierClientProcessController
        extends ICrudProcessController<ServiceSupplierClientDto, ServiceSupplierClientSearchingDto> {

    void audit(Long id) throws IException;

    void proposal(ServiceSupplierClientDto serviceSupplier) throws IException;
}