package org.trinity.message;

/**
 * @author Isaiah Liu
 *
 */
public interface ILookupType extends IMessageType {
	@Override
	default IMessageType getParentType() {
		return GeneralMessageType.LOOKUP;
	}

	Class<? extends ILookupMessage<? extends ILookupType>> getTargetType();
}
