package org.trinity.common.exception;

import java.util.ArrayList;
import java.util.List;

import org.trinity.common.util.Tuple2;
import org.trinity.message.exception.GeneralErrorMessage;
import org.trinity.message.exception.IErrorMessage;

/**
 * Base class of all business exception types.
 *
 * @author Isaiah Liu
 *
 * @see Exception
 */
public abstract class IException extends Exception {
    private static final long serialVersionUID = 1L;

    private List<Tuple2<IErrorMessage, String[]>> errorMessages;

    public IException() {
        super();
    }

    protected IException(final IErrorMessage exceptionMessage, final String... params) {
        addMessage(exceptionMessage, params);
    }

    protected IException(final String message) {
        addMessage(GeneralErrorMessage.UNKNOWN_EXCEPTION, message);
    }

    protected IException(final Throwable e, final IErrorMessage exceptionMessage, final String... params) {
        super(e instanceof Exception ? e : new Exception(e));

        addMessage(exceptionMessage, params);
    }

    protected IException(final Throwable e, final String message) {
        super(e instanceof Exception ? e : new Exception(e));

        if (!(e instanceof IException)) {
            addMessage(GeneralErrorMessage.UNKNOWN_EXCEPTION, message);
        }
    }

    /**
     * Add a new message.
     *
     * @param exceptionMessage
     * @param params
     */
    public void addMessage(final IErrorMessage exceptionMessage, final String... params) {
        getErrorMessages().add(new Tuple2<IErrorMessage, String[]>(exceptionMessage, params));
    }

    public List<Tuple2<IErrorMessage, String[]>> getErrorMessages() {
        if (errorMessages == null) {
            errorMessages = new ArrayList<Tuple2<IErrorMessage, String[]>>();
        }
        return errorMessages;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
