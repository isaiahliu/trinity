package org.trinity.framework.contact.jtt808;

import org.trinity.framework.contact.IContactMessageMeta;

public interface IJtt808MessageMeta extends IContactMessageMeta {

    int getEncryptionMode();

    String getPhoneNo();

    void setEncryptionMode(final int encryptionMode);

    void setPhoneNo(final String phoneNo);

}
