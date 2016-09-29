package org.trinity.yqyl.common.message.lookup;

import org.trinity.message.ILookupMessage;

public enum FamilyRelationship implements ILookupMessage<LookupType> {
    FATHER("FTR"),
    MOTHER("MTR"),
    SON("SON"),
    DAUGHTER("DAU"),
    HUSBAND("HUS"),
    WIFE("WIF");

    private final String messageCode;

    private FamilyRelationship(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public LookupType getMessageType() {
        return LookupType.FAMILY_RELATIONSHIP;
    }

}
