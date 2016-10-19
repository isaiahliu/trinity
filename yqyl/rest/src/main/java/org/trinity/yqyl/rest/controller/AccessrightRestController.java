package org.trinity.yqyl.rest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.accessright.Authorize;
import org.trinity.yqyl.common.message.dto.domain.AccessrightDto;
import org.trinity.yqyl.common.message.dto.domain.AccessrightSearchingDto;
import org.trinity.yqyl.common.message.dto.request.AccessrightRequest;
import org.trinity.yqyl.common.message.dto.response.AccessrightResponse;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.process.controller.base.IAccessrightProcessController;

/**
 * @author isaia
 *
 */
@RestController
@RequestMapping("/security/accessright")
public class AccessrightRestController extends
		AbstractApplicationAwareCrudRestController<AccessrightDto, AccessrightSearchingDto, IAccessrightProcessController, AccessrightRequest, AccessrightResponse> {

	@Override
	public ResponseEntity<AccessrightResponse> getAll(final AccessrightSearchingDto request) throws IException {
		final AccessrightResponse response = createResponseInstance();

		final List<AccessrightDto> data = getDomainProcessController().getAccessRightTree();

		response.addData(data);

		return createResponseEntity(response);
	}

	@RequestMapping(value = "/refresh", method = RequestMethod.PUT)
	@Authorize(requireAny = AccessRight.ADMINISTRATOR)
	public @ResponseBody ResponseEntity<DefaultResponse> updateAll() throws IException {
		final DefaultResponse response = new DefaultResponse();

		getDomainProcessController().refreshAll();

		return createResponseEntity(response);
	}

	@Override
	protected AccessrightResponse createResponseInstance() {
		return new AccessrightResponse();
	}

	@Override
	@Authorize(enabled = false)
	protected void validateAdd() throws IException {
		super.validateAdd();
	}

	@Override
	@Authorize(enabled = false)
	protected void validateDelete() throws IException {
		super.validateDelete();
	}

	@Override
	@Authorize(requireAny = AccessRight.ADMINISTRATOR)
	protected void validateGetAll() throws IException {
		super.validateGetAll();
	}

	@Override
	@Authorize(requireAny = AccessRight.ADMINISTRATOR)
	protected void validateGetOne() throws IException {
		super.validateGetOne();
	}

	@Override
	@Authorize(requireAny = AccessRight.ADMINISTRATOR)
	protected void validateUpdate() throws IException {
		super.validateUpdate();
	}
}
