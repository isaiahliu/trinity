package org.trinity.framework.contact.tsykt;

import org.trinity.framework.contact.IContactMessageHandler;

public interface ITsyktMessageHandler extends
        IContactMessageHandler<ITsyktMessageSession, ITsyktMessageMeta, ITsyktMessage, ITsyktProcessor, ITsyktResponsor, ITsyktMessageSerializer, ITsyktMessageDeserializer, ITsyktMessageQueue, ITsyktMessagePool> {

}
