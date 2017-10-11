package org.trinity.framework.contact;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Function;

import org.trinity.common.util.Tuple3;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField.FieldType;
import org.trinity.framework.contact.ContactMessageStructField.StructFieldType;

public abstract class AbstractContactMessageSerializer<TMessageMeta extends IContactMessageMeta, TMessage extends IContactMessage<TMessageMeta>>
        implements IContactMessageSerializer<TMessageMeta, TMessage> {

    protected void extractField(final ByteArrayOutputStream output, final FieldType fieldType, final Object value,
            final int length, final char padLetter, final StoreMethod storeMethod, final TMessageMeta meta) {
        extractField(output, fieldType, value, length, padLetter, storeMethod, meta, item -> {
        }, item -> null);
    }

    protected void extractField(final ByteArrayOutputStream output, final FieldType fieldType, Object value,
            final int length, final char padLetter, final StoreMethod storeMethod, final TMessageMeta meta,
            final Consumer<Object> cacheSetterDelegate, final Function<String, Object> cacheGetterDelegate) {
        switch (fieldType) {
        case BYTE: {
            if (value == null) {
                value = 0;
            }

            if (value != null) {
                if (value instanceof IContactMessageFieldType) {
                    final int v = ((IContactMessageFieldType) value).getValue();
                    cacheSetterDelegate.accept(v);
                    ContactMessageUtil.write(output, v, 1, storeMethod);
                } else {
                    cacheSetterDelegate.accept((int) value);
                    ContactMessageUtil.write(output, (int) value, 1, storeMethod);
                }
            }
            break;
        }
        case NBYTE:
        case UTF8: {
            if (value == null) {
                value = "";
            }

            if (value != null) {
                String str = value.toString();
                final int emptyCount = length - str.length();

                if (emptyCount < 0) {
                    str = str.substring(0, str.length() + emptyCount);
                }

                for (int i = 0; i < emptyCount; i++) {
                    output.write((byte) padLetter);
                }

                for (final char c : str.toCharArray()) {
                    output.write((byte) c);
                }
            }
            break;
        }
        case WORD: {
            if (value == null) {
                value = 0;
            }

            if (value != null) {
                if (value instanceof IContactMessageFieldType) {
                    final int v = ((IContactMessageFieldType) value).getValue();
                    cacheSetterDelegate.accept(v);
                    ContactMessageUtil.write(output, v, 2, storeMethod);
                } else {
                    cacheSetterDelegate.accept((int) value);
                    ContactMessageUtil.write(output, (int) value, 2, storeMethod);
                }
            }
            break;
        }
        case DWORD: {
            if (value == null) {
                value = 0l;
            }

            if (value != null) {
                if (value instanceof IContactMessageFieldType) {
                    final int v = ((IContactMessageFieldType) value).getValue();
                    cacheSetterDelegate.accept(v);
                    ContactMessageUtil.write(output, v, 4, storeMethod);
                } else {
                    cacheSetterDelegate.accept((int) value);
                    ContactMessageUtil.write(output, (int) value, 4, storeMethod);
                }
            }
            break;
        }
        case STRING: {
            if (value == null) {
                value = "";
            }

            if (value != null) {
                final Charset charset = Charset.forName("GBK");
                final ByteBuffer buffer = charset.encode(value.toString());
                if (length > 0) {
                    final int remaining = buffer.remaining();
                    if (length <= remaining) {
                        for (int i = 0; i < length; i++) {
                            output.write(buffer.get());
                        }
                    } else {
                        while (buffer.hasRemaining()) {
                            output.write(buffer.get());
                        }
                        for (int i = 0; i < length - remaining; i++) {
                            output.write(padLetter);
                        }
                    }
                } else {
                    while (buffer.hasRemaining()) {
                        output.write(buffer.get());
                    }
                }
            }
            break;
        }
        case BCD: {
            if (value == null) {
                value = "";
            }

            if (value != null) {
                final int bcdLength = length * 2;

                final StringBuilder str = new StringBuilder();

                if (value.toString().length() > bcdLength) {
                    str.append(value.toString().substring(0, bcdLength));
                } else {
                    for (int i = 0; i < bcdLength - value.toString().length(); i++) {
                        str.append(padLetter);
                    }
                    str.append(value.toString());
                }

                for (int i = 0; i < bcdLength; i = i + 2) {
                    final String bcd = str.substring(i, i + 2);
                    output.write(Integer.valueOf(bcd, 16));
                }
            }
            break;
        }
        case VAR_BCD: {
            if (value == null) {
                value = "";
            }

            if (value != null) {
                String bcdValue = value.toString();
                final int bcdLength = bcdValue.length();
                if (bcdLength % 2 == 1) {
                    bcdValue += padLetter;
                }

                bcdValue = String.format("%02d", bcdLength) + bcdValue;

                for (int i = 0; i < bcdValue.length(); i = i + 2) {
                    final String bcd = bcdValue.substring(i, i + 2);
                    output.write(Integer.valueOf(bcd, 16));
                }
            }

            cacheSetterDelegate.accept(value);
        }
            break;
        case LLVAR_BCD: {
            if (value == null) {
                value = "";
            }

            if (value != null) {
                String bcdValue = value.toString();
                final int bcdLength = bcdValue.length();
                if (bcdLength % 2 == 1) {
                    bcdValue += padLetter;
                }

                final StringBuilder str = new StringBuilder();

                bcdValue = String.format("%04d", bcdLength) + bcdValue;

                for (int i = 0; i < bcdLength; i = i + 2) {
                    final String bcd = str.substring(i, i + 2);
                    output.write(Integer.valueOf(bcd, 16));
                }
            }
        }
            break;
        case STRUCT: {
            if (value == null) {
                for (int i = 0; i < length; i++) {
                    output.write(0);
                }

                break;
            }

            int result = 0;
            final Field[] structFields = Arrays.stream(value.getClass().getDeclaredFields())
                    .filter(item -> item.getAnnotation(ContactMessageStructField.class) != null).sorted((a, b) -> {
                        return b.getAnnotation(ContactMessageStructField.class).order()
                                - a.getAnnotation(ContactMessageStructField.class).order();
                    }).toArray(Field[]::new);

            final Map<String, Object> structCache = new HashMap<>();

            for (final Field structField : structFields) {
                final ContactMessageStructField structAnnotation = structField
                        .getAnnotation(ContactMessageStructField.class);

                Object structFieldValue = ContactMessageUtil.getFieldValue(value, structField);

                final String structRequiredFrom = structAnnotation.getRequiredFrom();
                boolean structRequired = structAnnotation.required();
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
                    structCache.put(structField.getName(), structFieldValue);

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

                final StructFieldType structFieldType = structAnnotation.fieldType();

                switch (structFieldType) {
                case FLAG: {
                    if (structFieldValue == null) {
                        structFieldValue = false;
                    }

                    result <<= 1;
                    result |= (boolean) structFieldValue ? 1 : 0;

                    structCache.put(structField.getName(), structFieldValue);
                }
                    break;
                case NBIT: {
                    if (structFieldValue == null) {
                        structFieldValue = 0;
                    }

                    int temp = 0;
                    for (int i = 0; i < structLength; i++) {
                        temp <<= 1;
                        temp |= 1;
                    }

                    result <<= structLength;
                    result |= (int) structFieldValue & temp;

                    structCache.put(structField.getName(), structFieldValue);
                }
                    break;
                case NIL: {
                    result <<= structLength;
                }
                    break;
                default:
                    break;
                }
            }

            ContactMessageUtil.write(output, result, length, storeMethod);
        }
            break;
        case BYTEARRAY: {
            try {
                output.write((byte[]) value);
            } catch (final IOException e) {
            }
            break;
        }
        case COMPONENT: {
            if (value == null) {
                break;
            }
            serializeObject(null, value, output);
        }
            break;
        case ADDITIONALS:
            if (value == null) {
                break;
            }

            @SuppressWarnings("unchecked")
            final List<Tuple3<IAdditionalMessageKey, Integer, Object>> additionalObject = (List<Tuple3<IAdditionalMessageKey, Integer, Object>>) value;

            additionalObject.stream().forEach(item -> {
                final ByteArrayOutputStream temp = new ByteArrayOutputStream();
                ContactMessageUtil.write(output, item.getItem1().getId(), length, storeMethod);

                switch (item.getItem1().getFieldType()) {
                case WORD:
                case BCD:
                case BYTE:
                case DWORD:
                case NBYTE:
                case STRING:
                case STRUCT:
                    extractField(temp, item.getItem1().getFieldType(), item.getItem3(),
                            item.getItem1().getDefaultLength(), '0', storeMethod, meta);
                    break;
                case COMPONENT:
                    serializeObject(null, item.getItem3(), temp);
                    break;
                default:
                case ADDITIONALS:
                    break;
                }

                ContactMessageUtil.write(output, temp.size(), 1, storeMethod);
                try {
                    temp.writeTo(output);
                } catch (final Exception e) {
                }
            });

            break;
        case MAC:
            final byte[] mac = getMac(meta, output);
            if (mac.length != 0) {
                output.write(mac, 0, mac.length);
            }

            break;
        case PASSWORD:
            final byte[] pin = new byte[8];

            String password = value.toString();

            if (password.length() > 14) {
                password = password.substring(0, 14);
            }

            pin[0] = (byte) password.length();

            for (int i = 0; i < 14; i += 2) {
                int temp = 0;
                if (i < password.length()) {
                    temp |= password.charAt(i) - '0';
                } else {
                    temp |= 0x0F;
                }

                temp <<= 4;

                if (i + 1 < password.length()) {
                    temp |= password.charAt(i + 1) - '0';
                } else {
                    temp |= 0x0F;
                }

                pin[i / 2 + 1] = (byte) temp;
            }

            final byte[] pan = new byte[8];

            String account = cacheGetterDelegate.apply("account").toString();
            account = account.substring(account.length() - 12);

            for (int i = 0; i < 6; i++) {
                int temp = account.charAt(i * 2) - '0';
                temp <<= 4;
                temp |= account.charAt(i * 2 + 1) - '0';

                pan[i + 2] = (byte) temp;
            }

            for (int i = 0; i < 8; i++) {
                pin[i] ^= pan[i];
            }

            output.write(pin, 0, 8);
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

    protected byte[] getMac(final TMessageMeta meta, final ByteArrayOutputStream output) {
        return new byte[0];
    }

    protected void serializeObject(final TMessageMeta header, final Object message,
            final ByteArrayOutputStream output) {
        final Map<String, Object> cache = new HashMap<>();
        StoreMethod storeMethod = StoreMethod.BIG_END;

        final ContactMessage contactMessageAnnotation = message.getClass().getAnnotation(ContactMessage.class);
        if (contactMessageAnnotation != null) {
            storeMethod = contactMessageAnnotation.storeMethod();
        }

        final Stack<Tuple3<Field, FieldType, ByteArrayOutputStream>> outputStack = new Stack<>();

        Tuple3<Field, FieldType, ByteArrayOutputStream> using = new Tuple3<>(null, null, output);

        for (final Field field : getFields(header, message)) {
            final ContactMessageField annotation = field.getAnnotation(ContactMessageField.class);

            final Object value = ContactMessageUtil.getFieldValue(message, field);

            final String requiredFrom = annotation.getRequiredFrom();
            boolean required = annotation.required();
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
                cache.put(field.getName(), value);

                continue;
            }

            final String lengthFrom = annotation.getLengthFrom();
            int length = annotation.length();
            if (lengthFrom.length() > 0) {
                final Object o = cache.get(lengthFrom);

                if (o != null) {
                    if (o instanceof Integer) {
                        length = (int) o;
                    }
                }
            }

            final char padLetter = annotation.padLetter();
            final FieldType fieldType = annotation.fieldType();

            if (annotation.depends()) {
                outputStack.push(using);

                using = new Tuple3<>(field, fieldType, new ByteArrayOutputStream());

                continue;
            }

            if (using.getItem1() != null) {
                if (annotation.getLengthFrom().equals(using.getItem1().getName())) {
                    outputStack.push(using);

                    using = new Tuple3<>(field, fieldType, new ByteArrayOutputStream());
                }
            }

            if (fieldType == FieldType.COMPONENT_LIST) {
                final List<?> valueList = (List<?>) value;
                for (int i = 0; i < length; i++) {
                    extractField(using.getItem3(), FieldType.COMPONENT, valueList.get(i), 0, padLetter, storeMethod,
                            header);
                }
            } else {
                extractField(using.getItem3(), fieldType, value, length, padLetter, storeMethod, header,
                        item -> cache.put(field.getName(), item), item -> cache.get(item));
            }

            if (using.getItem1() != null) {
                if (using.getItem1() == field) {
                    int dependsValue = 0;
                    final byte[] byteArray = using.getItem3().toByteArray();

                    switch (using.getItem2()) {
                    case COMPONENT_LIST:
                        dependsValue = ((List<?>) value).size();
                        break;
                    case BCD:
                    case NBYTE:
                    case BYTEARRAY:
                    case STRING:
                    case COMPONENT:
                        dependsValue = byteArray.length;
                        break;
                    default:
                        break;
                    }
                    final Tuple3<Field, FieldType, ByteArrayOutputStream> middle = outputStack.pop();

                    final String name = middle.getItem1().getName();

                    using = outputStack.pop();

                    extractField(using.getItem3(), middle.getItem2(), dependsValue, 0, ' ', storeMethod, header,
                            item -> {
                                cache.put(name, item);
                            }, item -> cache.get(item));

                    try {
                        using.getItem3().write(middle.getItem3().toByteArray());
                        using.getItem3().write(byteArray);
                    } catch (final IOException e) {
                    }
                }
            }
        }
    }
}
