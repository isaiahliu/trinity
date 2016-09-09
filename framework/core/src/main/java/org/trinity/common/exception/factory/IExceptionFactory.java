package org.trinity.common.exception.factory;

import org.trinity.common.exception.IException;
import org.trinity.message.exception.IErrorMessage;

/**
 * The exception factory to generate exceptions
 *
 * @author Isaiah Liu
 */
public interface IExceptionFactory {
    IException createException(IErrorMessage exceptionMessage, String... params);

    IException createException(String message);

    IException createException(Throwable e, IErrorMessage exceptionMessage, String... params);

    IException createException(Throwable e, String message);
}
