package org.trinity.framework.contact;

import java.util.List;

public interface IContactMessageQueue<TMessageMeta extends IContactMessageMeta, TMessage extends IContactMessage<TMessageMeta>, TSerializer extends IContactMessageSerializer<TMessageMeta, TMessage>, TDeserializer extends IContactMessageDeserializer<TMessageMeta, TMessage>> {

    byte[] getCodesFromMessages(TMessage message);

    List<TMessage> getMessagesFromCodes(byte[] messageCodes);

    default int getNextSerialNumber() {
        return getNextSerialNumber(1);
    }

    int getNextSerialNumber(int count);

    TMessage getRequestMessage(int serialNumber);

    List<Integer> getTimeoutSerialNumbers();

    byte[] getUnresponsedSendMessage();

    boolean isActive();

    TMessage pollUnresponsedSentMessage(int serialNumber);

    void setActive(String owner, boolean active);

}
