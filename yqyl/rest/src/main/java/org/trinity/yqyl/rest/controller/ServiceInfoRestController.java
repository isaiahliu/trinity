package org.trinity.yqyl.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.yqyl.common.message.dto.domain.ServiceInfoDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceInfoSearchingDto;
import org.trinity.yqyl.common.message.dto.request.ServiceRequest;
import org.trinity.yqyl.common.message.dto.response.ServiceResponse;
import org.trinity.yqyl.process.controller.base.IServiceProcessController;

@RestController
@RequestMapping("/service/info")
public class ServiceInfoRestController extends
		AbstractApplicationAwareCrudRestController<ServiceInfoDto, ServiceInfoSearchingDto, IServiceProcessController, ServiceRequest, ServiceResponse> {

	@Override
	protected ServiceResponse createResponseInstance() {
		return new ServiceResponse();
	}
}
