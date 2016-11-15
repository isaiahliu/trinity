package org.trinity.framework.contact.jtt808.messages.terminal.request;

import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageField.FieldType;
import org.trinity.framework.contact.IContactMessageFieldType;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class Jtt808TerminalUploadDriverRequest extends AbstractJtt808TerminalRequest {
    public enum ReadCardResult implements IContactMessageFieldType {
        SUCCESS(0x00),
        NOT_AUTHENTICATED(0x01),
        LOCKED(0x02),
        DRAWN_OUT(0x03),
        DATA_VERIFY_FAILED(0x04);

        private final int value;

        private ReadCardResult(final int value) {
            this.value = value;
        }

        @Override
        public int getValue() {
            return value;
        }
    }

    public enum Status implements IContactMessageFieldType {
        ON_WORK(0x01),
        OFF_WORK(0x02);

        private final int value;

        private Status(final int value) {
            this.value = value;
        }

        @Override
        public int getValue() {
            return value;
        }
    }

    @ContactMessageField(fieldType = FieldType.BYTE, order = 1)
    private Status status;

    @ContactMessageField(fieldType = FieldType.BYTE, order = 2, required = false)
    private int onWork;

    @ContactMessageField(fieldType = FieldType.BCD, length = 6, order = 4, padLetter = '0')
    private String datetime;

    @ContactMessageField(fieldType = FieldType.BYTE, order = 5, getRequiredFrom = "onWork")
    private ReadCardResult result;

    @ContactMessageField(fieldType = FieldType.BYTE, order = 6, getRequiredFrom = "onWork")
    private int driverNameLength;

    @ContactMessageField(fieldType = FieldType.STRING, getLengthFrom = "driverNameLength", getRequiredFrom = "onWork", order = 7)
    private String driverName;

    @ContactMessageField(fieldType = FieldType.STRING, length = 20, padLetter = 0x00, order = 8, getRequiredFrom = "onWork")
    private String qualificationCertId;

    @ContactMessageField(fieldType = FieldType.BYTE, order = 9, getRequiredFrom = "onWork")
    private int tcbNameLength;

    @ContactMessageField(fieldType = FieldType.STRING, getLengthFrom = "tcbNameLength", order = 10, getRequiredFrom = "onWork")
    private String tcbName;

    @ContactMessageField(fieldType = FieldType.BCD, length = 4, order = 11, getRequiredFrom = "onWork", padLetter = '0')
    private String validDate;

    public String getDatetime() {
        return datetime;
    }

    public String getDriverName() {
        return driverName;
    }

    public int getDriverNameLength() {
        return driverNameLength;
    }

    public String getQualificationCertId() {
        return qualificationCertId;
    }

    public ReadCardResult getResult() {
        return result;
    }

    public Status getStatus() {
        return status;
    }

    public String getTcbName() {
        return tcbName;
    }

    public int getTcbNameLength() {
        return tcbNameLength;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setDatetime(final String datetime) {
        this.datetime = datetime;
    }

    public void setDriverName(final String driverName) {
        this.driverName = driverName;
        if (driverName != null) {
            driverNameLength = driverName.length();
        }
    }

    public void setDriverNameLength(final int driverNameLength) {
        this.driverNameLength = driverNameLength;
    }

    public void setQualificationCertId(final String qualificationCertId) {
        this.qualificationCertId = qualificationCertId;
    }

    public void setResult(final ReadCardResult result) {
        this.result = result;
    }

    public void setStatus(final Status status) {
        onWork = (status == Status.ON_WORK) ? 1 : 0;
        this.status = status;
    }

    public void setTcbName(final String tcbName) {
        this.tcbName = tcbName;
        if (tcbName != null) {
            tcbNameLength = tcbName.length();
        }
    }

    public void setTcbNameLength(final int tcbNameLength) {
        this.tcbNameLength = tcbNameLength;
    }

    public void setValidDate(final String validDate) {
        this.validDate = validDate;
    }

    @Override
    protected int getDefaultMessageId() {
        return 0x0702;
    }

    protected int getOnWork() {
        return onWork;
    }

    protected void setOnWork(final int onWork) {
        this.onWork = onWork;
    }
}
