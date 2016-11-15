package org.trinity.framework.contact;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.trinity.framework.contact.ContactMessage.StoreMethod;

public final class ContactMessageUtil {
    public static final int DEFAULT_MAX_SERIAL_NUMBER = 0xffff;
    public static final int DEFAULT_MAX_BODY_LENGTH = 0xffff;
    public static final int DEFAULT_TIMEOUT_SECONDS = 20;
    public static final int DEFAULT_MAX_RETRY_TIMES = 3;

    public static List<Byte> convertByteArrayToList(final byte[] array) {
        final List<Byte> list = new ArrayList<>();

        for (final byte b : array) {
            list.add(b);
        }

        return list;
    }

    public static byte[] convertListToByteArray(final List<Byte> list) {
        final byte[] array = new byte[list.size()];

        int index = 0;
        for (final byte b : list) {
            array[index++] = b;
        }

        return array;
    }

    public static Object getFieldValue(final Object message, final Field field) {
        try {
            final String fieldName = field.getName();
            final StringBuilder getter = new StringBuilder();
            getter.append(String.valueOf(fieldName.charAt(0)).toUpperCase());

            if (fieldName.length() > 1) {
                getter.append(fieldName.substring(1));
            }
            Method getterMethod = null;
            try {
                getterMethod = message.getClass().getDeclaredMethod("get" + getter.toString());
            } catch (final NoSuchMethodException e) {
                getterMethod = message.getClass().getDeclaredMethod("is" + getter.toString());
            }

            getterMethod.setAccessible(true);
            final Object value = getterMethod.invoke(message);

            if (value instanceof IContactMessageFieldType) {
                return ((IContactMessageFieldType) value).getValue();
            } else {
                return value;
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            return 0;
        }
    }

    public static int read(final ByteArrayInputStream input, final int length, final StoreMethod storeMethod) {
        int result = 0;
        if (storeMethod == StoreMethod.BIG_END) {
            for (int index = 0; index < length; index++) {
                result <<= 8;

                final int read = input.read();

                result |= read;
            }
        } else {
            final byte[] bytes = new byte[length];
            input.read(bytes, 0, length);
            for (int i = length - 1; i >= 0; i--) {
                result <<= 8;

                result |= Byte.toUnsignedInt(bytes[i]);
            }
        }
        return result;
    }

    public static void setFieldValue(final Object message, final Field field, final Object value) {
        try {
            final String fieldName = field.getName();
            final StringBuilder setter = new StringBuilder("set");
            setter.append(String.valueOf(fieldName.charAt(0)).toUpperCase());

            if (fieldName.length() > 1) {
                setter.append(fieldName.substring(1));
            }

            final Class<?> fieldType = field.getType();

            final Method setterMethod = message.getClass().getDeclaredMethod(setter.toString(), fieldType);
            setterMethod.setAccessible(true);

            if (value instanceof Integer && fieldType.isEnum()) {
                if (IContactMessageFieldType.class.isAssignableFrom(fieldType)) {
                    for (final Object type : fieldType.getEnumConstants()) {
                        if (((IContactMessageFieldType) type).getValue() == (int) value) {
                            setterMethod.invoke(message, type);
                            return;
                        }
                    }
                }
            } else {
                setterMethod.invoke(message, value);
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
        }
    }

    public static void write(final ByteArrayOutputStream output, int value, final int byteCount,
            final StoreMethod storeMethod) {
        try {
            final byte[] codes = new byte[byteCount];
            if (storeMethod == StoreMethod.BIG_END) {
                for (int index = byteCount - 1; index >= 0; index--) {
                    codes[index] = (byte) (value & 0xff);

                    value >>= 8;
                }
            } else {
                for (int index = 0; index < byteCount; index++) {
                    codes[index] = (byte) (value & 0xff);

                    value >>= 8;
                }
            }
            output.write(codes);
        } catch (final IOException e) {
        }
    }
}
