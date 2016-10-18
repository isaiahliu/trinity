package org.trinity.yqyl.process.controller;

import org.springframework.stereotype.Service;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderAppraiseDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderAppraiseSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceOrderAppraiseProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceOrderAppraiseRepository;
import org.trinity.yqyl.repository.business.entity.ServiceOrderAppraise;

@Service
public class ServiceOrderAppraiseProcessController extends
        AbstractAutowiredCrudProcessController<ServiceOrderAppraise, ServiceOrderAppraiseDto, ServiceOrderAppraiseSearchingDto, IServiceOrderAppraiseRepository>
        implements IServiceOrderAppraiseProcessController {
    public ServiceOrderAppraiseProcessController() {
        super(ServiceOrderAppraise.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_ORDER_APPRAISE);
    }
}
