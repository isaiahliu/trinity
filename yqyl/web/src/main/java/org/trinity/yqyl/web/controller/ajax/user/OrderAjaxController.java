package org.trinity.yqyl.web.controller.ajax.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.accessright.ISecurityUtil;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;
import org.trinity.rest.controller.AbstractRestController;
import org.trinity.rest.util.IRestfulServiceUtil;
import org.trinity.yqyl.common.accessright.Authorize;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderSearchingDto;
import org.trinity.yqyl.common.message.dto.request.ServiceOrderAppraiseRequest;
import org.trinity.yqyl.common.message.dto.request.ServiceOrderRequest;
import org.trinity.yqyl.common.message.dto.response.ServiceOrderResponse;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.web.util.Url;

@RestController
@RequestMapping("/ajax/user/order")
public class OrderAjaxController extends AbstractRestController {
    @Autowired
    private ISecurityUtil<AccessRight> securityUtil;

    @Autowired
    private IRestfulServiceUtil restfulServiceUtil;

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public @ResponseBody DefaultResponse ajaxEditOrder(@RequestBody final ServiceOrderRequest serviceOrderRequest) throws IException {
        return restfulServiceUtil.callRestService(Url.ORDER_UPDATE, null, serviceOrderRequest, null, DefaultResponse.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ServiceOrderResponse> ajaxGetOrderDetail(@PathVariable("id") final Long id) throws IException {
        final ServiceOrderResponse response = restfulServiceUtil.callRestService(Url.ORDER, String.valueOf(id), null, null,
                ServiceOrderResponse.class);

        return createResponseEntity(response);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Authorize(requireAny = AccessRight.OPERATOR)
    public ResponseEntity<ServiceOrderResponse> ajaxGetOrders(final ServiceOrderSearchingDto request) throws IException {
        final ServiceOrderResponse response = restfulServiceUtil.callRestService(Url.ORDER, null, null, request,
                ServiceOrderResponse.class);

        return createResponseEntity(response);
    }

    @RequestMapping(value = "/processed", method = RequestMethod.GET)
    public ResponseEntity<ServiceOrderResponse> ajaxGetProcessedOrders(final ServiceOrderSearchingDto request) throws IException {
        request.setReceiverUserName(securityUtil.getCurrentToken().getUsername());

        final ServiceOrderResponse response = restfulServiceUtil.callRestService(Url.ORDER_PROCESSED, null, null, request,
                ServiceOrderResponse.class);

        return createResponseEntity(response);
    }

    @RequestMapping(value = "/processing", method = RequestMethod.GET)
    public ResponseEntity<ServiceOrderResponse> ajaxGetProcessingOrders(final ServiceOrderSearchingDto request) throws IException {
        request.setReceiverUserName(securityUtil.getCurrentToken().getUsername());

        final ServiceOrderResponse response = restfulServiceUtil.callRestService(Url.ORDER_PROCESSING, null, null, request,
                ServiceOrderResponse.class);

        return createResponseEntity(response);
    }

    @RequestMapping(value = "/unprocessed", method = RequestMethod.GET)
    public ResponseEntity<ServiceOrderResponse> ajaxGetUnprocessedOrders(final ServiceOrderSearchingDto request) throws IException {
        request.setReceiverUserName(securityUtil.getCurrentToken().getUsername());

        final ServiceOrderResponse response = restfulServiceUtil.callRestService(Url.ORDER_UNPROCESSED, null, null, request,
                ServiceOrderResponse.class);

        return createResponseEntity(response);
    }

    @RequestMapping(value = "/proposal", method = RequestMethod.POST)
    public @ResponseBody ServiceOrderResponse ajaxProposeOrder(@RequestBody final ServiceOrderRequest serviceOrderRequest)
            throws IException {
        return restfulServiceUtil.callRestService(Url.ORDER_PROPOSAL, null, serviceOrderRequest, null, ServiceOrderResponse.class);
    }

    @RequestMapping(value = "/appraise", method = RequestMethod.POST)
    public @ResponseBody DefaultResponse ajaxSubmitOrder(@RequestBody final ServiceOrderAppraiseRequest request) throws IException {
        return restfulServiceUtil.callRestService(Url.APPRAISE_NEW, null, request, null, DefaultResponse.class);
    }
}
