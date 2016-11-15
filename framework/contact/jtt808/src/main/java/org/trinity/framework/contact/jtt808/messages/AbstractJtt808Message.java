package org.trinity.framework.contact.jtt808.messages;

import org.trinity.framework.contact.AbstractContactMessage;
import org.trinity.framework.contact.jtt808.IJtt808Message;
import org.trinity.framework.contact.jtt808.IJtt808MessageMeta;
import org.trinity.framework.contact.jtt808.Jtt808MessageMeta;

public abstract class AbstractJtt808Message extends AbstractContactMessage<IJtt808MessageMeta> implements IJtt808Message {

    @Override
    protected IJtt808MessageMeta createMessageMeta() {
        return new Jtt808MessageMeta(getDefaultMessageId());
    }
}
