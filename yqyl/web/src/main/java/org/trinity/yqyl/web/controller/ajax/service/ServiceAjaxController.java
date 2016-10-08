package org.trinity.yqyl.web.controller.ajax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.exception.IException;
import org.trinity.rest.controller.AbstractRestController;
import org.trinity.rest.util.IRestfulServiceUtil;
import org.trinity.yqyl.common.message.dto.domain.ServiceInfoSearchingDto;
import org.trinity.yqyl.common.message.dto.response.ServiceCategoryResponse;
import org.trinity.yqyl.common.message.dto.response.ServiceInfoResponse;
import org.trinity.yqyl.web.util.Url;

@RestController
@RequestMapping("/ajax/service")
public class ServiceAjaxController extends AbstractRestController {
	@Autowired
	private IRestfulServiceUtil restfulServiceUtil;

	@RequestMapping(value = "/category/parents", method = RequestMethod.GET)
	public @ResponseBody ServiceCategoryResponse ajaxGetParentCategories() throws IException {
		return restfulServiceUtil.callRestService(Url.SERVICE_CATEGORY_PARENTS, null, null, null, ServiceCategoryResponse.class);
	}

	@RequestMapping(value = "/category/children/{id}", method = RequestMethod.GET)
	public @ResponseBody ServiceCategoryResponse ajaxGetSubCategories(@PathVariable("id") final Long id) throws IException {
		return restfulServiceUtil.callRestService(Url.SERVICE_CATEGORY_CHILDREN, String.valueOf(id), null, null,
				ServiceCategoryResponse.class);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ServiceInfoResponse ajaxServices(final ServiceInfoSearchingDto request) throws IException {
		return restfulServiceUtil.callRestService(Url.SERVICE_ALL, null, null, request, ServiceInfoResponse.class);
	}
}
