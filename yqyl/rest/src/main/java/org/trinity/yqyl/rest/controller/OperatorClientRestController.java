package org.trinity.yqyl.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.accessright.Authorize;
import org.trinity.yqyl.common.message.dto.domain.OperatorClientDto;
import org.trinity.yqyl.common.message.dto.domain.OperatorClientSearchingDto;
import org.trinity.yqyl.common.message.dto.request.OperatorClientRequest;
import org.trinity.yqyl.common.message.dto.response.OperatorClientResponse;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.process.controller.base.IOperatorClientProcessController;

@RestController
@RequestMapping("/client/operator")
public class OperatorClientRestController extends
		AbstractApplicationAwareCrudRestController<OperatorClientDto, OperatorClientSearchingDto, IOperatorClientProcessController, OperatorClientRequest, OperatorClientResponse> {

	@Override
	protected OperatorClientResponse createResponseInstance() {
		return new OperatorClientResponse();
	}

	@Override
	@Authorize(requireAny = AccessRight.CLIENT_ADMINISTRATOR)
	protected void validateAdd() throws IException {
		super.validateAdd();
	}

	@Override
	@Authorize(requireAny = AccessRight.CLIENT_ADMINISTRATOR)
	protected void validateDelete() throws IException {
		super.validateDelete();
	}

	@Override
	@Authorize(requireAny = AccessRight.CLIENT_ADMINISTRATOR)
	protected void validateGetAll() throws IException {
		super.validateGetAll();
	}

	@Override
	@Authorize(requireAny = AccessRight.CLIENT_ADMINISTRATOR)
	protected void validateGetOne() throws IException {
		super.validateGetOne();
	}

	@Override
	@Authorize(requireAny = AccessRight.CLIENT_ADMINISTRATOR)
	protected void validateUpdate() throws IException {
		super.validateUpdate();
	}

}
