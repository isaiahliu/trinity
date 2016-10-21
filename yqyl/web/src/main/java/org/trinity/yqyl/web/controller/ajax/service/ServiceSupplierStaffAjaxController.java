package org.trinity.yqyl.web.controller.ajax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.exception.IException;
import org.trinity.rest.controller.AbstractRestController;
import org.trinity.rest.util.IRestfulServiceUtil;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierStaffSearchingDto;
import org.trinity.yqyl.common.message.dto.response.ServiceSupplierStaffResponse;
import org.trinity.yqyl.web.util.Url;

@RestController
@RequestMapping("/ajax/service/supplier/staff")
public class ServiceSupplierStaffAjaxController extends AbstractRestController {
    @Autowired
    private IRestfulServiceUtil restfulServiceUtil;

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public @ResponseBody ServiceSupplierStaffResponse ajaxServices(final ServiceSupplierStaffSearchingDto request) throws IException {
        request.setSearchAll(false);
        return restfulServiceUtil.callRestService(Url.STAFF, null, null, request, ServiceSupplierStaffResponse.class);
    }
}
