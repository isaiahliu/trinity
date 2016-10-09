package org.trinity.yqyl.process.controller;

import org.springframework.stereotype.Service;
import org.trinity.yqyl.common.message.dto.domain.ServiceSubOrderDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSubOrderSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceSubOrderProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceSubOrderRepository;
import org.trinity.yqyl.repository.business.entity.ServiceSubOrder;

@Service
public class ServiceSubOrderProcessController
      extends AbstractAutowiredCrudProcessController<ServiceSubOrder, ServiceSubOrderDto, ServiceSubOrderSearchingDto, IServiceSubOrderRepository>
      implements IServiceSubOrderProcessController {
  public ServiceSubOrderProcessController() {
      super(ServiceSubOrder.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_SUB_ORDER);
  }
}

