package org.trinity.framework.contact;

import org.trinity.common.exception.IException;

public interface IContactResponsor<TMessageSession extends IContactMessageSession, TMessageMeta extends IContactMessageMeta, TMessage extends IContactMessage<TMessageMeta>> {
    Class<?>[] getRequestTypes();

    TMessage[] processMessage(TMessageSession messageSession, TMessage request) throws IException;
}
