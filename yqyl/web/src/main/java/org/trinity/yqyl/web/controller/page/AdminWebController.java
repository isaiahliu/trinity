package org.trinity.yqyl.web.controller.page;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.common.exception.IException;

@RestController
@RequestMapping("/service/admin")
public class AdminWebController extends AbstractResourceWebController {
	@RequestMapping(value = { "/userManagement", "" }, method = RequestMethod.GET)
	public ModelAndView userManagementPage() throws IException {
		return createModelAndView("service/admin/userManagement");
	}
}
