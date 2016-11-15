package org.trinity.framework.contact.jtt808.messages.terminal.request;

import java.util.ArrayList;
import java.util.List;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageField.FieldType;
import org.trinity.framework.contact.IContactMessageFieldType;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class Jtt808TerminalUploadPositionBatchRequest extends AbstractJtt808TerminalRequest {
    @ContactMessage(storeMethod = StoreMethod.BIG_END)
    public static class PositionData {
        @ContactMessageField(fieldType = FieldType.WORD, order = 1, depends = true)
        private int length;

        @ContactMessageField(fieldType = FieldType.COMPONENT, order = 2, getLengthFrom = "length")
        private Jtt808TerminalUploadPositionRequest request;

        public PositionData() {
        }

        public PositionData(final Jtt808TerminalUploadPositionRequest request) {
            this.request = request;
        }

        public int getLength() {
            return length;
        }

        public Jtt808TerminalUploadPositionRequest getRequest() {
            if (request == null) {
                request = new Jtt808TerminalUploadPositionRequest();
            }
            return request;
        }

        public void setLength(final int length) {
            this.length = length;
        }

        public void setRequest(final Jtt808TerminalUploadPositionRequest request) {
            this.request = request;
        }
    }

    public static enum PostionDataType implements IContactMessageFieldType {
        NORMAL(0),
        BLIND_AREA(1);

        private final int value;

        private PostionDataType(final int value) {
            this.value = value;
        }

        @Override
        public int getValue() {
            return value;
        }
    }

    @ContactMessageField(fieldType = FieldType.WORD, order = 1)
    private int count = -1;

    @ContactMessageField(fieldType = FieldType.BYTE, order = 2)
    private PostionDataType positionDataType;

    @ContactMessageField(fieldType = FieldType.COMPONENT_LIST, order = 3, getLengthFrom = "count")
    private List<PositionData> items;

    public int getCount() {
        if (count == -1) {
            return getItems().size();
        }
        return count;
    }

    public List<PositionData> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    public PostionDataType getPositionDataType() {
        return positionDataType;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public void setItems(final List<PositionData> items) {
        this.items = items;
    }

    public void setPositionDataType(final PostionDataType positionDataType) {
        this.positionDataType = positionDataType;
    }

    @Override
    protected int getDefaultMessageId() {
        return 0x0704;
    }
}
