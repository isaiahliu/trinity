package org.trinity.yqyl.web.controller.page;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.accessright.Authorize;
import org.trinity.yqyl.common.message.lookup.AccessRight;

@RestController
@RequestMapping("/servicer")
public class ServicerWebController extends AbstractResourceWebController {
    @RequestMapping({ "", "/info" })
    @Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
    public ModelAndView infoPage() throws IException {
        return createModelAndView("servicer/info");
    }

    @RequestMapping("/order")
    @Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
    public ModelAndView orderPage() throws IException {
        return createModelAndView("servicer/order");
    }

    @RequestMapping("/service/edit/{id}")
    @Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
    public ModelAndView serviceEditPage(@PathVariable("id") final Long id) throws IException {
        return createModelAndView("servicer/serviceEdit").addObject("serviceInfoId", id);
    }

    @RequestMapping("/service/new")
    @Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
    public ModelAndView serviceNewPage() throws IException {
        return createModelAndView("servicer/serviceEdit").addObject("serviceInfoId", 0);
    }

    @RequestMapping("/service")
    @Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
    public ModelAndView servicePage() throws IException {
        return createModelAndView("servicer/service");
    }

    @RequestMapping("/staff")
    @Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
    public ModelAndView staffPage() throws IException {
        return createModelAndView("servicer/staff");
    }

    @RequestMapping("/training")
    @Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
    public ModelAndView trainingPage() throws IException {
        return createModelAndView("servicer/training");
    }

    @RequestMapping("/transaction")
    @Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
    public ModelAndView transactionPage() throws IException {
        return createModelAndView("servicer/transaction");
    }

    @Override
    @Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
    protected ModelAndView createModelAndView(final String viewName) {
        return super.createModelAndView(viewName).addObject("platform", "SERVICER");
    }
}
