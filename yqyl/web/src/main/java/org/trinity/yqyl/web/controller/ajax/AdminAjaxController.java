package org.trinity.yqyl.web.controller.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;
import org.trinity.rest.controller.AbstractRestController;
import org.trinity.rest.util.IRestfulServiceUtil;
import org.trinity.yqyl.common.message.dto.domain.OperatorClientSearchingDto;
import org.trinity.yqyl.common.message.dto.response.OperatorClientResponse;
import org.trinity.yqyl.web.util.Url;

@RestController
@RequestMapping("/ajax/service/admin")
public class AdminAjaxController extends AbstractRestController {
    @Autowired
    private IRestfulServiceUtil restfulServiceUtil;

    @RequestMapping(value = "/audit/receiver/{id}", method = RequestMethod.PUT)
    public @ResponseBody DefaultResponse ajaxAuditReceiver(@PathVariable("id") final Long id) throws IException {
        return restfulServiceUtil.callRestService(Url.RECEIVER_AUDIT, String.valueOf(id), null, null, DefaultResponse.class);
    }

    @RequestMapping(value = "/operator", method = RequestMethod.GET)
    public @ResponseBody OperatorClientResponse ajaxOperator(final OperatorClientSearchingDto dto) throws IException {
        return restfulServiceUtil.callRestService(Url.OPERATOR, null, null, dto, OperatorClientResponse.class);
    }
}
