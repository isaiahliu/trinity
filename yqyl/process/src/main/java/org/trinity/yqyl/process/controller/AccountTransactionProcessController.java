package org.trinity.yqyl.process.controller;

import org.springframework.stereotype.Service;
import org.trinity.yqyl.common.message.dto.domain.AccountTransactionDto;
import org.trinity.yqyl.common.message.dto.domain.AccountTransactionSearchingDto;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IAccountTransactionProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IAccountTransactionRepository;
import org.trinity.yqyl.repository.business.entity.AccountTransaction;

@Service
public class AccountTransactionProcessController extends
        AbstractAutowiredCrudProcessController<AccountTransaction, AccountTransactionDto, AccountTransactionSearchingDto, IAccountTransactionRepository>
        implements IAccountTransactionProcessController {
}
