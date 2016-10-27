package org.trinity.yqyl.web.controller.ajax.user;

import java.io.InputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.trinity.common.accessright.ISecurityUtil;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;
import org.trinity.rest.controller.AbstractRestController;
import org.trinity.rest.util.IRestfulServiceUtil;
import org.trinity.yqyl.common.accessright.Authorize;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderAppraiseDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderSearchingDto;
import org.trinity.yqyl.common.message.dto.request.ServiceOrderAppraiseRequest;
import org.trinity.yqyl.common.message.dto.request.ServiceOrderRequest;
import org.trinity.yqyl.common.message.dto.response.ServiceOrderAppraiseResponse;
import org.trinity.yqyl.common.message.dto.response.ServiceOrderResponse;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.OrderStatus;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.web.util.Url;

@RestController
@RequestMapping("/ajax/user/order")
public class OrderAjaxController extends AbstractRestController {
    @Autowired
    private ISecurityUtil<AccessRight> securityUtil;

    @Autowired
    private IRestfulServiceUtil restfulServiceUtil;

    @RequestMapping(value = "/appraise/active/{id}", method = RequestMethod.PUT)
    public @ResponseBody DefaultResponse ajaxActiveOrderAppraise(@PathVariable("id") final Long id) throws IException {
        final ServiceOrderAppraiseDto dto = new ServiceOrderAppraiseDto();
        dto.setId(id);
        dto.setStatus(new LookupDto(RecordStatus.ACTIVE));
        final ServiceOrderAppraiseRequest request = new ServiceOrderAppraiseRequest();
        request.getData().add(dto);

        return restfulServiceUtil.callRestService(Url.APPRAISE_UPDATE, null, request, null, DefaultResponse.class);
    }

    @RequestMapping(value = "/assign", method = RequestMethod.POST)
    public @ResponseBody DefaultResponse ajaxAssignOrder(@RequestBody final ServiceOrderRequest serviceOrderRequest) throws IException {
        serviceOrderRequest.getData().forEach(item -> {
            item.setStatus(new LookupDto(OrderStatus.IN_PROGRESS));
            item.setApprovalTime(new Date());
        });

        return restfulServiceUtil.callRestService(Url.ORDER_UPDATE, null, serviceOrderRequest, null, DefaultResponse.class);
    }

    @RequestMapping(value = "/appraise/disable/{id}", method = RequestMethod.PUT)
    public @ResponseBody DefaultResponse ajaxDisableOrderAppraise(@PathVariable("id") final Long id) throws IException {
        final ServiceOrderAppraiseDto dto = new ServiceOrderAppraiseDto();
        dto.setId(id);
        dto.setStatus(new LookupDto(RecordStatus.DISABLED));
        final ServiceOrderAppraiseRequest request = new ServiceOrderAppraiseRequest();
        request.getData().add(dto);

        return restfulServiceUtil.callRestService(Url.APPRAISE_UPDATE, null, request, null, DefaultResponse.class);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public @ResponseBody DefaultResponse ajaxEditOrder(@RequestBody final ServiceOrderRequest serviceOrderRequest) throws IException {
        return restfulServiceUtil.callRestService(Url.ORDER_UPDATE, null, serviceOrderRequest, null, DefaultResponse.class);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ServiceOrderResponse> ajaxGetOrderDetail(@PathVariable("id") final Long id,
            final ServiceOrderSearchingDto request) throws IException {
        final ServiceOrderResponse response = restfulServiceUtil.callRestService(Url.ORDER, String.valueOf(id), null, request,
                ServiceOrderResponse.class);

        return createResponseEntity(response);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
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
    public @ResponseBody ServiceOrderAppraiseResponse ajaxSubmitOrder(@RequestBody final ServiceOrderAppraiseRequest request)
            throws IException {
        return restfulServiceUtil.callRestService(Url.APPRAISE_NEW, null, request, null, ServiceOrderAppraiseResponse.class);
    }

    @RequestMapping(value = "/{id}/receipt", method = RequestMethod.POST)
    @Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
    public ResponseEntity<DefaultResponse> ajaxUploadReceipt(@PathVariable("id") final Long id, final MultipartHttpServletRequest request)
            throws IException {
        if (request.getFileNames().hasNext()) {
            try {
                final ServiceOrderRequest contentRequest = new ServiceOrderRequest();

                final InputStream stream = request.getFile("IMAGE").getInputStream();
                final byte[] bytes = new byte[stream.available()];
                stream.read(bytes);

                final ServiceOrderDto dto = new ServiceOrderDto();
                dto.setId(id);
                dto.setReceiptContent(bytes);
                contentRequest.getData().add(dto);

                return createResponseEntity(
                        restfulServiceUtil.callRestService(Url.ORDER_RECEIPT, null, contentRequest, null, DefaultResponse.class));
            } catch (final Exception e) {
            }
        }

        return createResponseEntity(new DefaultResponse());
    }
}
