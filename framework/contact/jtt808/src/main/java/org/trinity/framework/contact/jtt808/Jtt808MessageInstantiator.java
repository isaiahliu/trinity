package org.trinity.framework.contact.jtt808;

import org.trinity.framework.contact.jtt808.messages.platform.request.Jtt808PlatformConfigurationRequest;
import org.trinity.framework.contact.jtt808.messages.platform.response.Jtt808PlatformGeneralResponse;
import org.trinity.framework.contact.jtt808.messages.terminal.request.Jtt808Terminal1505Request;
import org.trinity.framework.contact.jtt808.messages.terminal.request.Jtt808Terminal1508Request;
import org.trinity.framework.contact.jtt808.messages.terminal.request.Jtt808TerminalLogoutRequest;
import org.trinity.framework.contact.jtt808.messages.terminal.request.Jtt808TerminalPulseRequest;
import org.trinity.framework.contact.jtt808.messages.terminal.request.Jtt808TerminalRegisterRequest;
import org.trinity.framework.contact.jtt808.messages.terminal.request.Jtt808TerminalReregisterRequest;
import org.trinity.framework.contact.jtt808.messages.terminal.request.Jtt808TerminalUnIdentifiedRequest;
import org.trinity.framework.contact.jtt808.messages.terminal.request.Jtt808TerminalUploadDriverRequest;
import org.trinity.framework.contact.jtt808.messages.terminal.request.Jtt808TerminalUploadPositionBatchRequest;
import org.trinity.framework.contact.jtt808.messages.terminal.request.Jtt808TerminalUploadPositionRequest;
import org.trinity.framework.contact.jtt808.messages.terminal.response.Jtt808TerminalGeneralResponse;
import org.trinity.framework.contact.jtt808.messages.terminal.response.Jtt808TerminalRegisterResponse;

public final class Jtt808MessageInstantiator implements IJtt808MessageInstantiator {
    @Override
    public IJtt808Message createMessage(final int messageId) {
        switch (messageId) {
        case 0x0001:
            return new Jtt808PlatformGeneralResponse();
        case 0x8001:
            return new Jtt808TerminalGeneralResponse();
        case 0x0002:
            return new Jtt808TerminalPulseRequest();
        case 0x0100:
            return new Jtt808TerminalRegisterRequest();
        case 0x0003:
            return new Jtt808TerminalLogoutRequest();
        case 0x8100:
            return new Jtt808TerminalRegisterResponse();
        case 0x0102:
            return new Jtt808TerminalReregisterRequest();
        case 0x0200:
            return new Jtt808TerminalUploadPositionRequest();
        case 0x0704:
            return new Jtt808TerminalUploadPositionBatchRequest();
        case 0x0702:
            return new Jtt808TerminalUploadDriverRequest();
        case 0x8103:
            return new Jtt808PlatformConfigurationRequest();
        case 0x1505:
            return new Jtt808Terminal1505Request();
        case 0x1508:
            return new Jtt808Terminal1508Request();
        default:
            return new Jtt808TerminalUnIdentifiedRequest();
        }
    }
}
