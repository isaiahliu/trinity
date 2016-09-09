package org.trinity.yqyl.web.controller.page;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.common.exception.IException;

@RestController
@RequestMapping("/service/supplyment")
public class ServiceSupplymentWebController extends AbstractResourceWebController {
	@RequestMapping("")
	public ModelAndView defaultPage() throws IException {
		return createModelAndView("service/supplyment/default");
	}

}
