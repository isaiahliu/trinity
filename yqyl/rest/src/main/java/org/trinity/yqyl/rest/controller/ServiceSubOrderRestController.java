package org.trinity.yqyl.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.yqyl.common.message.dto.domain.ServiceSubOrderDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSubOrderSearchingDto;
import org.trinity.yqyl.common.message.dto.request.ServiceSubOrderRequest;
import org.trinity.yqyl.common.message.dto.response.ServiceSubOrderResponse;
import org.trinity.yqyl.process.controller.base.IServiceSubOrderProcessController;

@RestController
@RequestMapping("/*")
public class ServiceSubOrderRestController extends
      AbstractApplicationAwareCrudRestController<ServiceSubOrderDto, ServiceSubOrderSearchingDto, IServiceSubOrderProcessController, ServiceSubOrderRequest, ServiceSubOrderResponse> {

  @Override
  protected ServiceSubOrderResponse createResponseInstance() {
      return new ServiceSubOrderResponse();
  }
}

