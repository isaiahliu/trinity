package org.trinity.yqyl.process.controller;

import org.springframework.stereotype.Service;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientHealthIndicatorDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientHealthIndicatorSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceReceiverClientHealthIndicatorProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceReceiverClientHealthIndicatorRepository;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientHealthIndicator;

@Service
public class ServiceReceiverClientHealthIndicatorProcessController
      extends AbstractAutowiredCrudProcessController<ServiceReceiverClientHealthIndicator, ServiceReceiverClientHealthIndicatorDto, ServiceReceiverClientHealthIndicatorSearchingDto, IServiceReceiverClientHealthIndicatorRepository>
      implements IServiceReceiverClientHealthIndicatorProcessController {
  public ServiceReceiverClientHealthIndicatorProcessController() {
      super(ServiceReceiverClientHealthIndicator.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_RECEIVER_CLIENT_HEALTH_INDICATOR);
  }
}

