package org.trinity.framework.contact.tsykt;

import org.trinity.framework.contact.IContactMessageMeta;

public interface ITsyktMessageMeta extends IContactMessageMeta {
    byte[] getBitMap();

    void markAvailable(int... positions);
}
