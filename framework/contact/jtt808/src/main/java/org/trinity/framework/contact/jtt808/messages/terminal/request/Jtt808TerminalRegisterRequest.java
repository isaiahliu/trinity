package org.trinity.framework.contact.jtt808.messages.terminal.request;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageField.FieldType;
import org.trinity.framework.contact.IContactMessageFieldType;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class Jtt808TerminalRegisterRequest extends AbstractJtt808TerminalRequest {
    public enum PlateColor implements IContactMessageFieldType {
        NONE(0),
        BLUE(1),
        YELLOW(2),
        BLACK(3),
        WHITE(4),
        OTHER(9);

        private final int value;

        private PlateColor(final int value) {
            this.value = value;
        }

        @Override
        public int getValue() {
            return value;
        }
    }

    @ContactMessageField(fieldType = FieldType.WORD, order = 1)
    private int provinceId;

    @ContactMessageField(fieldType = FieldType.WORD, order = 2)
    private int cityId;

    @ContactMessageField(fieldType = FieldType.NBYTE, order = 3, length = 5, padLetter = 0)
    private String producerId;

    @ContactMessageField(fieldType = FieldType.NBYTE, order = 4, length = 20, padLetter = 0)
    private String terminalModel;

    @ContactMessageField(fieldType = FieldType.NBYTE, order = 5, length = 7, padLetter = 0)
    private String terminalId;

    @ContactMessageField(fieldType = FieldType.BYTE, order = 6)
    private PlateColor plateColor;

    @ContactMessageField(fieldType = FieldType.STRING, order = 7, length = -1)
    private String plateNumber;

    public int getCityId() {
        return cityId;
    }

    public PlateColor getPlateColor() {
        return plateColor;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getProducerId() {
        return producerId;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public String getTerminalModel() {
        return terminalModel;
    }

    public void setCityId(final int cityId) {
        this.cityId = cityId;
    }

    public void setPlateColor(final PlateColor plateColor) {
        this.plateColor = plateColor;
    }

    public void setPlateNumber(final String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setProducerId(final String producerId) {
        this.producerId = producerId;
    }

    public void setProvinceId(final int provinceId) {
        this.provinceId = provinceId;
    }

    public void setTerminalId(final String terminalId) {
        this.terminalId = terminalId;
    }

    public void setTerminalModel(final String terminalModel) {
        this.terminalModel = terminalModel;
    }

    @Override
    protected int getDefaultMessageId() {
        return 0x0100;
    }
}
