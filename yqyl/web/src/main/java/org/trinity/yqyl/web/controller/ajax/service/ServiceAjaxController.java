package org.trinity.yqyl.web.controller.ajax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;
import org.trinity.rest.controller.AbstractRestController;
import org.trinity.rest.util.IRestfulServiceUtil;
import org.trinity.yqyl.common.accessright.Authorize;
import org.trinity.yqyl.common.message.dto.domain.LookupDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceCategorySearchingDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceInfoSearchingDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderSearchingDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientSearchingDto;
import org.trinity.yqyl.common.message.dto.request.ServiceCategoryRequest;
import org.trinity.yqyl.common.message.dto.request.ServiceSupplierClientRequest;
import org.trinity.yqyl.common.message.dto.response.ServiceCategoryResponse;
import org.trinity.yqyl.common.message.dto.response.ServiceInfoResponse;
import org.trinity.yqyl.common.message.dto.response.ServiceOrderResponse;
import org.trinity.yqyl.common.message.dto.response.ServiceSupplierClientResponse;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.OrderStatus;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.common.message.lookup.ServiceSupplierClientStatus;
import org.trinity.yqyl.web.util.Url;

@RestController
@RequestMapping("/ajax/service")
public class ServiceAjaxController extends AbstractRestController {
	@Autowired
	private IRestfulServiceUtil restfulServiceUtil;

	@RequestMapping(value = "/supplier/audit/{id}", method = RequestMethod.PUT)
	@Authorize(requireAny = AccessRight.OPERATOR)
	public @ResponseBody ServiceSupplierClientResponse ajaxAuditServiceSupplier(@PathVariable("id") final Long id) throws IException {
		final ServiceSupplierClientDto serviceSupplierClientDto = new ServiceSupplierClientDto();
		serviceSupplierClientDto.setId(id);
		serviceSupplierClientDto.setStatus(new LookupDto(ServiceSupplierClientStatus.ACTIVE));

		final ServiceSupplierClientRequest request = new ServiceSupplierClientRequest();
		request.getData().add(serviceSupplierClientDto);

		return restfulServiceUtil.callRestService(Url.SUPPLIER_UPDATE, null, request, null, ServiceSupplierClientResponse.class);
	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public @ResponseBody ServiceCategoryResponse ajaxGetACategory(@PathVariable("id") final Long id) throws IException {
		return restfulServiceUtil.callRestService(Url.SERVICE_CATEGORY, String.valueOf(id), null, null, ServiceCategoryResponse.class);
	}

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public @ResponseBody ServiceCategoryResponse ajaxGetAllCategories(final ServiceCategorySearchingDto request) throws IException {
		return restfulServiceUtil.callRestService(Url.SERVICE_CATEGORY, null, null, request, ServiceCategoryResponse.class);
	}

	@RequestMapping(value = "/category/parents", method = RequestMethod.GET)
	public @ResponseBody ServiceCategoryResponse ajaxGetParentCategories(
			@RequestParam(value = "status", required = false) final String status) throws IException {
		final ServiceCategoryResponse response = restfulServiceUtil.callRestService(Url.SERVICE_CATEGORY_PARENTS, null, null, null,
				ServiceCategoryResponse.class);
		if (!StringUtils.isEmpty(status)) {
			response.getData().removeIf(item -> !item.getStatus().getCode().equals(status));
		}
		return response;
	}

	@RequestMapping(value = "/supplier/{id}", method = RequestMethod.GET)
	public @ResponseBody ServiceSupplierClientResponse ajaxGetServiceSupplier(@PathVariable("id") final Long id) throws IException {
		return restfulServiceUtil.callRestService(Url.SUPPLIER, String.valueOf(id), null, null, ServiceSupplierClientResponse.class);
	}

	@RequestMapping(value = "/supplier/{id}/orders", method = RequestMethod.GET)
	public @ResponseBody ServiceOrderResponse ajaxGetServiceSupplierOrders(@PathVariable("id") final Long id,
			final ServiceOrderSearchingDto request) throws IException {
		request.setServiceSupplierClientId(id);
		request.getStatus().add(OrderStatus.SETTLED.getMessageCode());

		return restfulServiceUtil.callRestService(Url.ORDER, null, null, request, ServiceOrderResponse.class);
	}

	@RequestMapping(value = "/supplier/{id}/services", method = RequestMethod.GET)
	public @ResponseBody ServiceInfoResponse ajaxGetServiceSupplierServices(@PathVariable("id") final Long id) throws IException {
		final ServiceInfoSearchingDto request = new ServiceInfoSearchingDto();
		request.setServiceSupplierClientId(id);

		return restfulServiceUtil.callRestService(Url.SERVICE_INFO, null, null, request, ServiceInfoResponse.class);
	}

	@RequestMapping(value = "/category/children/{id}", method = RequestMethod.GET)
	public @ResponseBody ServiceCategoryResponse ajaxGetSubCategories(@PathVariable("id") final Long id,
			@RequestParam(value = "status", required = false) final String status) throws IException {
		final ServiceCategoryResponse response = restfulServiceUtil.callRestService(Url.SERVICE_CATEGORY_CHILDREN, String.valueOf(id), null,
				null, ServiceCategoryResponse.class);
		if (!StringUtils.isEmpty(status)) {
			response.getData().removeIf(item -> !item.getStatus().getCode().equals(status));
		}
		return response;
	}

	@RequestMapping(value = "/supplier", method = RequestMethod.GET)
	public @ResponseBody ServiceSupplierClientResponse ajaxServices(final ServiceSupplierClientSearchingDto request) throws IException {
		return restfulServiceUtil.callRestService(Url.SUPPLIER, null, null, request, ServiceSupplierClientResponse.class);
	}

	@RequestMapping(value = "/category", method = RequestMethod.PUT)
	public @ResponseBody DefaultResponse ajaxUpdateCategories(@RequestBody final ServiceCategoryRequest request) throws IException {
		request.getData().forEach(item -> {
			item.setDescription(null);
			if (item.getId() == null || item.getId() == 0) {
				item.setStatus(new LookupDto(RecordStatus.ACTIVE));
			}
		});

		return restfulServiceUtil.callRestService(Url.SERVICE_CATEGORY_UPDATE, null, request, null, DefaultResponse.class);
	}
}
