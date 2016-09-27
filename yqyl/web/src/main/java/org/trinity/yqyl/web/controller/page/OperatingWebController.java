package org.trinity.yqyl.web.controller.page;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.common.exception.IException;
import org.trinity.message.MessageUtils;
import org.trinity.yqyl.common.accessright.Authorize;
import org.trinity.yqyl.common.message.dto.response.ServiceSupplierClientResponse;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.ServiceSupplierClientStatus;
import org.trinity.yqyl.web.util.Url;

@RestController
@RequestMapping("/service/operator")
public class OperatingWebController extends AbstractResourceWebController {

    @RequestMapping(value = { "/audit/receiver" }, method = RequestMethod.GET)
    @Authorize(requireAny = AccessRight.OPERATOR)
    public ModelAndView auditReceiverPage() throws IException {
        return createModelAndView("service/operator/proposedReceiver");
    }

    @RequestMapping(value = { "/audit/receiver/{id}" }, method = RequestMethod.GET)
    @Authorize(requireAny = AccessRight.OPERATOR)
    public ModelAndView auditReceiverPage(@PathVariable("id") final Long id) throws IException {
        return createModelAndView("service/operator/auditReceiver").addObject("id", id);
    }

    @RequestMapping(value = { "/audit/supplier/{id}" }, method = RequestMethod.GET)
    @Authorize(requireAny = AccessRight.OPERATOR)
    public ModelAndView auditSupplierPage(@PathVariable("id") final Long id) throws IException {
        final ServiceSupplierClientResponse response = restfulServiceUtil.callRestService(Url.SUPPLIER, String.valueOf(id), null, null,
                ServiceSupplierClientResponse.class);

        final ModelAndView modelAndView = createModelAndView("service/operator/auditSupplier");
        if (!response.getData().isEmpty()) {
            if (MessageUtils.in(response.getData().get(0).getStatus().getCode(), ServiceSupplierClientStatus.PROPOSAL)) {
                modelAndView.addObject("supplier", response.getData().get(0));
            } else {
                modelAndView.addObject("error", "Error");
            }
        }

        return modelAndView;
    }

    @RequestMapping(value = { "", "/audit/supplier" }, method = RequestMethod.GET)
    @Authorize(requireAny = AccessRight.OPERATOR)
    public ModelAndView proposedSupplierPage() throws IException {
        return createModelAndView("service/operator/proposedSupplier");
    }
}
