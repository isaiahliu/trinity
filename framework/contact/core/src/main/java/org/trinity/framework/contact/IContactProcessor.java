package org.trinity.framework.contact;

public interface IContactProcessor<TMessageSession extends IContactMessageSession, TMessageMeta extends IContactMessageMeta, TMessage extends IContactMessage<TMessageMeta>> {
    Class<?>[] getResponseTypes();

    void processMessage(TMessageSession messageSession, TMessage request, TMessage response);
}
