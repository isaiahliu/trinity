package org.trinity.process.controller;

import org.trinity.common.exception.factory.IExceptionFactory;

public abstract class AbstractProcessController implements IProcessController {
    protected abstract IExceptionFactory getExceptionFactory();
}
