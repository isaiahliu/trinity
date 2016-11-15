package org.trinity.framework.contact;

public interface IContactMessageSerializer<TMessageMeta extends IContactMessageMeta, TMessage extends IContactMessage<TMessageMeta>> {
    byte[] serializeBody(TMessage message);

    byte[] serializeHeader(TMessageMeta header);
}
