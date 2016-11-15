package org.trinity.framework.contact;

public interface IContactMessageHandler<TMessageSession extends IContactMessageSession, TMessageMeta extends IContactMessageMeta, TMessage extends IContactMessage<TMessageMeta>, TProcessor extends IContactProcessor<TMessageSession, TMessageMeta, TMessage>, TResponsor extends IContactResponsor<TMessageSession, TMessageMeta, TMessage>, TSerializer extends IContactMessageSerializer<TMessageMeta, TMessage>, TDeserializer extends IContactMessageDeserializer<TMessageMeta, TMessage>, TMessageQueue extends IContactMessageQueue<TMessageMeta, TMessage, TSerializer, TDeserializer>, TMessagePool extends IContactMessagePool<TMessageMeta, TMessage, TSerializer, TDeserializer, TMessageQueue>> {
    void disconnect(String owner);

    TMessagePool getMessagePool();

    void onReceive(String owner, TMessageSession messageSession, byte[] messageCodes);

    void onSend(String owner, TMessageSession messageSession, TMessage message);

    void registerMessageProcessor(TProcessor processor);

    void registerMessageResponsor(TResponsor responsor);

    void setMessagePool(TMessagePool messagePool);
}
