package org.trinity.framework.contact.tsykt;

import org.trinity.framework.contact.IContactMessageMeta;

public interface ITsyktMessageMeta extends IContactMessageMeta {

    int getEncryptionMode();

    String getPhoneNo();

    void setEncryptionMode(final int encryptionMode);

    void setPhoneNo(final String phoneNo);

}
