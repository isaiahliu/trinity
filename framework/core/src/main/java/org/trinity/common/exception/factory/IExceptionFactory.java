package org.trinity.common.exception.factory;

import org.trinity.common.exception.IException;
import org.trinity.message.exception.IExceptionMessage;

/**
 * @author Isaiah Liu
 *
 *         The exception factory to generate exceptions
 */
public interface IExceptionFactory {
    IException createException(IExceptionMessage exceptionMessage, String... params);

    IException createException(String message);

    IException createException(Throwable e, IExceptionMessage exceptionMessage, String... params);

    IException createException(Throwable e, String message);
}
