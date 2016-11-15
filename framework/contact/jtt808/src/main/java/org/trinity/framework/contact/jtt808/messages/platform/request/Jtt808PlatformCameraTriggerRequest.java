package org.trinity.framework.contact.jtt808.messages.platform.request;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageField.FieldType;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class Jtt808PlatformCameraTriggerRequest extends AbstractJtt808PlatformRequest {
    @ContactMessageField(fieldType = FieldType.BYTE, order = 1)
    private int channel;

    @ContactMessageField(fieldType = FieldType.WORD, order = 2)
    private int count;

    @ContactMessageField(fieldType = FieldType.WORD, order = 3)
    private int second;

    @ContactMessageField(fieldType = FieldType.BYTE, order = 4)
    private int saveFlag;

    @ContactMessageField(fieldType = FieldType.BYTE, order = 5)
    private int resolution;

    @ContactMessageField(fieldType = FieldType.BYTE, order = 6)
    private int quality;

    @ContactMessageField(fieldType = FieldType.BYTE, order = 7)
    private int color;

    @ContactMessageField(fieldType = FieldType.BYTE, order = 8)
    private int opp;

    @ContactMessageField(fieldType = FieldType.BYTE, order = 9)
    private int baohe;

    @ContactMessageField(fieldType = FieldType.BYTE, order = 10)
    private int co;

    public int getBaohe() {
        return baohe;
    }

    public int getChannel() {
        return channel;
    }

    public int getCo() {
        return co;
    }

    public int getColor() {
        return color;
    }

    public int getCount() {
        return count;
    }

    public int getOpp() {
        return opp;
    }

    public int getQuality() {
        return quality;
    }

    public int getResolution() {
        return resolution;
    }

    public int getSaveFlag() {
        return saveFlag;
    }

    public int getSecond() {
        return second;
    }

    public void setBaohe(final int baohe) {
        this.baohe = baohe;
    }

    public void setChannel(final int channel) {
        this.channel = channel;
    }

    public void setCo(final int co) {
        this.co = co;
    }

    public void setColor(final int color) {
        this.color = color;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public void setOpp(final int opp) {
        this.opp = opp;
    }

    public void setQuality(final int quality) {
        this.quality = quality;
    }

    public void setResolution(final int resolution) {
        this.resolution = resolution;
    }

    public void setSaveFlag(final int saveFlag) {
        this.saveFlag = saveFlag;
    }

    public void setSecond(final int second) {
        this.second = second;
    }

    @Override
    protected int getDefaultMessageId() {
        return 0x8801;
    }
}
