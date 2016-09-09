package org.trinity.common.dto;

import javax.validation.ConstraintViolationException;

/**
 * @author Isaiah Liu
 *
 *         A class to store validation results. If there's such a parameter in the service, the validation error will be
 *         put into the class instead of throwing.
 *
 */
public class ValidationResult {
    private ConstraintViolationException exception;

    public ConstraintViolationException getException() {
        return exception;
    }

    public boolean hasError() {
        return exception != null;
    }

    public void setException(final ConstraintViolationException exception) {
        this.exception = exception;
    }
}
