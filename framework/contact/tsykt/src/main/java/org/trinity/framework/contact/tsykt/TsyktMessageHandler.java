package org.trinity.framework.contact.tsykt;

import org.trinity.framework.contact.AbstractContactMessageHandler;

public final class TsyktMessageHandler extends
        AbstractContactMessageHandler<ITsyktMessageSession, ITsyktMessageMeta, ITsyktMessage, ITsyktProcessor, ITsyktResponsor, ITsyktMessageSerializer, ITsyktMessageDeserializer, ITsyktMessageQueue, ITsyktMessagePool>
        implements ITsyktMessageHandler {
    private static class DefaultProcessor implements ITsyktProcessor {
        @Override
        public Class<?>[] getResponseTypes() {
            return new Class<?>[0];
        }

        @Override
        public void processMessage(final ITsyktMessageSession messageSession, final ITsyktMessage request, final ITsyktMessage response) {
        }
    }

    private static class DefaultResponsor implements ITsyktResponsor {
        @Override
        public Class<?>[] getRequestTypes() {
            return new Class<?>[0];
        }

        @Override
        public ITsyktMessage[] processMessage(final ITsyktMessageSession messageSession, final ITsyktMessage request) {
            return new ITsyktMessage[] {};
        }
    }

    private ITsyktProcessor defaultProcessor;

    private ITsyktResponsor defaultResponsor;

    @Override
    protected ITsyktMessagePool createMessagePool() {
        return new TsyktMessagePool();
    }

    @Override
    protected ITsyktProcessor getDefaultProcessor() {
        if (defaultProcessor == null) {
            defaultProcessor = new DefaultProcessor();
        }
        return defaultProcessor;
    }

    @Override
    protected ITsyktResponsor getDefaultResponsor() {
        if (defaultResponsor == null) {
            defaultResponsor = new DefaultResponsor();
        }
        return defaultResponsor;
    }
}
