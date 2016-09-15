package org.trinity.yqyl.web.controller.page;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.accessright.Authorize;
import org.trinity.yqyl.common.message.lookup.AccessRight;

@RestController
@RequestMapping("/service/admin")
public class AdminWebController extends AbstractResourceWebController {
	@RequestMapping(value = { "/operator", "" }, method = RequestMethod.GET)
	@Authorize(requireAny = AccessRight.CLIENT_ADMINISTRATOR)
	public ModelAndView operatorManagementPage() throws IException {
		return createModelAndView("service/admin/operatorManagement");
	}

	@RequestMapping(value = { "/operator/{id}" }, method = RequestMethod.GET)
	@Authorize(requireAny = AccessRight.CLIENT_ADMINISTRATOR)
	public ModelAndView operatorPage(@PathVariable("id") final Long id) throws IException {
		return createModelAndView("service/admin/operator").addObject("id", id);
	}
}
