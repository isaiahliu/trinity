package org.trinity.yqyl.rest.accessright;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.trinity.message.IMessage;
import org.trinity.message.IMessageType;
import org.trinity.yqyl.common.message.LookupType;

public enum AccessRight implements GrantedAuthority, IMessage {
    NA("NA"),
    SUPER_USER("SYS001");

    private static Map<String, AccessRight> map;
    static {
        map = new HashMap<>();
        for (final AccessRight item : values()) {
            map.put(item.getMessageCode(), item);
        }
    }

    public static AccessRight forName(final String code) {
        return map.getOrDefault(code, AccessRight.NA);
    }

    private String code;

    private AccessRight(final String code) {
        this.code = code;
    }

    @Override
    public String getAuthority() {
        return getMessageCode();
    }

    @Override
    public String getMessageCode() {
        return code;
    }

    @Override
    public IMessageType getMessageType() {
        return LookupType.ACCESS_RIGHT_NAME;
    }

}
