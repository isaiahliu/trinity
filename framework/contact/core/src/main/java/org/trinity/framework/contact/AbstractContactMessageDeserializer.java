package org.trinity.framework.contact;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.trinity.common.util.Tuple3;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField.FieldType;
import org.trinity.framework.contact.IAdditionalMessageKey.DefaultAdditionalMessageKey;

public abstract class AbstractContactMessageDeserializer<TMessageMeta extends IContactMessageMeta, TMessage extends IContactMessage<TMessageMeta>>
        implements IContactMessageDeserializer<TMessageMeta, TMessage> {

    protected void deserializeObject(final TMessageMeta header, final Object object,
            final ByteArrayInputStream messageCodes) throws Exception, IllegalAccessException {
        final Map<String, Object> cache = new HashMap<>();

        StoreMethod storeMethod = StoreMethod.BIG_END;

        final ContactMessage contactMessageAnnotation = object.getClass().getAnnotation(ContactMessage.class);
        if (contactMessageAnnotation != null) {
            storeMethod = contactMessageAnnotation.storeMethod();
        }

        final Field[] fields = getFields(header, object);

        for (final Field field : fields) {
            final ContactMessageField annotation = field.getAnnotation(ContactMessageField.class);

            boolean required = annotation.required();
            final String requiredFrom = annotation.getRequiredFrom();

            if (requiredFrom.length() > 0) {
                final Object o = cache.get(requiredFrom);

                if (o != null) {
                    if (o instanceof Boolean) {
                        required = (boolean) o;
                    } else if (o instanceof Integer) {
                        required = (int) o > 0;
                    } else if (o instanceof IContactMessageFieldType) {
                        required = ((IContactMessageFieldType) o).getValue() > 0;
                    }
                }
            }
            if (!required) {
                cache.put(field.getName(), ContactMessageUtil.getFieldValue(object, field));

                continue;
            }

            final String lengthFrom = annotation.getLengthFrom();
            int length = annotation.length();
            if (lengthFrom.length() > 0) {
                final Object o = cache.get(lengthFrom);

                if (o != null) {
                    if (o instanceof Integer) {
                        length = (int) o;
                    } else {
                        length = Integer.parseInt(o.toString());
                    }
                }
            }

            final FieldType fieldType = annotation.fieldType();
            final char padLetter = annotation.padLetter();
            final Class<?> additionalEnumClass = annotation.additionalEnumClass();
            final Class<?> fieldClass = field.getType();

            if (fieldType == FieldType.COMPONENT_LIST) {
                final Class<?> type = (Class<?>) ((ParameterizedType) field.getGenericType())
                        .getActualTypeArguments()[0];

                final List<Object> result = new ArrayList<>();

                for (int i = 0; i < length; i++) {
                    extractField(messageCodes, -1, FieldType.COMPONENT, padLetter, type, additionalEnumClass,
                            storeMethod, item -> {
                                result.add(item);
                            });
                }
                ContactMessageUtil.setFieldValue(object, field, result);

            } else {
                extractField(messageCodes, length, fieldType, padLetter, fieldClass, additionalEnumClass, storeMethod,
                        item -> {
                            ContactMessageUtil.setFieldValue(object, field, item);
                        }, item -> cache.put(field.getName(), item));
            }
        }
    }

    protected void deserializeStructObject(final Object structObject, int result) {
        final Map<String, Object> structCache = new HashMap<>();

        final Field[] structFields = Arrays.stream(structObject.getClass().getDeclaredFields())
                .filter(item -> item.getAnnotation(ContactMessageStructField.class) != null).sorted((a, b) -> {
                    return a.getAnnotation(ContactMessageStructField.class).order()
                            - b.getAnnotation(ContactMessageStructField.class).order();
                }).toArray(Field[]::new);

        for (final Field structField : structFields) {
            final ContactMessageStructField structAnnotation = structField
                    .getAnnotation(ContactMessageStructField.class);

            boolean structRequired = structAnnotation.required();
            final String structRequiredFrom = structAnnotation.getRequiredFrom();

            if (structRequiredFrom.length() > 0) {
                final Object o = structCache.get(structRequiredFrom);

                if (o != null) {
                    if (o instanceof Boolean) {
                        structRequired = (boolean) o;
                    } else if (o instanceof Integer) {
                        structRequired = (int) o > 0;
                    } else if (o instanceof IContactMessageFieldType) {
                        structRequired = ((IContactMessageFieldType) o).getValue() > 0;
                    }
                }
            }
            if (!structRequired) {
                structCache.put(structField.getName(), ContactMessageUtil.getFieldValue(structObject, structField));
                continue;
            }

            final String structLengthFrom = structAnnotation.getLengthFrom();
            int structLength = structAnnotation.length();
            if (structLengthFrom.length() > 0) {
                final Object o = structCache.get(structLengthFrom);

                if (o != null) {
                    if (o instanceof Integer) {
                        structLength = (int) o;
                    }
                }
            }

            switch (structAnnotation.fieldType()) {
            case FLAG: {
                final int flag = result & 0x1;
                result >>= 1;
                structCache.put(structField.getName(), flag > 0);
                ContactMessageUtil.setFieldValue(structObject, structField, flag > 0);

            }
                break;
            case NBIT: {
                int temp = 0;
                for (int i = 0; i < structLength; i++) {
                    temp <<= 1;
                    temp |= 1;
                }

                final int value = result & temp;

                result >>= structLength;

                structCache.put(structField.getName(), result);
                ContactMessageUtil.setFieldValue(structObject, structField, value);
            }
                break;
            case NIL:
                result >>= structLength;
                break;
            }
        }
    }

    protected void extractField(final ByteArrayInputStream messageCodes, final int length, final FieldType fieldType,
            final char padLetter, final Class<?> fieldClass, final Class<?> additionalEnumClass,
            final StoreMethod storeMethod, final Consumer<Object> setter) throws Exception {
        extractField(messageCodes, length, fieldType, padLetter, fieldClass, additionalEnumClass, storeMethod, setter,
                item -> {
                });
    }

    protected void extractField(final ByteArrayInputStream messageCodes, final int length, final FieldType fieldType,
            final char padLetter, final Class<?> fieldClass, final Class<?> additionalEnumClass,
            final StoreMethod storeMethod, final Consumer<Object> setter, final Consumer<Object> cacheDelegate)
            throws Exception {
        switch (fieldType) {
        case BYTE: {
            final int result = ContactMessageUtil.read(messageCodes, 1, storeMethod);

            setter.accept(result);
            cacheDelegate.accept(result);
            break;
        }
        case NBYTE: {
            final char[] chars = new char[length];
            for (int i = 0; i < length; i++) {
                chars[i] = (char) (byte) messageCodes.read();
            }

            setter.accept(new String(chars).trim());
            break;
        }
        case WORD: {
            final int result = ContactMessageUtil.read(messageCodes, 2, storeMethod);

            setter.accept(result);
            cacheDelegate.accept(result);
            break;
        }
        case DWORD: {
            final int result = ContactMessageUtil.read(messageCodes, 4, storeMethod);

            setter.accept(result);
            cacheDelegate.accept(result);
            break;
        }
        case STRING: {
            final Charset charset = Charset.forName("GBK");

            final ByteArrayOutputStream tempStream = new ByteArrayOutputStream();

            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    tempStream.write(messageCodes.read());
                }
            } else {
                int b;
                while ((b = messageCodes.read()) != -1) {

                    tempStream.write(b);
                }
            }

            final ByteBuffer buffer = ByteBuffer.wrap(tempStream.toByteArray());

            buffer.position(0);

            setter.accept(charset.decode(buffer).toString());
            break;
        }
        case BCD: {
            final StringBuilder str = new StringBuilder();
            for (int i = 0; i < length; i++) {
                final String temp = Integer.toHexString(ContactMessageUtil.read(messageCodes, 1, storeMethod));

                if (temp.length() == 1) {
                    str.append(padLetter);
                }

                str.append(temp);
            }

            setter.accept(str.toString());
            cacheDelegate.accept(str.toString());
            break;
        }
        case STRUCT: {
            int result = 0;
            for (int i = 0; i < length; i++) {
                result <<= 8;
                result |= ContactMessageUtil.read(messageCodes, 1, storeMethod) & 0xff;
            }
            final Object structObject = fieldClass.newInstance();
            setter.accept(structObject);

            deserializeStructObject(structObject, result);
            break;
        }
        case BYTEARRAY: {
            byte[] result;
            if (length < 0) {
                result = new byte[messageCodes.available()];
            } else {
                result = new byte[length];
            }
            messageCodes.read(result);
            setter.accept(result);
            break;
        }
        case ADDITIONALS: {
            final List<Tuple3<IAdditionalMessageKey, Integer, Object>> additionals = new LinkedList<>();

            setter.accept(additionals);

            while (messageCodes.available() > 0) {
                IAdditionalMessageKey temp = null;
                final int additionalMessageId = ContactMessageUtil.read(messageCodes, length, storeMethod);
                final int additionalMessageLength = messageCodes.read();
                for (final Object o : additionalEnumClass.getEnumConstants()) {
                    final IAdditionalMessageKey item = (IAdditionalMessageKey) o;

                    if (item.getId() == additionalMessageId) {
                        temp = item;
                        break;
                    }
                }

                if (temp == null) {
                    temp = new DefaultAdditionalMessageKey(additionalMessageId, additionalMessageLength);
                }

                final IAdditionalMessageKey messageKey = temp;
                final FieldType additionalMessageFieldType = messageKey.getFieldType();
                final Class<?> messageType = messageKey.getMessageType();

                switch (additionalMessageFieldType) {
                case STRUCT: {
                    final Object additionalMessageObject = messageType.newInstance();
                    final int result = ContactMessageUtil.read(messageCodes, additionalMessageLength, storeMethod);
                    deserializeStructObject(additionalMessageObject, result);

                    additionals.add(new Tuple3<>(messageKey, additionalMessageLength, additionalMessageObject));
                }
                    break;
                case COMPONENT: {
                    final Object additionalMessageObject = messageType.newInstance();
                    deserializeObject(null, additionalMessageObject, messageCodes);

                    additionals.add(new Tuple3<>(messageKey, additionalMessageLength, additionalMessageObject));
                }
                    break;
                case BCD:
                case BYTE:
                case DWORD:
                case NBYTE:
                case STRING:
                case WORD: {
                    extractField(messageCodes, additionalMessageLength, additionalMessageFieldType, '0', Object.class,
                            Object.class, storeMethod, item -> {
                                Object v = item;
                                if (messageType != Object.class
                                        && IContactMessageFieldType.class.isAssignableFrom(messageType)) {
                                    for (final Object o : messageType.getEnumConstants()) {
                                        final IContactMessageFieldType f = (IContactMessageFieldType) o;
                                        if (v.equals(f.getValue())) {
                                            v = o;
                                            break;
                                        }
                                    }
                                }
                                additionals.add(new Tuple3<>(messageKey, additionalMessageLength, v));
                            });
                }
                    break;
                case ADDITIONALS:
                default:
                    break;
                }
            }

            break;
        }
        case COMPONENT: {
            final Object compObject = fieldClass.newInstance();
            setter.accept(compObject);

            if (length >= 0) {
                final byte[] componentBytes = new byte[length];

                messageCodes.read(componentBytes);

                deserializeObject(null, compObject, new ByteArrayInputStream(componentBytes));
            } else {
                deserializeObject(null, compObject, messageCodes);
            }
            break;
        }

        case VAR_BCD: {
            final int varLength = Integer
                    .parseInt(Integer.toHexString(ContactMessageUtil.read(messageCodes, 1, storeMethod)));

            final StringBuilder str = new StringBuilder();
            for (int i = 0; i < varLength; i += 2) {
                String temp = Integer.toHexString(ContactMessageUtil.read(messageCodes, 1, storeMethod));
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }

                str.append(temp.charAt(0));
                if (i < varLength - 1) {
                    str.append(temp.charAt(1));
                }
            }

            setter.accept(str.toString());
        }
            break;
        case LLVAR_BCD: {
            final int varLength = Integer
                    .parseInt(Integer.toHexString(ContactMessageUtil.read(messageCodes, 2, storeMethod)));

            final StringBuilder str = new StringBuilder();
            for (int i = 0; i < varLength; i += 2) {
                String temp = Integer.toHexString(ContactMessageUtil.read(messageCodes, 1, storeMethod));
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }

                str.append(temp.charAt(0));
                if (i < varLength - 1) {
                    str.append(temp.charAt(1));
                }
            }

            setter.accept(str.toString());
        }
            break;
        default:
            break;
        }
    }

    protected Field[] getFields(final TMessageMeta header, final Object object) {
        return Arrays.stream(object.getClass().getDeclaredFields())
                .filter(item -> item.getAnnotation(ContactMessageField.class) != null).sorted((a, b) -> {
                    return a.getAnnotation(ContactMessageField.class).order()
                            - b.getAnnotation(ContactMessageField.class).order();
                }).toArray(Field[]::new);
    }
}
