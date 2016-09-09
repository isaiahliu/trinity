package org.trinity.yqyl.process.controller;

import org.springframework.stereotype.Service;
import org.trinity.yqyl.common.message.dto.domain.AccountBalanceDto;
import org.trinity.yqyl.common.message.dto.domain.AccountBalanceSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IAccountBalanceProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IAccountBalanceRepository;
import org.trinity.yqyl.repository.business.entity.AccountBalance;

@Service
public class AccountBalanceProcessController extends
        AbstractAutowiredCrudProcessController<AccountBalance, AccountBalanceDto, AccountBalanceSearchingDto, IAccountBalanceRepository>
        implements IAccountBalanceProcessController {
    public AccountBalanceProcessController() {
        super(AccountBalance.class, ErrorMessage.UNABLE_TO_FIND_ACCOUNT_BALANCE);
    }
}
