package org.trinity.framework.contact;

import java.util.List;
import java.util.Set;

public interface IContactMessagePool<TMessageMeta extends IContactMessageMeta, TMessage extends IContactMessage<TMessageMeta>, TSerializer extends IContactMessageSerializer<TMessageMeta, TMessage>, TDeserializer extends IContactMessageDeserializer<TMessageMeta, TMessage>, TMessageQueue extends IContactMessageQueue<TMessageMeta, TMessage, TSerializer, TDeserializer>> {
    byte[] getCodesFromMessages(String owner, TMessage message);

    byte[] getExpiredRequestMessage(String owner);

    int getMaxBodyLength();

    int getMaxRetryTimes();

    int getMaxSerialNumber();

    List<TMessage> getMessagesFromCodes(String owner, byte[] messageCodes);

    Set<String> getOwners();

    TMessage getRequestMessage(String owner, int serialNumber);

    int getTimeOutSenconds();

    List<Integer> getTimeoutSerialNumbers(String owner);

    boolean isActive(String owner);

    TMessage pollRequestMessage(String owner, int serialNumber);

    void registerOwner(String owner);

    void removeOwner(String owner);

    void setActive(String owner, boolean active);
}
