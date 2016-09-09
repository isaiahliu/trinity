package org.trinity.yqyl.web.controller.ajax;

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
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientSearchingDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientSearchingDto;
import org.trinity.yqyl.common.message.dto.response.ServiceReceiverClientResponse;
import org.trinity.yqyl.common.message.dto.response.ServiceSupplierClientResponse;
import org.trinity.yqyl.common.message.lookup.ServiceReceiverClientStatus;
import org.trinity.yqyl.common.message.lookup.ServiceSupplierClientStatus;
import org.trinity.yqyl.web.util.Url;

@RestController
@RequestMapping("/ajax/service/operator")
public class OperatorAjaxController extends AbstractRestController {
    @Autowired
    private IRestfulServiceUtil restfulServiceUtil;

    @RequestMapping(value = "/audit/receiver/{id}", method = RequestMethod.PUT)
    public @ResponseBody DefaultResponse ajaxAuditReceiver(@PathVariable("id") final Long id) throws IException {
        return restfulServiceUtil.callRestService(Url.RECEIVER_AUDIT, String.valueOf(id), null, null, DefaultResponse.class);
    }

    @RequestMapping(value = "/audit/supplier/{id}", method = RequestMethod.PUT)
    public @ResponseBody DefaultResponse ajaxAuditSupplier(@PathVariable("id") final Long id) throws IException {
        return restfulServiceUtil.callRestService(Url.SUPPLIER_AUDIT, String.valueOf(id), null, null, DefaultResponse.class);
    }

    @RequestMapping(value = "/proposalReceiver", method = RequestMethod.GET)
    public @ResponseBody ServiceReceiverClientResponse ajaxProposalReceiver(final ServiceReceiverClientSearchingDto dto) throws IException {
        dto.setStatus(ServiceReceiverClientStatus.PROPOSAL.getMessageCode());

        return restfulServiceUtil.callRestService(Url.RECEIVER, null, null, dto, ServiceReceiverClientResponse.class);
    }

    @RequestMapping(value = "/proposalSupplier", method = RequestMethod.GET)
    public @ResponseBody ServiceSupplierClientResponse ajaxProposalSupplier(final ServiceSupplierClientSearchingDto dto) throws IException {
        dto.setStatus(ServiceSupplierClientStatus.PROPOSAL.getMessageCode());

        return restfulServiceUtil.callRestService(Url.SUPPLIER, null, null, dto, ServiceSupplierClientResponse.class);
    }
}
