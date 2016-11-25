package org.trinity.yqyl.process.controller.base;

import org.trinity.common.exception.IException;
import org.trinity.process.controller.IProcessController;
import org.trinity.yqyl.common.message.dto.domain.SecurityDto;

public interface ISecurityProcessController extends IProcessController {
    SecurityDto authenticate(String tokenName, String username, String password) throws IException;

    SecurityDto logout(String tokenName) throws IException;

    void register(SecurityDto securityDto) throws IException;
}
