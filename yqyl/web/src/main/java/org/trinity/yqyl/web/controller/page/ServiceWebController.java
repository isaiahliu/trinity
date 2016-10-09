package org.trinity.yqyl.web.controller.page;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.common.exception.IException;

@RestController
@RequestMapping("/service")
public class ServiceWebController extends AbstractResourceWebController {
    @RequestMapping("/info/{id}")
    public ModelAndView infoPage(@PathVariable("id") final Long id) throws IException {
        return createModelAndView("service/info").addObject("id", id);
    }

    @RequestMapping({ "", "/search" })
    public ModelAndView searchPage() throws IException {
        return createModelAndView("service/search");
    }

}
