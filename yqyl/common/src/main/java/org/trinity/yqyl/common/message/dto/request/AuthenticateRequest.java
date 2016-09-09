package org.trinity.yqyl.common.message.dto.request;

import org.trinity.common.dto.request.AbstractRequest;
import org.trinity.yqyl.common.message.dto.domain.SecurityDto;

public class AuthenticateRequest extends AbstractRequest {
    private SecurityDto user;

    public SecurityDto getUser() {
        return user;
    }

    public void setUser(final SecurityDto user) {
        this.user = user;
    }
}
