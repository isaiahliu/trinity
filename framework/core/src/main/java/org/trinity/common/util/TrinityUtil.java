package org.trinity.common.util;

import java.util.UUID;

public final class TrinityUtil {
    public static String randomBase36Uuid() {
        final UUID uuid = UUID.randomUUID();
        return Long.toUnsignedString(uuid.getLeastSignificantBits(), 36) + Long.toUnsignedString(uuid.getMostSignificantBits(), 36);
    }
}
