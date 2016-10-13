package org.trinity.yqyl.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.message.dto.domain.ServiceCategoryDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceCategorySearchingDto;
import org.trinity.yqyl.common.message.dto.request.ServiceCategoryRequest;
import org.trinity.yqyl.common.message.dto.response.ServiceCategoryResponse;
import org.trinity.yqyl.process.controller.base.IServiceCategoryProcessController;

@RestController
@RequestMapping("/common/servicecategory")
public class ServiceCategoryRestController extends
        AbstractApplicationAwareCrudRestController<ServiceCategoryDto, ServiceCategorySearchingDto, IServiceCategoryProcessController, ServiceCategoryRequest, ServiceCategoryResponse> {

    @Override
    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<ServiceCategoryResponse> getAll(final ServiceCategorySearchingDto request) throws IException {
        final ServiceCategoryResponse response = new ServiceCategoryResponse();

        response.addData(getDomainProcessController().getParentServiceCategoriesWithChildren(request));
        return createResponseEntity(response);
    }

    @RequestMapping(value = "/children/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<ServiceCategoryResponse> getAllChildren(@PathVariable("id") final Long id) throws IException {
        final ServiceCategoryResponse response = new ServiceCategoryResponse();

        response.addData(getDomainProcessController().getSubServiceCategories(id));

        return createResponseEntity(response);
    }

    @RequestMapping(value = "/parents", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<ServiceCategoryResponse> getAllParentServiceCategories() throws IException {
        final ServiceCategoryResponse response = new ServiceCategoryResponse();

        response.addData(getDomainProcessController().getAllParentServiceCategories());

        return createResponseEntity(response);
    }

    @Override
    protected ServiceCategoryResponse createResponseInstance() {
        return new ServiceCategoryResponse();
    }
}
