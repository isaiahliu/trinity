package org.trinity.message.exception;

/**
 * Some generic error messages.
 *
 * @author Isaiah Liu
 */
public enum GeneralErrorMessage implements IErrorMessage {
    UNKNOWN_EXCEPTION,
    NOT_ALL_ACCESS_IS_GRANTED,
    NONE_ACCESS_IS_GRANTED,
    TOKEN_IS_MISSING,
    SUPER_USER_IS_REQUIRED,
    AUTHERIZATION_DISABLED;

    @Override
    public String getMessageCode() {
        return name();
    }
}
