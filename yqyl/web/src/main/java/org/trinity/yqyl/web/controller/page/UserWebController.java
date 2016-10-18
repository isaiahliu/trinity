package org.trinity.yqyl.web.controller.page;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.common.exception.IException;

@RestController
@RequestMapping("/user")
public class UserWebController extends AbstractResourceWebController {

    @RequestMapping(value = "/family", method = RequestMethod.GET)
    public ModelAndView familyPage() throws IException {
        return createModelAndView("user/family");
    }

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public ModelAndView healthPage() throws IException {
        return createModelAndView("user/health");
    }

    @RequestMapping(value = "/order/appraise/{id}", method = RequestMethod.GET)
    public ModelAndView orderAppraisePage(@PathVariable("id") final Long id) throws IException {
        return createModelAndView("user/appraise").addObject("orderId", id);
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public ModelAndView orderDetailPage(@PathVariable("id") final Long id) throws IException {
        return createModelAndView("service/detail").addObject("orderId", id);
    }

    @RequestMapping(value = "/order/edit/{id}", method = RequestMethod.GET)
    public ModelAndView orderEditPage(@PathVariable("id") final Long id) throws IException {
        return createModelAndView("service/proposal").addObject("orderId", id);
    }

    @RequestMapping(value = { "", "/order" }, method = RequestMethod.GET)
    public ModelAndView orderPage() throws IException {
        return createModelAndView("user/order");
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public ModelAndView passwordPage() throws IException {
        return createModelAndView("user/password");
    }

    @RequestMapping(value = "/realname", method = RequestMethod.GET)
    public ModelAndView realnamePage() throws IException {
        return createModelAndView("user/realname");
    }

    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    public ModelAndView userinfoPage() throws IException {
        return createModelAndView("user/userinfo");
    }

    @RequestMapping(value = "/volunteer", method = RequestMethod.GET)
    public ModelAndView volunteerPage() throws IException {
        return createModelAndView("user/volunteer");
    }

    @RequestMapping(value = { "/yiquan", "/yiquan/bind" }, method = RequestMethod.GET)
    public ModelAndView yiquanBindPage() throws IException {
        return createModelAndView("user/yiquan/bind");
    }

    @RequestMapping(value = "/yiquan/branch", method = RequestMethod.GET)
    public ModelAndView yiquanBranchPage() throws IException {
        return createModelAndView("user/yiquan/branch");
    }

    @RequestMapping(value = "/yiquan/point", method = RequestMethod.GET)
    public ModelAndView yiquanPointPage() throws IException {
        return createModelAndView("user/yiquan/point");
    }

    @RequestMapping(value = "/yiquan/search", method = RequestMethod.GET)
    public ModelAndView yiquanSearchPage() throws IException {
        return createModelAndView("user/yiquan/search");
    }

    @RequestMapping(value = "/yiquan/topup", method = RequestMethod.GET)
    public ModelAndView yiquanTopupPage() throws IException {
        return createModelAndView("user/yiquan/topup");
    }
}
