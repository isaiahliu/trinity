package org.trinity.framework.contact;

import org.trinity.framework.contact.ContactMessageField.FieldType;

public interface IAdditionalMessageKey {
    public static class DefaultAdditionalMessageKey implements IAdditionalMessageKey {
        private final int defaultLength;

        private final int id;

        public DefaultAdditionalMessageKey(final int id, final int defaultLength) {
            super();
            this.id = id;
            this.defaultLength = defaultLength;
        }

        @Override
        public int getDefaultLength() {
            return defaultLength;
        }

        @Override
        public int getId() {
            return id;
        }
    }

    public static class UnknownAdditionalMessageType {
        @ContactMessageField(fieldType = FieldType.BCD, order = 1)
        private String data;
    }

    int getDefaultLength();

    default FieldType getFieldType() {
        return FieldType.BCD;
    }

    int getId();

    default Class<?> getMessageType() {
        return UnknownAdditionalMessageType.class;
    }
}
