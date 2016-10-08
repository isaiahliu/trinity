package org.trinity.yqyl.web.controller.ajax.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.accessright.ISecurityUtil;
import org.trinity.common.exception.IException;
import org.trinity.rest.controller.AbstractRestController;
import org.trinity.rest.util.IRestfulServiceUtil;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderSearchingDto;
import org.trinity.yqyl.common.message.dto.response.OrderResponse;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.web.util.Url;

@RestController
@RequestMapping("/ajax/user/order")
public class OrderAjaxController extends AbstractRestController {
    @Autowired
    private ISecurityUtil<AccessRight> securityUtil;

    @Autowired
    private IRestfulServiceUtil restfulServiceUtil;

    @RequestMapping(value = "/processed", method = RequestMethod.GET)
    public ResponseEntity<OrderResponse> ajaxGetProcessedOrders(final ServiceOrderSearchingDto request) throws IException {
        request.setReceiverUserName(securityUtil.getCurrentToken().getUsername());

        final OrderResponse response = restfulServiceUtil.callRestService(Url.ORDER_PROCESSED, null, null, request, OrderResponse.class);

        return createResponseEntity(response);
    }

    @RequestMapping(value = "/processing", method = RequestMethod.GET)
    public ResponseEntity<OrderResponse> ajaxGetProcessingOrders(final ServiceOrderSearchingDto request) throws IException {
        request.setReceiverUserName(securityUtil.getCurrentToken().getUsername());

        final OrderResponse response = restfulServiceUtil.callRestService(Url.ORDER_PROCESSING, null, null, request, OrderResponse.class);

        return createResponseEntity(response);
    }

    @RequestMapping(value = "/unprocessed", method = RequestMethod.GET)
    public ResponseEntity<OrderResponse> ajaxGetUnprocessedOrders(final ServiceOrderSearchingDto request) throws IException {
        request.setReceiverUserName(securityUtil.getCurrentToken().getUsername());

        final OrderResponse response = restfulServiceUtil.callRestService(Url.ORDER_UNPROCESSED, null, null, request, OrderResponse.class);

        return createResponseEntity(response);
    }
}
