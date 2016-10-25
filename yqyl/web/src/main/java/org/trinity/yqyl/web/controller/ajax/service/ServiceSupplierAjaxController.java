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
import org.trinity.yqyl.common.accessright.Authorize;
import org.trinity.yqyl.common.message.dto.domain.LookupDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceInfoSearchingDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderSearchingDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientSearchingDto;
import org.trinity.yqyl.common.message.dto.request.ServiceSupplierClientRequest;
import org.trinity.yqyl.common.message.dto.response.ServiceInfoResponse;
import org.trinity.yqyl.common.message.dto.response.ServiceOrderResponse;
import org.trinity.yqyl.common.message.dto.response.ServiceSupplierClientResponse;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.OrderStatus;
import org.trinity.yqyl.common.message.lookup.ServiceStatus;
import org.trinity.yqyl.common.message.lookup.ServiceSupplierClientStatus;
import org.trinity.yqyl.web.util.Url;

@RestController
@RequestMapping("/ajax/service/supplier")
public class ServiceSupplierAjaxController extends AbstractRestController {
    @Autowired
    private IRestfulServiceUtil restfulServiceUtil;

    @RequestMapping(value = "/audit/{id}", method = RequestMethod.PUT)
    @Authorize(requireAny = AccessRight.ADMINISTRATOR)
    public @ResponseBody ServiceSupplierClientResponse ajaxAuditServiceSupplier(@PathVariable("id") final Long id) throws IException {
        final ServiceSupplierClientDto serviceSupplierClientDto = new ServiceSupplierClientDto();
        serviceSupplierClientDto.setId(id);
        serviceSupplierClientDto.setStatus(new LookupDto(ServiceSupplierClientStatus.ACTIVE));

        final ServiceSupplierClientRequest request = new ServiceSupplierClientRequest();
        request.getData().add(serviceSupplierClientDto);

        return restfulServiceUtil.callRestService(Url.SUPPLIER_UPDATE, null, request, null, ServiceSupplierClientResponse.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody ServiceSupplierClientResponse ajaxGetServiceSupplier(@PathVariable("id") final Long id) throws IException {
        return restfulServiceUtil.callRestService(Url.SUPPLIER, String.valueOf(id), null, null, ServiceSupplierClientResponse.class);
    }

    @RequestMapping(value = "/{id}/orders", method = RequestMethod.GET)
    public @ResponseBody ServiceOrderResponse ajaxGetServiceSupplierOrders(@PathVariable("id") final Long id,
            final ServiceOrderSearchingDto request) throws IException {
        request.setServiceSupplierClientId(id);
        request.getStatus().add(OrderStatus.SETTLED.getMessageCode());

        return restfulServiceUtil.callRestService(Url.ORDER, null, null, request, ServiceOrderResponse.class);
    }

    @RequestMapping(value = "/{id}/services", method = RequestMethod.GET)
    public @ResponseBody ServiceInfoResponse ajaxGetServiceSupplierServices(@PathVariable("id") final Long id) throws IException {
        final ServiceInfoSearchingDto request = new ServiceInfoSearchingDto();
        request.setServiceSupplierClientId(id);
        request.getStatus().add(ServiceStatus.ACTIVE.getMessageCode());

        return restfulServiceUtil.callRestService(Url.SERVICE_INFO, null, null, request, ServiceInfoResponse.class);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody ServiceSupplierClientResponse ajaxServices(final ServiceSupplierClientSearchingDto request) throws IException {
        return restfulServiceUtil.callRestService(Url.SUPPLIER, null, null, request, ServiceSupplierClientResponse.class);
    }
}
