package org.trinity.yqyl.process.controller.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.trinity.common.accessright.ISecurityUtil;
import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.IPagingDto;
import org.trinity.common.dto.object.ISearchingDto;
import org.trinity.common.exception.IException;
import org.trinity.common.exception.factory.IExceptionFactory;
import org.trinity.message.exception.IErrorMessage;
import org.trinity.process.controller.AbstractCrudProcessController;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.process.datapermission.IDataPermissionValidatorProvider;
import org.trinity.repository.repository.IJpaRepository;
import org.trinity.yqyl.common.message.lookup.AccessRight;

public abstract class AbstractAutowiredCrudProcessController<TEntity, TDto extends AbstractBusinessDto, TSearchingDto extends ISearchingDto, TRepository extends IJpaRepository<TEntity, TSearchingDto>>
        extends AbstractCrudProcessController<TEntity, TDto, TSearchingDto> {

    private final Class<TEntity> domainEntityType;
    private final IErrorMessage noInstanceFoundError;

    @Autowired
    private ISecurityUtil<AccessRight> securityUtil;

    @Autowired
    private IObjectConverter<IPagingDto, Pageable> pagingConverter;

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

    public void setPagingConverter(final IObjectConverter<IPagingDto, Pageable> pagingConverter) {
        this.pagingConverter = pagingConverter;
    }

    private String getCurrentUsername() {
        try {
            return getSecurityUtil().getCurrentToken().getUsername();
        } catch (final IException e) {
            return null;
        }
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

    protected IObjectConverter<IPagingDto, Pageable> getPagingConverter() {
        return pagingConverter;
    }

    @Override
    protected Pageable prepareSearch(final TSearchingDto data) {
        data.setCurrentUsername(getCurrentUsername());

        return getPagingConverter().convert(data);
    }

    protected void setSecurityUtil(final ISecurityUtil<AccessRight> securityUtil) {
        this.securityUtil = securityUtil;
    }
}
