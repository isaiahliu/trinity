package org.trinity.yqyl.web.controller.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;
import org.trinity.rest.controller.AbstractRestController;
import org.trinity.rest.util.IRestfulServiceUtil;
import org.trinity.yqyl.common.accessright.Authorize;
import org.trinity.yqyl.common.message.dto.domain.OperatorClientSearchingDto;
import org.trinity.yqyl.common.message.dto.request.OperatorClientRequest;
import org.trinity.yqyl.common.message.dto.response.OperatorClientResponse;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.web.util.Url;

@RestController
@RequestMapping("/ajax/service/admin")
public class AdminAjaxController extends AbstractRestController {
	@Autowired
	private IRestfulServiceUtil restfulServiceUtil;

	@RequestMapping(value = "/operator", method = RequestMethod.GET)
	@Authorize(requireAny = AccessRight.CLIENT_ADMINISTRATOR)
	public @ResponseBody OperatorClientResponse ajaxAllOperator(final OperatorClientSearchingDto dto) throws IException {
		return restfulServiceUtil.callRestService(Url.OPERATOR, null, null, dto, OperatorClientResponse.class);
	}

	@RequestMapping(value = "/operator", method = RequestMethod.PUT)
	@Authorize(requireAny = AccessRight.CLIENT_ADMINISTRATOR)
	public @ResponseBody DefaultResponse ajaxAuditReceiver(@RequestBody final OperatorClientRequest request) throws IException {
		return restfulServiceUtil.callRestService(Url.OPERATOR_UPDATE, null, request, null, DefaultResponse.class);
	}

	@RequestMapping(value = "/operator/{id}", method = RequestMethod.GET)
	@Authorize(requireAny = AccessRight.CLIENT_ADMINISTRATOR)
	public @ResponseBody OperatorClientResponse ajaxOperator(@PathVariable("id") final Long id) throws IException {
		return restfulServiceUtil.callRestService(Url.OPERATOR, String.valueOf(id), null, null, OperatorClientResponse.class);
	}
}
