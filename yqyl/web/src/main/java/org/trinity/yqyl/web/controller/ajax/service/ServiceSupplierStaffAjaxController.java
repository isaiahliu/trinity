package org.trinity.yqyl.web.controller.ajax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;
import org.trinity.rest.controller.AbstractRestController;
import org.trinity.rest.util.IRestfulServiceUtil;
import org.trinity.yqyl.common.accessright.Authorize;
import org.trinity.yqyl.common.message.dto.domain.LookupDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierStaffDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierStaffSearchingDto;
import org.trinity.yqyl.common.message.dto.request.ServiceSupplierStaffRequest;
import org.trinity.yqyl.common.message.dto.response.ServiceSupplierStaffResponse;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.StaffStatus;
import org.trinity.yqyl.web.util.Url;

@RestController
@RequestMapping("/ajax/service/supplier/staff")
public class ServiceSupplierStaffAjaxController extends AbstractRestController {
    @Autowired
    private IRestfulServiceUtil restfulServiceUtil;

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    @Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
    public @ResponseBody ServiceSupplierStaffResponse ajaxGetStaffs(final ServiceSupplierStaffSearchingDto request) throws IException {
        request.setSearchAll(false);

        return restfulServiceUtil.callRestService(Url.STAFF, null, null, request, ServiceSupplierStaffResponse.class);
    }

    @RequestMapping(value = "/away/{id}", method = RequestMethod.DELETE)
    @Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
    public @ResponseBody DefaultResponse ajaxStaffAway(@PathVariable("id") final Long id) throws IException {
        final ServiceSupplierStaffDto serviceSupplierStaffDto = new ServiceSupplierStaffDto();
        serviceSupplierStaffDto.setId(id);
        serviceSupplierStaffDto.setStatus(new LookupDto(StaffStatus.FIRED));

        final ServiceSupplierStaffRequest request = new ServiceSupplierStaffRequest();
        request.getData().add(serviceSupplierStaffDto);

        return restfulServiceUtil.callRestService(Url.STAFF_UPDATE, null, request, null, DefaultResponse.class);
    }

    @RequestMapping(value = "/return/{id}", method = RequestMethod.POST)
    @Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
    public @ResponseBody DefaultResponse ajaxStaffReturn(@PathVariable("id") final Long id) throws IException {
        final ServiceSupplierStaffDto serviceSupplierStaffDto = new ServiceSupplierStaffDto();
        serviceSupplierStaffDto.setId(id);
        serviceSupplierStaffDto.setStatus(new LookupDto(StaffStatus.ACTIVE));

        final ServiceSupplierStaffRequest request = new ServiceSupplierStaffRequest();
        request.getData().add(serviceSupplierStaffDto);

        return restfulServiceUtil.callRestService(Url.STAFF_UPDATE, null, request, null, DefaultResponse.class);
    }
}
