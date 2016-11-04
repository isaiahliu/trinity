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

    @RequestMapping("/supplier/audit/{entityId}")
    @Authorize(requireAny = AccessRight.ADMINISTRATOR)
    public ModelAndView infoPage(@PathVariable("entityId") final Long entityId) throws IException {
        return createModelAndView("service/info").addObject("serviceSupplierClientId", entityId);
    }

    @RequestMapping({ "", "/news" })
    @Authorize(requireAny = AccessRight.ADMINISTRATOR)
    public ModelAndView newsPage() throws IException {
        return createModelAndView("admin/news");
    }

    @RequestMapping("/permission/edit/{entityId}")
    @Authorize(requireAny = AccessRight.ADMINISTRATOR)
    public ModelAndView permissionEditPage(@PathVariable("entityId") final Long entityId) throws IException {
        return createModelAndView("admin/permissionEdit").addObject("userId", entityId);
    }

    @RequestMapping("/permission")
    @Authorize(requireAny = AccessRight.ADMINISTRATOR)
    public ModelAndView permissionPage() throws IException {
        return createModelAndView("admin/permission");
    }

    @RequestMapping("/receiver")
    @Authorize(requireAny = AccessRight.ADMINISTRATOR)
    public ModelAndView receiverPage() throws IException {
        return createModelAndView("admin/receiver");
    }

    @RequestMapping("/role")
    @Authorize(requireAny = AccessRight.ADMINISTRATOR)
    public ModelAndView rolePage() throws IException {
        return createModelAndView("admin/role");
    }

    @RequestMapping("/service/appraise")
    @Authorize(requireAny = AccessRight.ADMINISTRATOR)
    public ModelAndView serviceAppraisePage() throws IException {
        return createModelAndView("admin/service/appraise");
    }

    @RequestMapping("/service/category/edit")
    @Authorize(requireAny = AccessRight.ADMINISTRATOR)
    public ModelAndView serviceCategoryEditPage() throws IException {
        return createModelAndView("admin/service/categoryEdit");
    }

    @RequestMapping("/service/category")
    @Authorize(requireAny = AccessRight.ADMINISTRATOR)
    public ModelAndView serviceCategoryPage() throws IException {
        return createModelAndView("admin/service/category");
    }

    @RequestMapping("/service/order")
    @Authorize(requireAny = AccessRight.ADMINISTRATOR)
    public ModelAndView serviceOrderPage() throws IException {
        return createModelAndView("admin/service/order");
    }

    @RequestMapping("/service/category/edit/{entityId}")
    @Authorize(requireAny = AccessRight.ADMINISTRATOR)
    public ModelAndView serviceSubCategoryEditPage(@PathVariable("entityId") final Long entityId) throws IException {
        return createModelAndView("admin/service/categoryEdit").addObject("categoryId", entityId);
    }

    @RequestMapping("/supplier")
    @Authorize(requireAny = AccessRight.ADMINISTRATOR)
    public ModelAndView supplierPage() throws IException {
        return createModelAndView("admin/supplier");
    }

    @RequestMapping("/training")
    @Authorize(requireAny = AccessRight.ADMINISTRATOR)
    public ModelAndView trainingPage() throws IException {
        return createModelAndView("admin/training");
    }

    @Override
    @Authorize(requireAny = AccessRight.ADMINISTRATOR)
    protected ModelAndView createModelAndView(final String viewName) {
        return super.createModelAndView(viewName).addObject("platform", "ADMIN");
    }
}
