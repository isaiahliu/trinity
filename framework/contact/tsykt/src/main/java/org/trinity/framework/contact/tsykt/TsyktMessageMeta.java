package org.trinity.framework.contact.tsykt;

import org.trinity.framework.contact.AbstractContactMessageMeta;

public class TsyktMessageMeta extends AbstractContactMessageMeta implements ITsyktMessageMeta {
    private String phoneNo;
    private int encryptionMode;

    public TsyktMessageMeta(final int id) {
        super(id);
    }

    @Override
    public int getEncryptionMode() {
        return encryptionMode;
    }

    @Override
    public String getPhoneNo() {
        if (phoneNo == null) {
            phoneNo = "";
        }
        return phoneNo;
    }

    @Override
    public void setEncryptionMode(final int encryptionMode) {
        this.encryptionMode = encryptionMode;
    }

    @Override
    public void setPhoneNo(final String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
