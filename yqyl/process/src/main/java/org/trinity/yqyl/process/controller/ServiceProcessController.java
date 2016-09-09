package org.trinity.yqyl.process.controller;

import org.trinity.yqyl.common.message.dto.domain.ServiceDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceRepository;
import org.trinity.yqyl.repository.business.entity.Service;

@org.springframework.stereotype.Service
public class ServiceProcessController
        extends AbstractAutowiredCrudProcessController<Service, ServiceDto, ServiceSearchingDto, IServiceRepository>
        implements IServiceProcessController {
    public ServiceProcessController() {
        super(Service.class, ErrorMessage.UNABLE_TO_FIND_SERVICE);
    }
}
