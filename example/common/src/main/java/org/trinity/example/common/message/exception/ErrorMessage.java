package org.trinity.example.common.message.exception;

import org.trinity.message.exception.IExceptionMessage;

public enum ErrorMessage implements IExceptionMessage {
    EMPTY_REQUEST;

    @Override
    public String getMessageCode() {
        return name();
    }
}
