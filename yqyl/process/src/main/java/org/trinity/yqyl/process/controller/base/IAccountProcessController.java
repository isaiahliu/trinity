package org.trinity.yqyl.process.controller.base;

import org.trinity.process.controller.ICrudProcessController;
import org.trinity.yqyl.common.message.dto.domain.AccountDto;
import org.trinity.yqyl.common.message.dto.domain.AccountSearchingDto;

public interface IAccountProcessController extends ICrudProcessController<AccountDto, AccountSearchingDto> {
}

