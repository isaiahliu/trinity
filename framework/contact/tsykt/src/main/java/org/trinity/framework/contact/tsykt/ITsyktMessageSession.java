package org.trinity.framework.contact.tsykt;

import org.trinity.framework.contact.IContactMessageSession;

public interface ITsyktMessageSession extends IContactMessageSession {
    long getTimestamp();

    void setTimestamp(long timestamp);
}
