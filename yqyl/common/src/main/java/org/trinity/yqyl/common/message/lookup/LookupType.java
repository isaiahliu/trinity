package org.trinity.yqyl.common.message.lookup;

import org.trinity.message.GeneralMessageType;
import org.trinity.message.ILookupMessage;
import org.trinity.message.ILookupType;
import org.trinity.message.IMessageType;

public enum LookupType implements ILookupType, ILookupMessage<LookupType> {
    NA("NA"),
    LOOKUP("LOOKUP"),
    CURRENCY("CURRENCY"),
    ACCOUNT_STATUS("ACSTAT"),
    FAVORITE_CATEGORY("FVCATE"),
    ACCOUNT_POSTING_STATUS("APSTAT"),
    ACCOUNT_BALANCE_STATUS("ABSTAT"),
    ACCOUNT_BALANCE_CATEGORY("ABCATE"),
    USER_STATUS("URSTAT"),
    ACCESS_RIGHT("ACCESS"),
    TOKEN_STATUS("TKSTAT"),
    SYSTEM_ATTRIBUTE_KEY("SYSKEY"),
    VALUE_TYPE("VALTYP"),
    RECORD_STATUS("RCSTAT"),
    ROLE_NAME("RLNAME"),
    TRANSACTION_CATEOGY("TXCATE"),
    PERSONAL_TYPE("PSTYPE"),
    OPERATOR_CLIENT_STATUS("OPCSTAT"),
    ANNOUNCEMENT_STATUS("ANSTAT"),
    CLIENT_TYPE("CLTYPE"),
    LANGUAGE("LANGUAGE"),
    MESSAGE_STATUS("MSGSTAT"),
    ORDER_STATUS("ODSTAT"),
    SERVICE_STATUS("SVSTAT"),
    SERVICE_SUPPLIER_CLIENT_STATUS("SSCSTAT"),
    GENDER("GENDER"),
    SERVICE_RECEIVER_CLIENT_STATUS("SRCSTAT"),
    FAMILY_RELATIONSHIP("FARELTN"),
    FLAG_STATUS("FLSTAT"),
    SMOKER_AGE("SMKAGE"),
    FREQUENCY_STATUS("FRQSTAT");

    private String typeName;

    private LookupType(final String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String getMessageCode() {
        return typeName;
    }

    @Override
    public LookupType getMessageType() {
        return LOOKUP;
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
