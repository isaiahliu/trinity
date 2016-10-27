package org.trinity.yqyl.web.controller.page;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.common.exception.IException;

@RestController
@RequestMapping("/service")
public class ServiceWebController extends AbstractResourceWebController {
    @RequestMapping("/info/{id}")
    public ModelAndView infoPage(@PathVariable("id") final Long id) throws IException {
        return createModelAndView("service/info").addObject("serviceSupplierClientId", id);
    }

    @RequestMapping("/proposal/{id}")
    public ModelAndView proposalPage(@PathVariable("id") final Long id,
            @RequestParam(value = "selected", required = false) final List<Long> selectedServiceInfos) throws IException {
        return createModelAndView("service/proposal").addObject("serviceSupplierClientId", id).addObject("selectedServiceInfos",
                (selectedServiceInfos != null) ? selectedServiceInfos : Collections.emptyList());
    }

    @RequestMapping("/publish")
    public ModelAndView publishPage() throws IException {
        return createModelAndView("service/publish");
    }

    @RequestMapping({ "", "/search" })
    public ModelAndView searchPage() throws IException {
        return createModelAndView("service/search");
    }
}
