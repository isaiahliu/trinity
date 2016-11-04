package org.trinity.rest.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.ISearchingDto;
import org.trinity.common.dto.object.PagingDto;
import org.trinity.common.dto.request.AbstractDataRequest;
import org.trinity.common.dto.request.AbstractDataRequest.AddData;
import org.trinity.common.dto.request.AbstractDataRequest.UpdateData;
import org.trinity.common.dto.response.AbstractResponse;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.common.dto.validator.OnValid;
import org.trinity.common.exception.IException;
import org.trinity.common.util.SelfAware;
import org.trinity.process.controller.ICrudProcessController;

public abstract class AbstractCrudRestController<TDto extends AbstractBusinessDto, TSearchingDto extends ISearchingDto, TProcessController extends ICrudProcessController<TDto, TSearchingDto>, TRequest extends AbstractDataRequest<TDto>, TResponse extends AbstractResponse<TDto>>
        extends AbstractRestController
        implements SelfAware<AbstractCrudRestController<TDto, TSearchingDto, TProcessController, TRequest, TResponse>> {

    private AbstractCrudRestController<TDto, TSearchingDto, TProcessController, TRequest, TResponse> selfProxy = this;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<TResponse> addAll(@RequestBody @OnValid(AddData.class) final TRequest request) throws IException {
        selfProxy.validateAdd();

        final TResponse response = createResponseInstance();

        final List<TDto> data = getDomainProcessController().addAll(request.getData());

        response.addData(data);

        return createResponseEntity(response);
    }

    @RequestMapping(value = "/{entityId}", method = RequestMethod.DELETE)
    public @ResponseBody ResponseEntity<DefaultResponse> deleteAll(@PathVariable("entityId") final Long entityId) throws IException {
        selfProxy.validateDelete();

        final DefaultResponse response = new DefaultResponse();

        getDomainProcessController().delete(entityId);

        return createResponseEntity(response);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<TResponse> getAll(final TSearchingDto request) throws IException {
        if (request.isSearchAll()) {
            try {
                selfProxy.validateGet();
            } catch (final IException e) {
                request.setSearchScope(ISearchingDto.SEARCH_ME);
                selfProxy.validateGetMe();
            }
        } else {
            selfProxy.validateGetMe();
        }

        final TResponse response = createResponseInstance();

        final Page<TDto> data = getDomainProcessController().getAll(request);

        final PagingDto responsePaging = new PagingDto();
        responsePaging.setPageIndex(data.getNumber());
        responsePaging.setPageSize(data.getSize());
        responsePaging.setPageCount(data.getTotalPages());
        responsePaging.setItemCount(data.getTotalElements());

        response.addData(data.getContent());
        response.getMeta().setPaging(responsePaging);

        return createResponseEntity(response);
    }

    @RequestMapping(value = "/{entityId}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<TResponse> getOne(@PathVariable("entityId") final Long entityId, final TSearchingDto request)
            throws IException {
        request.setId(entityId);

        final TResponse response = createResponseInstance();

        response.addData(getDomainProcessController().getAll(request).getContent());

        return createResponseEntity(response);
    }

    @Override
    public void setSelf(final AbstractCrudRestController<TDto, TSearchingDto, TProcessController, TRequest, TResponse> selfProxy) {
        this.selfProxy = selfProxy;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<DefaultResponse> updateAll(@RequestBody @OnValid(UpdateData.class) final TRequest request)
            throws IException {
        selfProxy.validateUpdate();

        final DefaultResponse response = new DefaultResponse();

        getDomainProcessController().updateAll(request.getData());

        return createResponseEntity(response);
    }

    protected abstract TResponse createResponseInstance();

    protected abstract TProcessController getDomainProcessController();

    protected void validateAdd() throws IException {
    }

    protected void validateDelete() throws IException {
    }

    protected void validateGet() throws IException {
    }

    protected void validateGetMe() throws IException {
    }

    protected void validateUpdate() throws IException {
    }
}
