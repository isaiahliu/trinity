package org.trinity.yqyl.web.controller.ajax.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.dto.IResponse;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;
import org.trinity.rest.controller.AbstractRestController;
import org.trinity.rest.util.IRestfulServiceUtil;
import org.trinity.yqyl.common.message.dto.request.ServiceReceiverClientRequest;
import org.trinity.yqyl.common.message.dto.response.ServiceReceiverClientHealthIndicatorResponse;
import org.trinity.yqyl.common.message.dto.response.ServiceReceiverClientResponse;
import org.trinity.yqyl.common.message.lookup.ServiceReceiverClientStatus;
import org.trinity.yqyl.web.util.Url;

@RestController
@RequestMapping("/ajax/user/receiver")
public class ServiceReceiverClientAjaxController extends AbstractRestController {
    @Autowired
    private IRestfulServiceUtil restfulServiceUtil;

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<IResponse> ajaxAddUpdateServiceReceiver(@RequestBody final ServiceReceiverClientRequest request)
            throws IException {
        if (request.getData().isEmpty()) {
            return createResponseEntity(new DefaultResponse());
        }

        request.getData().forEach(item -> {
            if (item.getId() == null) {
                item.setStatus(new LookupDto(ServiceReceiverClientStatus.PROPOSAL.getMessageCode(), ""));
            }
        });

        final DefaultResponse response = restfulServiceUtil.callRestService(Url.RECEIVER_UPDATE, null, request, null,
                DefaultResponse.class);
        return createResponseEntity(response);
    }

    @RequestMapping(value = "/{entityId}", method = RequestMethod.DELETE)
    public ResponseEntity<DefaultResponse> ajaxDeleteUserinfo(@PathVariable("entityId") final Long entityId) throws IException {
        final DefaultResponse response = restfulServiceUtil.callRestService(Url.RECEIVER_CANCEL_PROPOSAL, String.valueOf(entityId), null,
                null, DefaultResponse.class);

        return createResponseEntity(response);
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public ResponseEntity<ServiceReceiverClientResponse> ajaxGetServiceReceiverClientForMe() throws IException {
        final ServiceReceiverClientResponse response = restfulServiceUtil.callRestService(Url.RECEIVER_ME, null, null, null,
                ServiceReceiverClientResponse.class);

        return createResponseEntity(response);
    }

    @RequestMapping(value = "/healthindicator/{entityId}", method = RequestMethod.GET)
    public ResponseEntity<ServiceReceiverClientHealthIndicatorResponse> ajaxGetServiceReceiverClientHealthIndicator(
            @PathVariable("entityId") final Long entityId) throws IException {
        final ServiceReceiverClientHealthIndicatorResponse response = restfulServiceUtil.callRestService(Url.HEALTH_INDICATOR,
                String.valueOf(entityId), null, null, ServiceReceiverClientHealthIndicatorResponse.class);

        return createResponseEntity(response);
    }

    @RequestMapping(value = "/{entityId}", method = RequestMethod.GET)
    public ResponseEntity<ServiceReceiverClientResponse> ajaxGetUserinfo(@PathVariable("entityId") final Long entityId) throws IException {
        final ServiceReceiverClientResponse response = restfulServiceUtil.callRestService(Url.RECEIVER, String.valueOf(entityId), null,
                null, ServiceReceiverClientResponse.class);

        return createResponseEntity(response);
    }
}
