package org.trinity.yqyl.web.controller.page;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.common.exception.IException;

@RestController
@RequestMapping("/servicer")
public class ServicerWebController extends AbstractResourceWebController {
    @RequestMapping({ "", "/info" })
    public ModelAndView infoPage() throws IException {
        return createModelAndView("servicer/info");
    }

    @RequestMapping("/order")
    public ModelAndView orderPage() throws IException {
        return createModelAndView("servicer/order");
    }

    @RequestMapping("/service")
    public ModelAndView servicePage() throws IException {
        return createModelAndView("servicer/service");
    }

    @RequestMapping("/staff")
    public ModelAndView staffPage() throws IException {
        return createModelAndView("servicer/staff");
    }

    @RequestMapping("/training")
    public ModelAndView trainingPage() throws IException {
        return createModelAndView("servicer/training");
    }

    @RequestMapping("/transaction")
    public ModelAndView transactionPage() throws IException {
        return createModelAndView("servicer/transaction");
    }

    @Override
    protected ModelAndView createModelAndView(final String viewName) {
        return super.createModelAndView(viewName).addObject("platform", "SERVICER");
    }
}
