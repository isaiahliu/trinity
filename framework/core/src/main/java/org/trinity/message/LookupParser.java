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

	@SafeVarargs
	public static void addEnumLookups(final Class<? extends ILookupMessage<?>>... types) {

		for (final Class<? extends ILookupMessage<?>> type : types) {
			if (!type.isEnum()) {
				continue;
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

	public static Object parseObject(final Class<?> type, final String name) {
		if (!ILookupMessage.class.isAssignableFrom(type)) {
			return null;
		}

		if (type.isEnum()) {
			return parseEnumObject(type, name);
		} else {
			return parseFreeTextObject(type, name);
		}
	}

	private static Object parseEnumObject(final Class<?> type, final String name) {
		final Map<String, ILookupMessage<?>> valueMap = messageMap.get(type);
		if (valueMap == null) {
			return null;
		}

		Object result = valueMap.getOrDefault(name, null);

		if (result == null) {
			result = valueMap.getOrDefault("NA", null);
		}
		return result;
	}

	private static Object parseFreeTextObject(final Class<?> type, final String name) {
		ILookupMessage<?> newInstance;
		try {
			newInstance = (ILookupMessage<?>) (type.newInstance());
			newInstance.setMessageCode(name);
			return newInstance;
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}

	private LookupParser() {
	}
}
