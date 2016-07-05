package org.trinity.yqyl.common.message.lookup;

import java.util.HashMap;
import java.util.Map;

import org.trinity.common.accessright.IAccessRight;
import org.trinity.message.IMessageType;
import org.trinity.yqyl.common.message.LookupType;

public enum AccessRight implements IAccessRight {
    NA("NA"),
    SUPERUSER("0000"),
    UPDATE_VEHICLE("0001"),
    ADD_VEHICLE("0002"),
    DELETE_VEHICLE("0003");

    private static Map<String, AccessRight> map = new HashMap<>();

    static {
        for (final AccessRight accessRight : AccessRight.values()) {
            map.put(accessRight.getMessageCode().toUpperCase(), accessRight);
        }
    }

    public static AccessRight forName(final String name) {
        if (name == null) {
            return null;
        }
        return map.get(name.toUpperCase());
    }

    private final String messageCode;

    private AccessRight(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public IMessageType getMessageType() {
        return LookupType.ACCESS_RIGHT;
    }
}
