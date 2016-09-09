package org.trinity.yqyl.web.controller.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.exception.IException;
import org.trinity.message.IMessageResolverChain;
import org.trinity.rest.controller.AbstractRestController;
import org.trinity.rest.util.IRestfulServiceUtil;
import org.trinity.yqyl.web.util.Url;

@RestController
@RequestMapping("/ajax/common")
public class CommonAjaxController extends AbstractRestController {
    @Autowired
    private IRestfulServiceUtil restfulServiceUtil;

    @Autowired
    protected IMessageResolverChain messageResolver;

    @RequestMapping(value = "/resource", method = RequestMethod.PUT)
    public @ResponseBody DefaultResponse ajaxRefreshResource() throws IException {
        messageResolver.refresh();
        return restfulServiceUtil.callRestService(Url.RESOURCE_REFRESH, null, null, null, DefaultResponse.class);
    }
}
