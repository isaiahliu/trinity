package org.trinity.yqyl.web.controller.page;

import javax.annotation.security.PermitAll;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.common.exception.IException;

@RestController
@RequestMapping
public class HomeWebController extends AbstractResourceWebController {
	@RequestMapping("")
	@PreAuthorize("hasRole(abc)")
	public ModelAndView defaultPage() throws IException {
		return createModelAndView("redirect:home");
	}

	@RequestMapping("/home")
	@PermitAll
	@PreAuthorize("isAnonymous()")
	public ModelAndView home() throws IException {
		return createModelAndView("home");
	}

}
