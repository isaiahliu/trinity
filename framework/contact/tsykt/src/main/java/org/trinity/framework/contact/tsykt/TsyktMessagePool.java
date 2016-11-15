package org.trinity.framework.contact.tsykt;

import org.trinity.framework.contact.AbstractContactMessagePool;

public final class TsyktMessagePool extends
        AbstractContactMessagePool<ITsyktMessageMeta, ITsyktMessage, ITsyktMessageSerializer, ITsyktMessageDeserializer, ITsyktMessageQueue>
        implements ITsyktMessagePool {

    private ITsyktMessageDeserializer deserializer;

    private ITsyktMessageSerializer serializer;

    @Override
    public ITsyktMessageDeserializer getDeserializer() {
        if (deserializer == null) {
            deserializer = new TsyktMessageDeserializer();
        }
        return deserializer;
    }

    @Override
    public ITsyktMessageSerializer getSerializer() {
        if (serializer == null) {
            serializer = new TsyktMessageSerializer();
        }

        return serializer;
    }

    @Override
    public void setDeserializer(final ITsyktMessageDeserializer deserializer) {
        this.deserializer = deserializer;
    }

    @Override
    public void setSerializer(final ITsyktMessageSerializer serializer) {
        this.serializer = serializer;
    }

    @Override
    protected ITsyktMessageQueue getQueueForOwner(final String owner) {
        ITsyktMessageQueue queue = getPool().get(owner);

        if (queue == null) {
            synchronized (this) {
                if (!getPool().containsKey(owner)) {
                    queue = new TsyktMessageQueue(getSerializer(), getDeserializer(), getMaxSerialNumber(), getMaxBodyLength(),
                            getTimeOutSenconds(), getMaxRetryTimes());
                    getPool().put(owner, queue);
                }
            }
        }

        return queue;
    }
}
