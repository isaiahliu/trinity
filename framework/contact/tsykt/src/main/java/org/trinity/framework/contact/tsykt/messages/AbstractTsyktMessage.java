package org.trinity.framework.contact.tsykt.messages;

import org.trinity.framework.contact.AbstractContactMessage;
import org.trinity.framework.contact.tsykt.ITsyktMessage;
import org.trinity.framework.contact.tsykt.ITsyktMessageMeta;
import org.trinity.framework.contact.tsykt.TsyktMessageMeta;

public abstract class AbstractTsyktMessage extends AbstractContactMessage<ITsyktMessageMeta> implements ITsyktMessage {

    @Override
    protected ITsyktMessageMeta createMessageMeta() {
        return new TsyktMessageMeta(getDefaultMessageId());
    }
}
