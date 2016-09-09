package org.trinity.rest.controller;

import org.springframework.web.servlet.ModelAndView;
import org.trinity.rest.IWebController;

public abstract class AbstractWebController implements IWebController {
	protected ModelAndView createModelAndView(final String viewName) {
		return new ModelAndView(viewName);
	}
}
