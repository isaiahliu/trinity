package org.trinity.yqyl.web.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.common.exception.IException;
import org.trinity.message.MessageUtils;
import org.trinity.rest.util.IRestfulServiceUtil;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientDto;
import org.trinity.yqyl.common.message.dto.response.LookupResponse;
import org.trinity.yqyl.common.message.dto.response.ServiceReceiverClientResponse;
import org.trinity.yqyl.common.message.dto.response.ServiceSupplierClientResponse;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.LookupType;
import org.trinity.yqyl.common.message.lookup.ServiceSupplierClientStatus;
import org.trinity.yqyl.web.util.Url;

@RestController
@RequestMapping("/user")
public class UserWebController extends AbstractResourceWebController {

    @Autowired
    private IRestfulServiceUtil restfulServiceUtil;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ModelAndView getAccountPage() throws IException {
        return createModelAndView("user/account");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage() throws IException {
        return createModelAndView("user/login");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView getRegisterPage() throws IException {
        return createModelAndView("user/register");
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public ModelAndView passwordPage() throws IException {
        return createModelAndView("user/password");
    }

    @RequestMapping(value = "/supplier/announcement1", method = RequestMethod.GET)
    public ModelAndView supplierAnnouncement1Page() throws IException {
        return createModelAndView("user/supplier/announcement1");
    }

    @RequestMapping(value = "/supplier/announcement2", method = RequestMethod.GET)
    public ModelAndView supplierAnnouncement2Page() throws IException {
        return createModelAndView("user/supplier/announcement2");
    }

    @RequestMapping(value = "/supplier/application", method = RequestMethod.GET)
    public ModelAndView supplierApplicationPage() throws IException {
        final ServiceSupplierClientResponse response = restfulServiceUtil.callRestService(Url.SUPPLIER_ME, null, null, null,
                ServiceSupplierClientResponse.class);

        final ModelAndView modelAndView = createModelAndView("user/supplier/application");
        if (response.getData().isEmpty()) {
            final ServiceSupplierClientDto emptyDto = new ServiceSupplierClientDto();
            modelAndView.addObject("data", emptyDto);
        } else {
            final ServiceSupplierClientDto existingDto = response.getData().get(0);

            if (MessageUtils.in(existingDto.getStatus().getCode(), ServiceSupplierClientStatus.INACTIVE,
                    ServiceSupplierClientStatus.PROPOSAL)) {
                modelAndView.addObject("data", existingDto);
            } else {
                modelAndView.addObject("error", messageResolver.getMessage(ErrorMessage.SUPPLIER_CLIENT_EXISTS));
            }
        }

        return modelAndView;
    }

    @RequestMapping(value = "/supplier/pay", method = RequestMethod.GET)
    public ModelAndView supplierPayPage() throws IException {
        return createModelAndView("user/supplier/pay");
    }

    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    public ModelAndView userinfoPage() throws IException {
        return createModelAndView("user/userinfo");
    }

    @RequestMapping(value = { "/receiver/info/{id}" }, method = RequestMethod.GET)
    public ModelAndView userReceiverInfoPage(@PathVariable(value = "id") final Long id) throws IException {
        final ModelAndView modelAndView = createModelAndView("user/receiver/receiverinfo");

        final ServiceReceiverClientResponse response = restfulServiceUtil.callRestService(Url.RECEIVER, String.valueOf(id), null, null,
                ServiceReceiverClientResponse.class);

        final LookupResponse genders = restfulServiceUtil.callRestService(Url.LOOKUP_TYPE, LookupType.GENDER.getMessageCode(), null, null,
                LookupResponse.class);

        if (response.getErrors().isEmpty()) {
            final ServiceReceiverClientDto receiver = response.getData().get(0);
            if (receiver.getAddress() != null) {
                receiver.setAddress(receiver.getAddress().replace("\\", "\\\\").replace("\n", "\\n").replace("'", "\\'"));
            }
            modelAndView.addObject("receiver", receiver).addObject("genders", genders.getData());
        } else {
            modelAndView.addObject("error", "Error");
        }

        return modelAndView;
    }

    @RequestMapping(value = { "/receiver/new" }, method = RequestMethod.GET)
    public ModelAndView userReceiverNewPage() throws IException {
        final ModelAndView modelAndView = createModelAndView("user/receiver/receiverinfo");

        final LookupResponse genders = restfulServiceUtil.callRestService(Url.LOOKUP_TYPE, LookupType.GENDER.getMessageCode(), null, null,
                LookupResponse.class);

        return modelAndView.addObject("receiver", new ServiceReceiverClientDto()).addObject("genders", genders.getData());
    }

    @RequestMapping(value = "/receiver", method = RequestMethod.GET)
    public ModelAndView userReceiverPage() throws IException {
        final ServiceReceiverClientResponse response = restfulServiceUtil.callRestService(Url.RECEIVER_ME, null, null, null,
                ServiceReceiverClientResponse.class);
        return createModelAndView("user/receiver/receivers").addObject("receivers", response.getData());
    }

    @RequestMapping(value = "/supplier", method = RequestMethod.GET)
    public ModelAndView userSupplierPage() throws IException {
        final ServiceSupplierClientResponse response = restfulServiceUtil.callRestService(Url.SUPPLIER_ME, null, null, null,
                ServiceSupplierClientResponse.class);

        final ModelAndView modelAndView = createModelAndView("user/supplier/supplier");
        if (!response.getData().isEmpty()) {
            if (!MessageUtils.in(response.getData().get(0).getStatus().getCode(), ServiceSupplierClientStatus.INACTIVE,
                    ServiceSupplierClientStatus.PROPOSAL)) {
                modelAndView.addObject("supplier", response.getData().get(0));
            }
        }

        return modelAndView;
    }
}
