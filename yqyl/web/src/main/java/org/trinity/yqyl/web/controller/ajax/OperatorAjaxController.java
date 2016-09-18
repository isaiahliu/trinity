package org.trinity.yqyl.web.controller.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.dto.IResponse;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;
import org.trinity.message.MessageUtils;
import org.trinity.rest.controller.AbstractRestController;
import org.trinity.rest.util.IRestfulServiceUtil;
import org.trinity.yqyl.common.accessright.Authorize;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientSearchingDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientSearchingDto;
import org.trinity.yqyl.common.message.dto.response.ServiceReceiverClientResponse;
import org.trinity.yqyl.common.message.dto.response.ServiceSupplierClientResponse;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.ServiceReceiverClientStatus;
import org.trinity.yqyl.common.message.lookup.ServiceSupplierClientStatus;
import org.trinity.yqyl.web.util.Url;

@RestController
@RequestMapping("/ajax/service/operator")
public class OperatorAjaxController extends AbstractRestController {
	@Autowired
	private IRestfulServiceUtil restfulServiceUtil;

	@RequestMapping(value = "/audit/receiver/{id}", method = RequestMethod.PUT)
	@Authorize(requireAny = AccessRight.OPERATOR)
	public @ResponseBody DefaultResponse ajaxAuditReceiver(@PathVariable("id") final Long id) throws IException {
		return restfulServiceUtil.callRestService(Url.RECEIVER_AUDIT, String.valueOf(id), null, null, DefaultResponse.class);
	}

	@RequestMapping(value = "/audit/supplier/{id}", method = RequestMethod.PUT)
	@Authorize(requireAny = AccessRight.OPERATOR)
	public @ResponseBody DefaultResponse ajaxAuditSupplier(@PathVariable("id") final Long id) throws IException {
		return restfulServiceUtil.callRestService(Url.SUPPLIER_AUDIT, String.valueOf(id), null, null, DefaultResponse.class);
	}

	@RequestMapping(value = "/audit/receiver/{id}", method = RequestMethod.GET)
	@Authorize(requireAny = AccessRight.OPERATOR)
	public @ResponseBody IResponse ajaxGetReceiver(@PathVariable("id") final Long id) throws IException {
		final ServiceReceiverClientResponse response = restfulServiceUtil.callRestService(Url.RECEIVER, String.valueOf(id), null, null,
				ServiceReceiverClientResponse.class);

		if (response.getData().isEmpty()
				|| !MessageUtils.in(response.getData().get(0).getStatus().getCode(), ServiceReceiverClientStatus.PROPOSAL)) {
			response.getData().clear();

			response.addError("error", "Error");
		}

		return response;
	}

	@RequestMapping(value = "/proposalReceiver", method = RequestMethod.GET)
	@Authorize(requireAny = AccessRight.OPERATOR)
	public @ResponseBody ServiceReceiverClientResponse ajaxProposalReceiver(final ServiceReceiverClientSearchingDto dto) throws IException {
		dto.setStatus(ServiceReceiverClientStatus.PROPOSAL.getMessageCode());

		return restfulServiceUtil.callRestService(Url.RECEIVER, null, null, dto, ServiceReceiverClientResponse.class);
	}

	@RequestMapping(value = "/proposalSupplier", method = RequestMethod.GET)
	@Authorize(requireAny = AccessRight.OPERATOR)
	public @ResponseBody ServiceSupplierClientResponse ajaxProposalSupplier(final ServiceSupplierClientSearchingDto dto) throws IException {
		dto.setStatus(ServiceSupplierClientStatus.PROPOSAL.getMessageCode());

		return restfulServiceUtil.callRestService(Url.SUPPLIER, null, null, dto, ServiceSupplierClientResponse.class);
	}
}
