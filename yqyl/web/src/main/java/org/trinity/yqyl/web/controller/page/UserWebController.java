package org.trinity.yqyl.web.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.common.exception.IException;
import org.trinity.rest.util.IRestfulServiceUtil;

@RestController
@RequestMapping("/user")
public class UserWebController extends AbstractResourceWebController {

    @Autowired
    private IRestfulServiceUtil restfulServiceUtil;

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public ModelAndView passwordPage() throws IException {
        return createModelAndView("user/password");
    }

    @RequestMapping(value = { "", "/userinfo" }, method = RequestMethod.GET)
    public ModelAndView userinfoPage() throws IException {
        return createModelAndView("user/userinfo");
    }

}
