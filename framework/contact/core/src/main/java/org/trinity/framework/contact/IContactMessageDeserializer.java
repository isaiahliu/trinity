package org.trinity.framework.contact;

import java.io.ByteArrayInputStream;

public interface IContactMessageDeserializer<TMessageMeta extends IContactMessageMeta, TMessage extends IContactMessage<TMessageMeta>> {
    TMessage deserializeBody(TMessageMeta header, ByteArrayInputStream messageCodes);

    TMessageMeta deserializeHeader(ByteArrayInputStream messageCodes);
}
