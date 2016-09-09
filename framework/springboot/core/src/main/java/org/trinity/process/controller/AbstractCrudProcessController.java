package org.trinity.process.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.repository.CrudRepository;
import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.IDto;
import org.trinity.common.exception.IException;
import org.trinity.common.util.SelfAware;
import org.trinity.message.exception.IErrorMessage;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.process.converter.IObjectConverter.CopyPolicy;

public abstract class AbstractCrudProcessController<TEntity, TDto extends AbstractBusinessDto, TSearchingDto extends IDto>
        extends AbstractProcessController
        implements ICrudProcessController<TDto, TSearchingDto>, SelfAware<AbstractCrudProcessController<TEntity, TDto, TSearchingDto>> {
    private AbstractCrudProcessController<TEntity, TDto, TSearchingDto> selfProxy = this;

    @Override
    @Transactional
    public List<TDto> addAll(final List<TDto> data) throws IException {
        for (final TDto dto : data) {
            final TEntity entity = getDomainObjectConverter().convertBack(dto);

            selfProxy.validateDataPermission(dto);

            selfProxy.addRelationship(entity, dto);

            getDomainEntityRepository().save(entity);

            getDomainObjectConverter().convert(entity, dto, CopyPolicy.TARGET_IS_NULL);
        }

        return data;
    }

    @Override
    @Transactional
    public void delete(final Long id) throws IException {

        final TEntity entity = getDomainEntityRepository().findOne(id);
        if (entity == null) {
            throw getExceptionFactory().createException(getNoInstanceFoundError(), String.valueOf(id));
        }

        final TDto dto = getDomainObjectConverter().convert(entity);

        selfProxy.validateDataPermission(dto);

        selfProxy.deleteRelationship(entity);

        getDomainEntityRepository().delete(entity);
    }

    @Override
    public Page<TDto> getAll(final TSearchingDto data) throws IException {
        return new PageImpl<>(Collections.emptyList());
    }

    @Override
    public List<TDto> getMe() throws IException {
        return Collections.emptyList();
    }

    @Override
    public TDto getOne(final Long id) throws IException {
        final TEntity entity = getDomainEntityRepository().findOne(id);
        if (entity == null) {
            throw getExceptionFactory().createException(getNoInstanceFoundError(), String.valueOf(id));
        }

        final TDto dto = getDomainObjectConverter().convert(entity);

        selfProxy.validateDataPermission(dto);

        return dto;
    }

    @Override
    public void setSelf(final AbstractCrudProcessController<TEntity, TDto, TSearchingDto> selfProxy) {
        this.selfProxy = selfProxy;
    }

    @Override
    @Transactional
    public void updateAll(final List<TDto> data) throws IException {
        final List<TEntity> entities = new ArrayList<>();
        for (final TDto dto : data) {
            final Long id = dto.getId();

            final TEntity entity = getDomainEntityRepository().findOne(id);
            if (entity == null) {
                throw getExceptionFactory().createException(getNoInstanceFoundError(), String.valueOf(id));
            }

            selfProxy.validateDataPermission(dto);

            selfProxy.updateRelationship(entity, dto);

            entities.add(getDomainObjectConverter().convertBack(dto, entity, CopyPolicy.SOURCE_IS_NOT_NULL));
        }

        getDomainEntityRepository().save(entities);
    }

    protected void addRelationship(final TEntity entity, final TDto dto) throws IException {
    }

    protected void deleteRelationship(final TEntity entity) {
    }

    protected abstract CrudRepository<TEntity, Long> getDomainEntityRepository();

    protected abstract Class<TEntity> getDomainEntityType();

    protected abstract IObjectConverter<TEntity, TDto> getDomainObjectConverter();

    protected abstract IErrorMessage getNoInstanceFoundError();

    protected void updateRelationship(final TEntity entity, final TDto dto) {
    }

    protected void validateDataPermission(final TDto dto) throws IException {
        getDataPermissionValidatorProvider().getValidator(getDomainEntityType()).validate(dto.getId());
    }
}
