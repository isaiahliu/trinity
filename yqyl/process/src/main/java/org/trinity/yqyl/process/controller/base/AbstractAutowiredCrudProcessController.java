package org.trinity.yqyl.process.controller.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.trinity.common.accessright.ISecurityUtil;
import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.IDto;
import org.trinity.common.dto.object.PagingDto;
import org.trinity.common.exception.factory.IExceptionFactory;
import org.trinity.message.exception.IErrorMessage;
import org.trinity.process.controller.AbstractCrudProcessController;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.process.datapermission.IDataPermissionValidatorProvider;
import org.trinity.yqyl.common.message.lookup.AccessRight;

public abstract class AbstractAutowiredCrudProcessController<TEntity, TDto extends AbstractBusinessDto, TSearchingDto extends IDto, TRepository extends CrudRepository<TEntity, Long>>
        extends AbstractCrudProcessController<TEntity, TDto, TSearchingDto> {

    private final Class<TEntity> domainEntityType;
    private final IErrorMessage noInstanceFoundError;

    @Autowired
    private ISecurityUtil<AccessRight> securityUtil;

    @Autowired
    private IObjectConverter<PagingDto, Pageable> pagingConverter;

    @Autowired
    private TRepository domainEntityRepository;

    @Autowired
    private IObjectConverter<TEntity, TDto> domainObjectConverter;

    @Autowired
    private IDataPermissionValidatorProvider dataPermissionValidatorProvider;

    @Autowired
    private IExceptionFactory exceptionFactory;

    protected AbstractAutowiredCrudProcessController(final Class<TEntity> domainEntityType, final IErrorMessage noInstanceFoundError) {
        super();
        this.domainEntityType = domainEntityType;
        this.noInstanceFoundError = noInstanceFoundError;
    }

    public ISecurityUtil<AccessRight> getSecurityUtil() {
        return securityUtil;
    }

    public void setPagingConverter(final IObjectConverter<PagingDto, Pageable> pagingConverter) {
        this.pagingConverter = pagingConverter;
    }

    @Override
    protected IDataPermissionValidatorProvider getDataPermissionValidatorProvider() {
        return dataPermissionValidatorProvider;
    }

    @Override
    protected TRepository getDomainEntityRepository() {
        return domainEntityRepository;
    }

    @Override
    protected Class<TEntity> getDomainEntityType() {
        return domainEntityType;
    }

    @Override
    protected IObjectConverter<TEntity, TDto> getDomainObjectConverter() {
        return domainObjectConverter;
    }

    @Override
    protected IExceptionFactory getExceptionFactory() {
        return exceptionFactory;
    }

    @Override
    protected IErrorMessage getNoInstanceFoundError() {
        return noInstanceFoundError;
    }

    protected IObjectConverter<PagingDto, Pageable> getPagingConverter() {
        return pagingConverter;
    }

    protected void setSecurityUtil(final ISecurityUtil<AccessRight> securityUtil) {
        this.securityUtil = securityUtil;
    }
}
