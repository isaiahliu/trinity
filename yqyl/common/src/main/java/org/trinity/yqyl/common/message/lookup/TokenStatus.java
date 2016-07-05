package org.trinity.yqyl.common.message.lookup;

import org.trinity.message.IMessage;
import org.trinity.message.IMessageType;
import org.trinity.yqyl.common.message.LookupType;

public enum TokenStatus implements IMessage {
	AUTHENTICATED("A"),
	EXPIRED("E"),
	UNAUTHENTICATED("U"),
	LOGGED_BY_OTHERS("L"),
	PASSWORD_CHANGED("C");

	private final String messageCode;

	private TokenStatus(final String messageCode) {
		this.messageCode = messageCode;
	}

	@Override
	public String getMessageCode() {
		return messageCode;
	}

	@Override
	public IMessageType getMessageType() {
		return LookupType.TOKEN_STATUS;
	}

}
