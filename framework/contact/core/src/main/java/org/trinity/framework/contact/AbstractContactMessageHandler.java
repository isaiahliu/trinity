package org.trinity.framework.contact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public abstract class AbstractContactMessageHandler<TMessageSession extends IContactMessageSession, TMessageMeta extends IContactMessageMeta, TMessage extends IContactMessage<TMessageMeta>, TProcessor extends IContactProcessor<TMessageSession, TMessageMeta, TMessage>, TResponsor extends IContactResponsor<TMessageSession, TMessageMeta, TMessage>, TSerializer extends IContactMessageSerializer<TMessageMeta, TMessage>, TDeserializer extends IContactMessageDeserializer<TMessageMeta, TMessage>, TMessageQueue extends IContactMessageQueue<TMessageMeta, TMessage, TSerializer, TDeserializer>, TMessagePool extends IContactMessagePool<TMessageMeta, TMessage, TSerializer, TDeserializer, TMessageQueue>>
        implements
        IContactMessageHandler<TMessageSession, TMessageMeta, TMessage, TProcessor, TResponsor, TSerializer, TDeserializer, TMessageQueue, TMessagePool> {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private final Map<Class<?>, List<TResponsor>> responsors;
    private final Map<Class<?>, List<TProcessor>> processors;

    private TMessagePool messagePool;

    public AbstractContactMessageHandler() {
        responsors = new HashMap<>();
        processors = new HashMap<>();
    }

    @Override
    public void disconnect(final String owner) {
        getMessagePool().removeOwner(owner);
    }

    @Override
    public TMessagePool getMessagePool() {
        if (messagePool == null) {
            messagePool = createMessagePool();
        }

        return messagePool;
    }

    @Override
    public void onReceive(final String owner, final TMessageSession messageSender, final byte[] messageCodes) {
        final StringBuilder hex = new StringBuilder();
        ContactMessageUtil.convertByteArrayToList(messageCodes).stream().map(item -> {
            final String hexString = Integer.toHexString(Byte.toUnsignedInt(item)).toUpperCase();
            if (hexString.length() == 1) {
                return "0" + hexString;
            } else {
                return hexString;
            }
        }).forEach(item -> hex.append(item + " "));

        logger.debug("Received: " + hex.toString());

        final List<TMessage> messages = getMessagePool().getMessagesFromCodes(owner, messageCodes);

        messages.forEach(item -> logger.debug(item.getClass().getSimpleName()));

        for (final TMessage message : messages) {
            if (message.isRequireResponse()) {
                List<TResponsor> respors = getResponsors().get(message.getClass());

                if (respors == null) {
                    respors = new ArrayList<>();

                    respors.add(getDefaultResponsor());
                }
                final List<TMessage> responses = new ArrayList<>();
                for (final TResponsor responsor : respors) {
                    TMessage[] resps = null;
                    try {
                        resps = responsor.processMessage(messageSender, message);

                    } catch (final Throwable e) {
                        try {
                            logger.error("process message error", e);
                            resps = getDefaultResponsor().processMessage(messageSender, message);
                        } catch (final Throwable ex) {
                        }
                    }
                    if (resps != null) {
                        responses.addAll(Arrays.asList(resps));
                    }
                }
                for (final TMessage response : responses) {
                    onSend(owner, messageSender, response);
                }
            } else {
                List<TProcessor> prosors = getProcessors().get(message.getClass());
                if (prosors == null) {
                    prosors = new ArrayList<>();
                    prosors.add(getDefaultProcessor());
                }
                TMessage requestMessage = null;

                final int requestSerialNumber = message.getRequestSerialNumber();

                requestMessage = getMessagePool().pollRequestMessage(owner, requestSerialNumber);

                for (final TProcessor processor : prosors) {
                    try {
                        processor.processMessage(messageSender, requestMessage, message);
                    } catch (final Throwable e) {
                        getDefaultProcessor().processMessage(messageSender, requestMessage, message);
                    }
                }
            }
        }
    }

    @Override
    public void onSend(final String owner, final TMessageSession messageSession, final TMessage message) {
        final byte[] codes = getMessagePool().getCodesFromMessages(owner, message);

        final StringBuilder hex = new StringBuilder();
        ContactMessageUtil.convertByteArrayToList(codes).stream().map(item -> {
            final String hexString = Integer.toHexString(Byte.toUnsignedInt(item)).toUpperCase();
            if (hexString.length() == 1) {
                return "0" + hexString;
            } else {
                return hexString;
            }
        }).forEach(item -> hex.append(item + " "));

        logger.debug("Sent: " + hex.toString());
        logger.debug(message.getClass().getSimpleName());

        messageSession.sendMessage(codes);
    }

    @Override
    public void registerMessageProcessor(final TProcessor processor) {
        if (processor == null || processor.getResponseTypes() == null) {
            return;
        }

        for (final Class<?> type : processor.getResponseTypes()) {
            if (!getProcessors().containsKey(type)) {
                getProcessors().put(type, new ArrayList<>());
            }
            getProcessors().get(type).add(processor);
        }
    }

    @Override
    public void registerMessageResponsor(final TResponsor responsor) {
        if (responsor == null || responsor.getRequestTypes() == null) {
            return;
        }

        for (final Class<?> type : responsor.getRequestTypes()) {
            if (!getResponsors().containsKey(type)) {
                getResponsors().put(type, new ArrayList<>());
            }
            getResponsors().get(type).add(responsor);
        }
    }

    @Override
    public void setMessagePool(final TMessagePool messagePool) {
        this.messagePool = messagePool;
    }

    protected abstract TMessagePool createMessagePool();

    protected abstract TProcessor getDefaultProcessor();

    protected abstract TResponsor getDefaultResponsor();

    protected Map<Class<?>, List<TProcessor>> getProcessors() {
        return processors;
    }

    protected Map<Class<?>, List<TResponsor>> getResponsors() {
        return responsors;
    }
}
