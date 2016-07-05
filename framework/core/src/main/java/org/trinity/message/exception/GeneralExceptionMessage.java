package org.trinity.message.exception;

/**
 * @author Isaiah Liu
 *
 *         Some generic exception messages.
 */
public enum GeneralExceptionMessage implements IExceptionMessage {
    UNKNOWN_EXCEPTION;

    @Override
    public String getMessageCode() {
        return name();
    }
}
