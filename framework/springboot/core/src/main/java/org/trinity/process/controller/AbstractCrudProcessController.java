package org.trinity.process.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional(rollbackOn = IException.class)
    public List<TDto> addAll(final List<TDto> data) throws IException {
        for (final TDto dto : data) {
            final TEntity entity = getDomainObjectConverter().convertBack(dto);

            selfProxy.validateDataPermission(dto);

            selfProxy.addRelationshipFields(entity, dto);

            getDomainEntityRepository().save(entity);

            getDomainObjectConverter().convert(entity, dto, CopyPolicy.TARGET_IS_NULL);

            selfProxy.addRelatedTables(entity, dto);
        }

        return data;
    }

    @Override
    @Transactional(rollbackOn = IException.class)
    public void delete(final Long id) throws IException {

        final TEntity entity = getDomainEntityRepository().findOne(id);
        if (entity == null) {
            throw getExceptionFactory().createException(GeneralErrorMessage.UNABLE_TO_FIND_INSTANCE, String.valueOf(id));
        }

        final TDto dto = getDomainObjectConverter().convert(entity);

        selfProxy.validateDataPermission(dto);

        selfProxy.deleteRelatedTables(entity);

        getDomainEntityRepository().delete(entity);
    }

    @Override
    @Transactional(rollbackOn = IException.class)
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
    @Transactional(rollbackOn = IException.class)
    public void updateAll(final List<TDto> data) throws IException {
        final List<TEntity> entities = new ArrayList<>();
        for (final TDto dto : data.stream().filter(item -> item.getId() != null && item.getId() != 0).collect(Collectors.toList())) {
            final Long id = dto.getId();

            selfProxy.validateDataPermission(dto);

            TEntity entity = null;

            entity = getDomainEntityRepository().findOne(id);
            if (entity == null) {
                throw getExceptionFactory().createException(GeneralErrorMessage.UNABLE_TO_FIND_INSTANCE, String.valueOf(id));
            }
            selfProxy.updateRelationshipFields(entity, dto);
            selfProxy.updateRelatedTables(entity, dto);

            entities.add(getDomainObjectConverter().convertBack(dto, entity, CopyPolicy.SOURCE_IS_NOT_NULL));
        }

        getDomainEntityRepository().save(entities);

        final List<TDto> newItems = data.stream().filter(item -> item.getId() == null || item.getId() == 0).collect(Collectors.toList());
        if (!newItems.isEmpty()) {
            newItems.forEach(item -> item.setId(null));

            addAll(newItems);
        }
    }

    protected void addRelatedTables(final TEntity entity, final TDto dto) throws IException {
    }

    protected void addRelationshipFields(final TEntity entity, final TDto dto) throws IException {
    }

    protected abstract boolean canAccessAllStatus();

    protected abstract Pageable createPageable(final TSearchingDto data);

    protected void deleteRelatedTables(final TEntity entity) throws IException {
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

    protected void updateRelatedTables(final TEntity entity, final TDto dto) throws IException {
    }

    protected void updateRelationshipFields(final TEntity entity, final TDto dto) throws IException {
    }

    protected void validateDataPermission(final TDto dto) throws IException {
        getDomainDataPermissionValidator().validate(dto.getId());
    }
}
