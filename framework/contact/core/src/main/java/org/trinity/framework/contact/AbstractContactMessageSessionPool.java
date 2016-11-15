package org.trinity.framework.contact;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractContactMessageSessionPool<TMessageSession extends IContactMessageSession>
        implements IContactMessageSessionPool<TMessageSession> {
    private final Map<String, TMessageSession> messageSessions;

    public AbstractContactMessageSessionPool() {
        messageSessions = new HashMap<>();
    }

    public Map<String, TMessageSession> getMessageSessions() {
        return messageSessions;
    }
}
