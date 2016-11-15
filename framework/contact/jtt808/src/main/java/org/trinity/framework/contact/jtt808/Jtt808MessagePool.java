package org.trinity.framework.contact.jtt808;

import org.trinity.framework.contact.AbstractContactMessagePool;

public final class Jtt808MessagePool extends
        AbstractContactMessagePool<IJtt808MessageMeta, IJtt808Message, IJtt808MessageSerializer, IJtt808MessageDeserializer, IJtt808MessageQueue>
        implements IJtt808MessagePool {

    private IJtt808MessageDeserializer deserializer;

    private IJtt808MessageSerializer serializer;

    @Override
    public IJtt808MessageDeserializer getDeserializer() {
        if (deserializer == null) {
            deserializer = new Jtt808MessageDeserializer();
        }
        return deserializer;
    }

    @Override
    public IJtt808MessageSerializer getSerializer() {
        if (serializer == null) {
            serializer = new Jtt808MessageSerializer();
        }

        return serializer;
    }

    @Override
    public void setDeserializer(final IJtt808MessageDeserializer deserializer) {
        this.deserializer = deserializer;
    }

    @Override
    public void setSerializer(final IJtt808MessageSerializer serializer) {
        this.serializer = serializer;
    }

    @Override
    protected IJtt808MessageQueue getQueueForOwner(final String owner) {
        IJtt808MessageQueue queue = getPool().get(owner);

        if (queue == null) {
            synchronized (this) {
                if (!getPool().containsKey(owner)) {
                    queue = new Jtt808MessageQueue(getSerializer(), getDeserializer(), getMaxSerialNumber(), getMaxBodyLength(),
                            getTimeOutSenconds(), getMaxRetryTimes());
                    getPool().put(owner, queue);
                }
            }
        }

        return queue;
    }
}
