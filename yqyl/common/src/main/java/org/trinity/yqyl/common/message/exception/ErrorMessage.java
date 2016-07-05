package org.trinity.yqyl.common.message.exception;

import org.trinity.message.exception.IExceptionMessage;

public enum ErrorMessage implements IExceptionMessage {
	TOKEN_IS_EXPIRED, LOGGED_BY_OTHERS, USER_IS_DISABLED, PASSWORD_CHANGED;

	@Override
	public String getMessageCode() {
		return name();
	}
}
