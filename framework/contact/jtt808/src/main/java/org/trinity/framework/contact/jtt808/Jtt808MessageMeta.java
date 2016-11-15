package org.trinity.framework.contact.jtt808;

import org.trinity.framework.contact.AbstractContactMessageMeta;

public class Jtt808MessageMeta extends AbstractContactMessageMeta implements IJtt808MessageMeta {
    private String phoneNo;
    private int encryptionMode;

    public Jtt808MessageMeta(final int id) {
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
