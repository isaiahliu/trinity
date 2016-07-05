package org.trinity.yqyl.common.message;

import org.trinity.message.GeneralMessageType;
import org.trinity.message.IMessageType;

public enum LookupType implements IMessageType {
    NA("NA"),
    USER_STATUS("URSTAT"),
    ACCESS_RIGHT("ACCESS"),
    TOKEN_STATUS("TKSTAT"),
    SYSTEM_ATTRIBUTE_KEY("SYSKEY"),
    VALUE_TYPE("VALTYP"),
    RECORD_STATUS("RCSTAT");

    private String typeName;

    private LookupType(final String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String getMessageTypeName() {
        return typeName;
    }

    @Override
    public IMessageType getParentType() {
        return GeneralMessageType.LOOKUP;
    }
}
