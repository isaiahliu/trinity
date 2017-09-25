package org.trinity.framework.contact.tsykt;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.Arrays;

import org.trinity.framework.contact.AbstractContactMessageDeserializer;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageUtil;

public final class TsyktMessageDeserializer extends AbstractContactMessageDeserializer<ITsyktMessageMeta, ITsyktMessage>
        implements ITsyktMessageDeserializer {
    private ITsyktMessageInstantiator messageInstantiator;

    @Override
    public ITsyktMessage deserializeBody(final ITsyktMessageMeta header, final ByteArrayInputStream messageCodes) {
        ITsyktMessage message = null;
        try {
            message = getMessageInstantiator().createMessage(header.getId());

            deserializeObject(header, message, messageCodes);
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            if (message != null) {
                message.setMeta(header);
            }
        }
        return message;
    }

    @Override
    public ITsyktMessageMeta deserializeHeader(final ByteArrayInputStream messageCodes) {
        messageCodes.skip(13);

        final int id = ContactMessageUtil.read(messageCodes, 2, StoreMethod.BIG_END);
        final ITsyktMessageMeta header = new TsyktMessageMeta(id);

        for (int byteIndex = 0; byteIndex < 8; byteIndex++) {
            int bitmap = ContactMessageUtil.read(messageCodes, 1, StoreMethod.BIG_END);
            for (int pos = 0; pos < 8; pos++) {
                if (bitmap % 2 == 1) {
                    final int pp = byteIndex * 8 + 8 - pos;
                    header.markAvailable(pp);
                }

                bitmap >>= 1;
            }
        }

        return header;
    }

    public ITsyktMessageInstantiator getMessageInstantiator() {
        if (messageInstantiator == null) {
            messageInstantiator = new TsyktMessageInstantiator();
        }
        return messageInstantiator;
    }

    @Override
    protected Field[] getFields(final ITsyktMessageMeta header, final Object object) {
        if (header == null) {
            return Arrays.stream(object.getClass().getDeclaredFields()).sorted((a, b) -> {
                return a.getAnnotation(ContactMessageField.class).order()
                        - b.getAnnotation(ContactMessageField.class).order();
            }).toArray(Field[]::new);
        } else {
            final boolean[] bitmap = header.getBitMap();

            return Arrays.stream(object.getClass().getDeclaredFields()).filter(item -> {
                final ContactMessageField annotation = item.getAnnotation(ContactMessageField.class);
                if (annotation == null) {
                    return false;
                }

                return bitmap[annotation.bitmapPos() - 1];
            }).sorted((a, b) -> {
                return a.getAnnotation(ContactMessageField.class).order()
                        - b.getAnnotation(ContactMessageField.class).order();
            }).toArray(Field[]::new);
        }
    }
}
