package org.trinity.yqyl.web.controller.page;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.accessright.Authorize;
import org.trinity.yqyl.common.message.lookup.AccessRight;

@RestController
@RequestMapping("/admin")
public class AdminWebController extends AbstractResourceWebController {

    @RequestMapping("/supplier/audit/{id}")
    @Authorize(requireAny = AccessRight.OPERATOR)
    public ModelAndView infoPage(@PathVariable("id") final Long id) throws IException {
        return createModelAndView("service/info").addObject("serviceSupplierClientId", id);
    }

    @RequestMapping({ "", "/news" })
    @Authorize(requireAny = AccessRight.OPERATOR)
    public ModelAndView newsPage() throws IException {
        return createModelAndView("admin/news");
    }

    @RequestMapping("/permission")
    @Authorize(requireAny = AccessRight.OPERATOR)
    public ModelAndView permissionPage() throws IException {
        return createModelAndView("admin/permission");
    }

    @RequestMapping("/receiver")
    @Authorize(requireAny = AccessRight.OPERATOR)
    public ModelAndView receiverPage() throws IException {
        return createModelAndView("admin/receiver");
    }

    @RequestMapping("/role")
    @Authorize(requireAny = AccessRight.OPERATOR)
    public ModelAndView rolePage() throws IException {
        return createModelAndView("admin/role");
    }

    @RequestMapping("/service/appraise")
    @Authorize(requireAny = AccessRight.OPERATOR)
    public ModelAndView serviceAppraisePage() throws IException {
        return createModelAndView("admin/service/appraise");
    }

    @RequestMapping("/service/category/edit")
    @Authorize(requireAny = AccessRight.OPERATOR)
    public ModelAndView serviceCategoryEditPage() throws IException {
        return createModelAndView("admin/service/categoryEdit");
    }

    @RequestMapping("/service/category")
    @Authorize(requireAny = AccessRight.OPERATOR)
    public ModelAndView serviceCategoryPage() throws IException {
        return createModelAndView("admin/service/category");
    }

    @RequestMapping("/service/order")
    @Authorize(requireAny = AccessRight.OPERATOR)
    public ModelAndView serviceOrderPage() throws IException {
        return createModelAndView("admin/service/order");
    }

    @RequestMapping("/service/category/edit/{id}")
    @Authorize(requireAny = AccessRight.OPERATOR)
    public ModelAndView serviceSubCategoryEditPage(@PathVariable("id") final Long id) throws IException {
        return createModelAndView("admin/service/categoryEdit").addObject("categoryId", id);
    }

    @RequestMapping("/supplier")
    @Authorize(requireAny = AccessRight.OPERATOR)
    public ModelAndView supplierPage() throws IException {
        return createModelAndView("admin/supplier");
    }

    @RequestMapping("/training")
    @Authorize(requireAny = AccessRight.OPERATOR)
    public ModelAndView trainingPage() throws IException {
        return createModelAndView("admin/training");
    }

    @Override
    @Authorize(requireAny = AccessRight.OPERATOR)
    protected ModelAndView createModelAndView(final String viewName) {
        return super.createModelAndView(viewName).addObject("platform", "ADMIN");
    }
}
