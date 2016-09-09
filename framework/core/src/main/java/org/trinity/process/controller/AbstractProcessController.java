package org.trinity.process.controller;

import org.trinity.common.exception.factory.IExceptionFactory;
import org.trinity.process.datapermission.IDataPermissionValidatorProvider;

public abstract class AbstractProcessController implements IProcessController {
    protected abstract IDataPermissionValidatorProvider getDataPermissionValidatorProvider();

    protected abstract IExceptionFactory getExceptionFactory();
}
