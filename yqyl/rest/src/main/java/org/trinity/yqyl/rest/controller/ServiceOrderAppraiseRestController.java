package org.trinity.yqyl.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderAppraiseDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderAppraiseSearchingDto;
import org.trinity.yqyl.common.message.dto.request.ServiceOrderAppraiseRequest;
import org.trinity.yqyl.common.message.dto.response.ServiceOrderAppraiseResponse;
import org.trinity.yqyl.process.controller.base.IServiceOrderAppraiseProcessController;

@RestController
@RequestMapping("/user/order/appraise")
public class ServiceOrderAppraiseRestController extends
        AbstractApplicationAwareCrudRestController<ServiceOrderAppraiseDto, ServiceOrderAppraiseSearchingDto, IServiceOrderAppraiseProcessController, ServiceOrderAppraiseRequest, ServiceOrderAppraiseResponse> {

    @Override
    protected ServiceOrderAppraiseResponse createResponseInstance() {
        return new ServiceOrderAppraiseResponse();
    }
}
