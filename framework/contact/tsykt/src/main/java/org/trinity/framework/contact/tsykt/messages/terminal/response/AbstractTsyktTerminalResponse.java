package org.trinity.framework.contact.tsykt.messages.terminal.response;

import org.trinity.framework.contact.tsykt.messages.AbstractTsyktResponse;

public abstract class AbstractTsyktTerminalResponse extends AbstractTsyktResponse {
    public abstract String getResponseCode();

    public abstract void setResponseCode(String responseCode);
}
