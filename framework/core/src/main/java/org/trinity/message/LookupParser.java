package org.trinity.message;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Isaiah Liu
 */
public final class LookupParser {
    private final static Map<Class<? extends ILookupMessage<?>>, Map<String, ILookupMessage<?>>> messageMap;
    private final static Map<ILookupType, Class<? extends ILookupMessage<?>>> lookupBindingMap;
    static {
        messageMap = new HashMap<>();
        lookupBindingMap = new HashMap<>();
    }

    public static Set<Class<? extends ILookupMessage<?>>> getRegisteredType() {
        return messageMap.keySet();
    }

    @SuppressWarnings("unchecked")
    public static <T extends ILookupMessage<?>> T parse(final Class<T> type, final String name) {

        return (T) parseObject(type, name);
    }

    public static Class<? extends ILookupMessage<?>> parseLookupClass(final ILookupType lookupTypeClass) {
        return lookupBindingMap.getOrDefault(lookupTypeClass, null);
    }

    @SuppressWarnings("unchecked")
    public static Object parseObject(final Class<?> type, final String name) {
        if (!ILookupMessage.class.isAssignableFrom(type)) {
            return null;
        }

        if (type.isEnum()) {
            return parseEnumObject((Class<? extends ILookupMessage<?>>) type, name);
        } else {
            return parseFreeTextObject((Class<? extends ILookupMessage<?>>) type, name);
        }
    }

    private static void addEnumLookups(final Class<? extends ILookupMessage<?>> type) {
        if (!type.isEnum()) {
            return;
        }

        final ILookupMessage<?>[] values = type.getEnumConstants();

        final Map<String, ILookupMessage<?>> valueMap = new HashMap<>();

        for (final ILookupMessage<?> item : values) {
            valueMap.put(item.getMessageCode(), item);
            lookupBindingMap.put(item.getMessageType(), type);
        }

        if (values.length != valueMap.size()) {
            throw new IllegalArgumentException("Duplicated Message Code in " + type.getName());
        }

        messageMap.put(type, valueMap);
    }

    private static Object parseEnumObject(final Class<? extends ILookupMessage<?>> type, final String name) {
        Map<String, ILookupMessage<?>> valueMap = messageMap.get(type);
        if (valueMap == null) {
            synchronized (type) {
                if (valueMap == null) {
                    addEnumLookups(type);
                }
            }

            valueMap = messageMap.get(type);

            if (valueMap == null) {
                return null;
            }
        }

        Object result = valueMap.getOrDefault(name, null);

        if (result == null) {
            result = valueMap.getOrDefault("NA", null);
        }
        return result;
    }

    private static Object parseFreeTextObject(final Class<? extends ILookupMessage<?>> type, final String name) {
        ILookupMessage<?> newInstance;
        try {
            newInstance = (type.newInstance());
            newInstance.setMessageCode(name);
            return newInstance;
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    private LookupParser() {
    }
}
