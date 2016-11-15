package org.trinity.framework.contact.jtt808;

import org.trinity.framework.contact.AbstractContactMessageHandler;
import org.trinity.framework.contact.jtt808.messages.terminal.response.Jtt808TerminalGeneralResponse;
import org.trinity.framework.contact.jtt808.messages.terminal.response.Jtt808TerminalGeneralResponse.TerminalGeneralResponseResult;

public final class Jtt808MessageHandler extends
        AbstractContactMessageHandler<IJtt808MessageSession, IJtt808MessageMeta, IJtt808Message, IJtt808Processor, IJtt808Responsor, IJtt808MessageSerializer, IJtt808MessageDeserializer, IJtt808MessageQueue, IJtt808MessagePool>
        implements IJtt808MessageHandler {
    private static class DefaultProcessor implements IJtt808Processor {
        @Override
        public Class<?>[] getResponseTypes() {
            return new Class<?>[0];
        }

        @Override
        public void processMessage(final IJtt808MessageSession messageSession, final IJtt808Message request,
                final IJtt808Message response) {
        }
    }

    private static class DefaultResponsor implements IJtt808Responsor {
        @Override
        public Class<?>[] getRequestTypes() {
            return new Class<?>[0];
        }

        @Override
        public IJtt808Message[] processMessage(final IJtt808MessageSession messageSession, final IJtt808Message request) {
            final Jtt808TerminalGeneralResponse response = new Jtt808TerminalGeneralResponse();

            response.setRequestMessageId(request.getMeta().getId());
            response.setRequestSerialNumber(request.getMeta().getSerialNumber());
            response.setResult(TerminalGeneralResponseResult.UNSUPPORTED);
            return new IJtt808Message[] { response };
        }
    }

    private IJtt808Processor defaultProcessor;

    private IJtt808Responsor defaultResponsor;

    @Override
    protected IJtt808MessagePool createMessagePool() {
        return new Jtt808MessagePool();
    }

    @Override
    protected IJtt808Processor getDefaultProcessor() {
        if (defaultProcessor == null) {
            defaultProcessor = new DefaultProcessor();
        }
        return defaultProcessor;
    }

    @Override
    protected IJtt808Responsor getDefaultResponsor() {
        if (defaultResponsor == null) {
            defaultResponsor = new DefaultResponsor();
        }
        return defaultResponsor;
    }
}
