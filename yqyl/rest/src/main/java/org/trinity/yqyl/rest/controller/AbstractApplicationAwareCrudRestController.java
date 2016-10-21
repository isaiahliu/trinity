package org.trinity.yqyl.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.ISearchingDto;
import org.trinity.common.dto.request.AbstractDataRequest;
import org.trinity.common.dto.response.AbstractResponse;
import org.trinity.process.controller.ICrudProcessController;
import org.trinity.rest.controller.AbstractCrudRestController;

/**
 * @author isaia
 *
 * @param <TDto>
 * @param <TSearchingDto>
 * @param <TProcessController>
 * @param <TRequest>
 * @param <TResponse>
 */
public abstract class AbstractApplicationAwareCrudRestController<TDto extends AbstractBusinessDto, TSearchingDto extends ISearchingDto, TProcessController extends ICrudProcessController<TDto, TSearchingDto>, TRequest extends AbstractDataRequest<TDto>, TResponse extends AbstractResponse<TDto>>
        extends AbstractCrudRestController<TDto, TSearchingDto, TProcessController, TRequest, TResponse> {
    @Autowired
    private TProcessController domainProcessController;

    @Override
    protected TProcessController getDomainProcessController() {
        return domainProcessController;
    }

}
