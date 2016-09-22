package org.trinity.yqyl.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientHealthIndicatorDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientHealthIndicatorSearchingDto;
import org.trinity.yqyl.common.message.dto.request.ServiceReceiverClientHealthIndicatorRequest;
import org.trinity.yqyl.common.message.dto.response.ServiceReceiverClientHealthIndicatorResponse;
import org.trinity.yqyl.process.controller.base.IServiceReceiverClientHealthIndicatorProcessController;

@RestController
@RequestMapping("/client/receiver/healthindicator")
public class ServiceReceiverClientHealthIndicatorRestController extends
		AbstractApplicationAwareCrudRestController<ServiceReceiverClientHealthIndicatorDto, ServiceReceiverClientHealthIndicatorSearchingDto, IServiceReceiverClientHealthIndicatorProcessController, ServiceReceiverClientHealthIndicatorRequest, ServiceReceiverClientHealthIndicatorResponse> {

	@Override
	protected ServiceReceiverClientHealthIndicatorResponse createResponseInstance() {
		return new ServiceReceiverClientHealthIndicatorResponse();
	}
}
