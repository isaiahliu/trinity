package org.trinity.yqyl.process.controller;

import org.springframework.stereotype.Service;
import org.trinity.yqyl.common.message.dto.domain.ServiceInfoDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceInfoRepository;
import org.trinity.yqyl.repository.business.entity.ServiceInfo;

@Service
public class ServiceInfoProcessController
        extends AbstractAutowiredCrudProcessController<ServiceInfo, ServiceInfoDto, ServiceSearchingDto, IServiceInfoRepository>
        implements IServiceProcessController {
    public ServiceInfoProcessController() {
        super(ServiceInfo.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_INFO);
    }
}
