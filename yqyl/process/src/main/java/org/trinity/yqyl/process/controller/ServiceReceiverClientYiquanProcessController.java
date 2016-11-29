package org.trinity.yqyl.process.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trinity.common.exception.IException;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.AccountBalanceDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientYiquanDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientYiquanSearchingDto;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IAccountTransactionProcessController;
import org.trinity.yqyl.process.controller.base.IServiceReceiverClientYiquanProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IAccountBalanceRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceReceiverClientYiquanRepository;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.entity.AccountBalance;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientYiquan;

@Service
public class ServiceReceiverClientYiquanProcessController extends
        AbstractAutowiredCrudProcessController<ServiceReceiverClientYiquan, ServiceReceiverClientYiquanDto, ServiceReceiverClientYiquanSearchingDto, IServiceReceiverClientYiquanRepository>
        implements IServiceReceiverClientYiquanProcessController {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IAccountBalanceRepository accountBalanceRepository;

    @Autowired
    private IAccountTransactionProcessController accountTransactionProcessController;

    @Autowired
    private IObjectConverter<AccountBalance, AccountBalanceDto> accountBalanceConverter;

    @Override
    @Transactional(rollbackOn = IException.class)
    public void bindMe(final ServiceReceiverClientYiquanDto yiquanDto) throws IException {
        // if (StringUtils.isEmpty(yiquanDto.getCode())) {
        // return;
        // }
        //
        // final String username = getSecurityUtil().getCurrentToken().getUsername();
        //
        // final User user = userRepository.findOneByUsername(username);

        // if (user.getYiquan() != null && user.getYiquan().getCode().equals(yiquanDto.getCode())) {
        // return;
        // }
        //
        // if (user.getYiquan() != null && !user.getYiquan().getCode().equals(yiquanDto.getCode())) {
        // getSecurityUtil().checkAccessRight(AccessRight.SUPER_USER);
        // }
        //
        // ServiceReceiverClientYiquan yiquan = getDomainEntityRepository().findOneByCode(yiquanDto.getCode());
        //
        // // TODO Test code here. If new yiquan code is found. Should query the API to get the yiquan balance.
        // yiquan = new ServiceReceiverClientYiquan();
        // yiquan.setCellphone(yiquanDto.getCellphone());
        // yiquan.setCode(yiquanDto.getCode());
        // yiquan.setStatus(RecordStatus.ACTIVE);
        // getDomainEntityRepository().save(yiquan);
        // final double yiquanBalance = 2000d;
        //
        // user.setYiquan(yiquan);
        // // user.setYiquanCode(yiquan.getCode());
        //
        // userRepository.save(user);
        //
        // final AccountBalance accountBalance = new AccountBalance();
        // accountBalance.setAmount(0d);
        // accountBalance.setCategory(AccountCategory.YIQUAN);
        // accountBalance.setStatus(AccountBalanceStatus.ACTIVE);
        // accountBalance.setAccount(user.getAccount());
        //
        // accountBalanceRepository.save(accountBalance);
        //
        // final AccountTransactionDto transaction = new AccountTransactionDto();
        //
        // transaction.setType(new LookupDto(TransactionType.BIND));
        // final AccountPostingDto accountPostingDto = new AccountPostingDto();
        // accountPostingDto.setAmount(yiquanBalance);
        // accountPostingDto.setBalance(accountBalanceConverter.convert(accountBalance));
        // transaction.getAccountPostings().add(accountPostingDto);
        //
        // accountTransactionProcessController.processTransaction(transaction);
    }

    @Override
    @Transactional(rollbackOn = IException.class)
    public void topupMe(final ServiceReceiverClientYiquanDto yiquanDto) throws IException {
        // if (yiquanDto.getTopUpAmount() == null || yiquanDto.getTopUpAmount() <= 0) {
        // throw getExceptionFactory().createException(ErrorMessage.TOPUP_AMOUNT_MUST_BE_POSITIVE);
        // }
        //
        // final String username = getSecurityUtil().getCurrentToken().getUsername();
        //
        // final User user = userRepository.findOneByUsername(username);
        //
        // final AccountBalance accountBalance = user.getAccount().getBalances().stream()
        // .filter(item -> item.getCategory() == AccountCategory.YIQUAN).findFirst().get();
        //
        // final AccountTransactionDto transaction = new AccountTransactionDto();
        //
        // transaction.setType(new LookupDto(TransactionType.TOP_UP));
        // final AccountPostingDto accountPostingDto = new AccountPostingDto();
        // accountPostingDto.setAmount(yiquanDto.getTopUpAmount());
        // accountPostingDto.setBalance(accountBalanceConverter.convert(accountBalance));
        // transaction.getAccountPostings().add(accountPostingDto);
        //
        // accountTransactionProcessController.processTransaction(transaction);
    }

    @Override
    protected boolean canAccessAllStatus() {
        return true;
    }
}
