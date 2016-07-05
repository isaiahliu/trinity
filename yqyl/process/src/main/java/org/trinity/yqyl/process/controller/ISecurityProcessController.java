package org.trinity.yqyl.process.controller;

import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.message.dto.domain.TokenDto;
import org.trinity.yqyl.common.message.dto.domain.UserDto;

public interface ISecurityProcessController {
    UserDto authenticate(final String tokenName, String username, String password) throws IException;

    TokenDto getToken(String identity);

    void logout(String token);
}
