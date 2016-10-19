package org.trinity.yqyl.web.controller.page;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.common.exception.IException;

@RestController
@RequestMapping("/admin")
public class AdminWebController extends AbstractResourceWebController {

	@RequestMapping("/supplier/audit/{id}")
	public ModelAndView infoPage(@PathVariable("id") final Long id) throws IException {
		return createModelAndView("service/info").addObject("serviceSupplierClientId", id);
	}

	@RequestMapping({ "", "/news" })
	public ModelAndView newsPage() throws IException {
		return createModelAndView("admin/news");
	}

	@RequestMapping("/permission")
	public ModelAndView permissionPage() throws IException {
		return createModelAndView("admin/permission");
	}

	@RequestMapping("/receiver")
	public ModelAndView receiverPage() throws IException {
		return createModelAndView("admin/receiver");
	}

	@RequestMapping("/role")
	public ModelAndView rolePage() throws IException {
		return createModelAndView("admin/role");
	}

	@RequestMapping("/service/appraise")
	public ModelAndView serviceAppraisePage() throws IException {
		return createModelAndView("admin/service/appraise");
	}

	@RequestMapping("/service/category/edit")
	public ModelAndView serviceCategoryEditPage() throws IException {
		return createModelAndView("admin/service/categoryEdit");
	}

	@RequestMapping("/service/category")
	public ModelAndView serviceCategoryPage() throws IException {
		return createModelAndView("admin/service/category");
	}

	@RequestMapping("/service/order")
	public ModelAndView serviceOrderPage() throws IException {
		return createModelAndView("admin/service/order");
	}

	@RequestMapping("/service/category/edit/{id}")
	public ModelAndView serviceSubCategoryEditPage(@PathVariable("id") final Long id) throws IException {
		return createModelAndView("admin/service/categoryEdit").addObject("categoryId", id);
	}

	@RequestMapping("/supplier")
	public ModelAndView supplierPage() throws IException {
		return createModelAndView("admin/supplier");
	}

	@RequestMapping("/training")
	public ModelAndView trainingPage() throws IException {
		return createModelAndView("admin/training");
	}

	@Override
	protected ModelAndView createModelAndView(final String viewName) {
		return super.createModelAndView(viewName).addObject("platform", "ADMIN");
	}
}
