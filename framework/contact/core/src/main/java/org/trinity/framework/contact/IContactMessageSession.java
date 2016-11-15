package org.trinity.framework.contact;

public interface IContactMessageSession {
    void close();

    String getMessageSessionKey();

    boolean isActive();

    void sendMessage(byte[] messageCodes);
}
