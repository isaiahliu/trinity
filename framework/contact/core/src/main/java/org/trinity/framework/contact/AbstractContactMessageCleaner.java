package org.trinity.framework.contact;

import java.util.Set;

public abstract class AbstractContactMessageCleaner<TMessageMeta extends IContactMessageMeta, TMessage extends IContactMessage<TMessageMeta>, TSerializer extends IContactMessageSerializer<TMessageMeta, TMessage>, TDeserializer extends IContactMessageDeserializer<TMessageMeta, TMessage>, TMessageQueue extends IContactMessageQueue<TMessageMeta, TMessage, TSerializer, TDeserializer>, TMessagePool extends IContactMessagePool<TMessageMeta, TMessage, TSerializer, TDeserializer, TMessageQueue>, TMessageSession extends IContactMessageSession>
        implements IContactMessageCleaner {
    private final TMessagePool messagePool;
    private final Thread thread;

    private boolean alive;

    private boolean processing;

    public AbstractContactMessageCleaner(final TMessagePool messagePool) {
        super();
        this.messagePool = messagePool;
        thread = new Thread(this::cleanMessage);

        start();
    }

    @Override
    public void resume() {
        processing = true;
    }

    @Override
    public void start() {
        if (thread.isAlive()) {
            resume();
        } else {
            alive = true;
            processing = true;

            thread.start();
        }
    }

    @Override
    public void stop() {
        alive = false;
    }

    @Override
    public void suspend() {
        processing = false;
    }

    private void cleanMessage() {

        while (alive) {
            try {
                if (processing) {
                    final Set<String> owners = messagePool.getOwners();

                    for (final String owner : owners) {
                        final byte[] expiredRequestMessage = messagePool.getExpiredRequestMessage(owner);

                        if (expiredRequestMessage.length > 0) {
                            getMessageSessionForOwner(owner).sendMessage(expiredRequestMessage);
                        }

                        messagePool.getTimeoutSerialNumbers(owner);
                    }
                }
                Thread.sleep(1000);
            } catch (final Throwable e) {
            }
        }
    }

    protected abstract TMessageSession getMessageSessionForOwner(String owner);
}
