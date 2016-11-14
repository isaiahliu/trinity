package org.trinity.yqyl.web.controller.page;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.accessright.Authorize;
import org.trinity.yqyl.common.message.lookup.AccessRight;

@RestController
@RequestMapping("/servicer")
public class ServicerWebController extends AbstractResourceWebController {
	@RequestMapping("/auditing")
	public ModelAndView auditingPage() throws IException {
		return createModelAndView("servicer/auditing");
	}

	@RequestMapping({ "", "/info" })
	public ModelAndView infoPage() throws IException {
		return createModelAndView("servicer/info").addObject("serviceSupplierClientId", 0);
	}

	@RequestMapping("/info/material")
	public ModelAndView materialPage() throws IException {
		return createModelAndView("servicer/material");
	}

	@RequestMapping("/order/{entityId}")
	@Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
	public ModelAndView orderDetailPage(@PathVariable("entityId") final Long entityId) throws IException {
		return createModelAndView("service/detail").addObject("orderId", entityId).addObject("mode", "supplier");
	}

	@RequestMapping("/order/edit/{entityId}")
	@Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
	public ModelAndView orderEditPage(@PathVariable("entityId") final Long entityId) throws IException {
		return createModelAndView("servicer/orderEdit").addObject("serviceOrderId", entityId);
	}

	@RequestMapping("/order")
	@Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
	public ModelAndView orderPage() throws IException {
		return createModelAndView("servicer/order");
	}

	@RequestMapping("/service/edit/{entityId}")
	@Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
	public ModelAndView serviceEditPage(@PathVariable("entityId") final Long entityId) throws IException {
		return createModelAndView("servicer/serviceEdit").addObject("serviceInfoId", entityId);
	}

	@RequestMapping("/service/new")
	@Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
	public ModelAndView serviceNewPage() throws IException {
		return createModelAndView("servicer/serviceEdit").addObject("serviceInfoId", 0);
	}

	@RequestMapping("/service")
	@Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
	public ModelAndView servicePage() throws IException {
		return createModelAndView("servicer/service");
	}

	@RequestMapping("/staff/edit/{entityId}")
	@Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
	public ModelAndView staffEditPage(@PathVariable("entityId") final Long entityId) throws IException {
		return createModelAndView("servicer/staffEdit").addObject("staffId", entityId);
	}

	@RequestMapping("/staff/new")
	@Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
	public ModelAndView staffNewPage() throws IException {
		return createModelAndView("servicer/staffEdit").addObject("staffId", 0);
	}

	@RequestMapping("/staff")
	@Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
	public ModelAndView staffPage() throws IException {
		return createModelAndView("servicer/staff");
	}

	@RequestMapping("/training")
	@Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
	public ModelAndView trainingPage() throws IException {
		return createModelAndView("servicer/training");
	}

	@RequestMapping("/transaction")
	@Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
	public ModelAndView transactionPage() throws IException {
		return createModelAndView("servicer/transaction");
	}

	@Override
	@Authorize(requireAny = AccessRight.SERVICE_SUPPLIER)
	protected ModelAndView createModelAndView(final String viewName) {
		return super.createModelAndView(viewName).addObject("platform", "SERVICER");
	}
}
