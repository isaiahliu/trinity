package org.trinity.yqyl.web.controller.page;

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

    @RequestMapping(value = "/yiquan", method = RequestMethod.GET)
    public ModelAndView yiquanPage() throws IException {
        return createModelAndView("user/yiquan");
    }
}
