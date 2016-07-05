package org.trinity.yqyl.common.message.dto.request;

import org.trinity.yqyl.common.message.dto.domain.UserDto;

public class PutAuthenticateRequest {
    private UserDto user;

    public UserDto getUser() {
        return user;
    }

    public void setUser(final UserDto user) {
        this.user = user;
    }
}
