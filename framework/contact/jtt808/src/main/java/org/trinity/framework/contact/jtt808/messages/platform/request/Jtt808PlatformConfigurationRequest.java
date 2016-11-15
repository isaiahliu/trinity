package org.trinity.framework.contact.jtt808.messages.platform.request;

import java.util.LinkedList;
import java.util.List;

import org.trinity.common.util.Tuple3;
import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageField.FieldType;
import org.trinity.framework.contact.IAdditionalMessageKey;
import org.trinity.framework.contact.IContactMessageFieldType;
import org.trinity.framework.contact.jtt808.messages.terminal.request.Jtt808TerminalRegisterRequest.PlateColor;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class Jtt808PlatformConfigurationRequest extends AbstractJtt808PlatformRequest {
    public static enum PlatformConfigurationRequestAdditionalMessageKey implements IAdditionalMessageKey {
        PULSE_SECONDS(0x0001, 4, FieldType.DWORD),
        POSITION_UPLOAD_POLICY(0x0020, 4, FieldType.DWORD, PositionUploadPolicy.class),
        POSITION_UPLOAD_PLAN(0x0021, 4, FieldType.DWORD, PositionUploadPlan.class),
        DEFAULT_SLEEP_POSTION_UPLOAD_TIME_PERIOD(0x0027, 4, FieldType.DWORD),
        DEFAULT_POSTION_UPLOAD_TIME_PERIOD(0x0029, 4, FieldType.DWORD),
        PROVINCE_ID(0x0081, 4, FieldType.DWORD),
        CITY_ID(0x0082, 4, FieldType.DWORD),
        PLATE_NUMBER(0x0083, 10, FieldType.STRING),
        PLATE_COLOR(0x0084, 1, FieldType.BYTE, PlateColor.class);

        public static enum PositionUploadPlan implements IContactMessageFieldType {
            ON_ACC(0),
            ON_ACC_AND_REGISTER(1);

            private final int value;

            private PositionUploadPlan(final int value) {
                this.value = value;
            }

            @Override
            public int getValue() {
                return value;
            }
        }

        public static enum PositionUploadPolicy implements IContactMessageFieldType {
            ON_TIME(0),
            ON_DISTANCE(1),
            ON_TIME_AND_DISTANCE(2);

            private final int value;

            private PositionUploadPolicy(final int value) {
                this.value = value;
            }

            @Override
            public int getValue() {
                return value;
            }
        }

        private final int id;

        private final int defaultLength;

        private final Class<?> messageType;

        private final FieldType fieldType;

        private PlatformConfigurationRequestAdditionalMessageKey(final int id, final int defaultLength, final FieldType fieldType) {
            this(id, defaultLength, fieldType, Object.class);
        }

        private PlatformConfigurationRequestAdditionalMessageKey(final int id, final int defaultLength, final FieldType fieldType,
                final Class<?> messageType) {
            this.id = id;
            this.defaultLength = defaultLength;
            this.fieldType = fieldType;
            this.messageType = messageType;
        }

        @Override
        public int getDefaultLength() {
            return defaultLength;
        }

        @Override
        public FieldType getFieldType() {
            return fieldType;
        }

        @Override
        public int getId() {
            return id;
        }

        @Override
        public Class<?> getMessageType() {
            return messageType;
        }
    }

    @ContactMessageField(fieldType = FieldType.BYTE, order = 1)
    private int count = 0;

    @ContactMessageField(fieldType = FieldType.ADDITIONALS, order = 2, additionalEnumClass = PlatformConfigurationRequestAdditionalMessageKey.class, length = 4)
    private List<Tuple3<IAdditionalMessageKey, Integer, Object>> addtionalMessages;

    public void addAdditionalMessage(final IAdditionalMessageKey messageKey, final Object value) {
        getAddtionalMessages().add(new Tuple3<>(messageKey, messageKey.getDefaultLength(), value));
        count++;
    }

    public int getCount() {
        return count;
    }

    protected List<Tuple3<IAdditionalMessageKey, Integer, Object>> getAddtionalMessages() {
        if (addtionalMessages == null) {
            addtionalMessages = new LinkedList<>();
        }
        return addtionalMessages;
    }

    @Override
    protected int getDefaultMessageId() {
        return 0x8103;
    }

    protected void setAddtionalMessages(final List<Tuple3<IAdditionalMessageKey, Integer, Object>> addtionalMessages) {
        this.addtionalMessages = addtionalMessages;
    }

    protected void setCount(final int count) {
        this.count = count;
    }
}
