package org.trinity.yqyl.process.controller;

import org.springframework.stereotype.Service;
import org.trinity.yqyl.common.message.dto.domain.AccountDto;
import org.trinity.yqyl.common.message.dto.domain.AccountSearchingDto;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IAccountProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IAccountRepository;
import org.trinity.yqyl.repository.business.entity.Account;

@Service
public class AccountProcessController
        extends AbstractAutowiredCrudProcessController<Account, AccountDto, AccountSearchingDto, IAccountRepository>
        implements IAccountProcessController {
}
