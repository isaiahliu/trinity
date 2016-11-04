package org.trinity.process.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.ISearchingDto;
import org.trinity.common.exception.IException;
import org.trinity.common.util.SelfAware;
import org.trinity.message.exception.GeneralErrorMessage;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.process.converter.IObjectConverter.CopyPolicy;
import org.trinity.process.datapermission.IDataPermissionValidator;
import org.trinity.repository.repository.IRepository;

public abstract class AbstractCrudProcessController<TEntity, TDto extends AbstractBusinessDto, TSearchingDto extends ISearchingDto>
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
            throw getExceptionFactory().createException(GeneralErrorMessage.UNABLE_TO_FIND_INSTANCE, String.valueOf(id));
        }

        final TDto dto = getDomainObjectConverter().convert(entity);

        selfProxy.validateDataPermission(dto);

        selfProxy.deleteRelationship(entity);

        getDomainEntityRepository().delete(entity);
    }

    @Override
    @Transactional
    public Page<TDto> getAll(final TSearchingDto data) throws IException {
        prepareSearch(data);

        final Pageable pageable = createPageable(data);

        return getDomainEntityRepository().query(data, pageable)
                .map(item -> getDomainObjectConverter().convert(item, data.generateRelationship()));
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

            selfProxy.validateDataPermission(dto);

            TEntity entity = null;

            if (id != null && id != 0) {
                entity = getDomainEntityRepository().findOne(id);
                if (entity == null) {
                    throw getExceptionFactory().createException(GeneralErrorMessage.UNABLE_TO_FIND_INSTANCE, String.valueOf(id));
                }
                selfProxy.updateRelationship(entity, dto);
            } else {
                dto.setId(null);
                entity = getDomainObjectConverter().convertBack(dto);
                selfProxy.addRelationship(entity, dto);
            }

            entities.add(getDomainObjectConverter().convertBack(dto, entity, CopyPolicy.SOURCE_IS_NOT_NULL));
        }

        getDomainEntityRepository().save(entities);
    }

    protected void addRelationship(final TEntity entity, final TDto dto) throws IException {
    }

    protected abstract boolean canAccessAllStatus();

    protected abstract Pageable createPageable(final TSearchingDto data);

    protected void deleteRelationship(final TEntity entity) throws IException {
    }

    protected abstract String getCurrentUsername();

    protected abstract IDataPermissionValidator<TEntity> getDomainDataPermissionValidator();

    protected abstract IRepository<TEntity, TSearchingDto> getDomainEntityRepository();

    protected abstract IObjectConverter<TEntity, TDto> getDomainObjectConverter();

    protected void prepareSearch(final TSearchingDto data) {
        data.setCurrentUsername(getCurrentUsername());

        if (!canAccessAllStatus()) {
            data.setSearchAllStatus(false);
            data.getStatus().clear();
        }
    }

    protected void updateRelationship(final TEntity entity, final TDto dto) throws IException {
    }

    protected void validateDataPermission(final TDto dto) throws IException {
        getDomainDataPermissionValidator().validate(dto.getId());
    }
}
