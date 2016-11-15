package org.trinity.framework.contact;

import java.util.Map;

public interface IContactMessageSessionPool<TMessageSession extends IContactMessageSession> {
    Map<String, TMessageSession> getMessageSessions();
}
