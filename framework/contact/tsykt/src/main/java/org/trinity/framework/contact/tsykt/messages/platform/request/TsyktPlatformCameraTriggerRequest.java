package org.trinity.framework.contact.tsykt.messages.platform.request;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageField.FieldType;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class TsyktPlatformCameraTriggerRequest extends AbstractTsyktPlatformRequest {
    @ContactMessageField(fieldType = FieldType.BYTE, order = 1)
    private int channel;

    @Override
    protected int getDefaultMessageId() {
        return 0x8801;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }
}
