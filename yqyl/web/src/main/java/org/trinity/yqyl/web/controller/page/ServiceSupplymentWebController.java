package org.trinity.yqyl.web.controller.page;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.common.exception.IException;

@RestController
@RequestMapping("/service/supplyment")
public class ServiceSupplymentWebController extends AbstractResourceWebController {
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ModelAndView ordersPage() throws IException {
        return createModelAndView("service/supplyment/orders");
    }

    @RequestMapping(value = { "", "/serviceList" }, method = RequestMethod.GET)
    public ModelAndView serviceListPage() throws IException {
        return createModelAndView("service/supplyment/serviceList");
    }

}
