package org.trinity.yqyl.process.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.exception.IException;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.AccountBalanceDto;
import org.trinity.yqyl.common.message.dto.domain.AccountPostingDto;
import org.trinity.yqyl.common.message.dto.domain.AccountTransactionDto;
import org.trinity.yqyl.common.message.dto.domain.YiquanDto;
import org.trinity.yqyl.common.message.dto.domain.YiquanSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.AccountCategory;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.common.message.lookup.ServiceReceiverClientStatus;
import org.trinity.yqyl.common.message.lookup.TransactionType;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IAccountProcessController;
import org.trinity.yqyl.process.controller.base.IAccountTransactionProcessController;
import org.trinity.yqyl.process.controller.base.IYiquanProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceReceiverClientRepository;
import org.trinity.yqyl.repository.business.dataaccess.IYiquanRepository;
import org.trinity.yqyl.repository.business.entity.Account;
import org.trinity.yqyl.repository.business.entity.AccountBalance;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClient;
import org.trinity.yqyl.repository.business.entity.Yiquan;

@Service
public class YiquanProcessController
        extends AbstractAutowiredCrudProcessController<Yiquan, YiquanDto, YiquanSearchingDto, IYiquanRepository>
        implements IYiquanProcessController {
    @Autowired
    private IServiceReceiverClientRepository serviceReceiverClientRepository;

    @Autowired
    private IAccountProcessController accountProcessController;

    @Autowired
    private IAccountTransactionProcessController accountTransactionProcessController;

    @Autowired
    private IObjectConverter<AccountBalance, AccountBalanceDto> accountBalanceConverter;

    @Override
    @Transactional(rollbackOn = IException.class)
    public void bindMe(final YiquanDto yiquanDto) throws IException {
        if (StringUtils.isEmpty(yiquanDto.getCode())) {
            return;
        }

        final String username = getSecurityUtil().getCurrentToken().getUsername();

        final ServiceReceiverClient client = serviceReceiverClientRepository.findOne(yiquanDto.getServiceReceiverClientId());

        if (!client.getUser().getUsername().equals(username)) {
            throw getExceptionFactory().createException(ErrorMessage.INSUFFICIENT_ACCESSRIGHT);
        }

        if (client.getStatus() != ServiceReceiverClientStatus.REALNAME) {
            throw getExceptionFactory().createException(ErrorMessage.CLIENT_SHOULD_BE_REALNAME);
        }

        if (client.getYiquan() != null) {
            throw getExceptionFactory().createException(ErrorMessage.YIQUAN_IS_ALREADY_BINDED);
        }

        // final String code = yiquanDto.getCode();
        // final String name = client.getName();
        // final String password = yiquanDto.getYiquanPassword();
        // final String identityCard = client.getIdentityCard();
        // TODO verify yiquan code, password, name, identity card from API.
        // TODO If verify fails, throw exception.

        Yiquan yiquan = getDomainEntityRepository().findOneByCode(yiquanDto.getCode());

        if (yiquan == null) {
            final Account account = accountProcessController.createAccount();
            yiquan = new Yiquan();
            yiquan.setCode(yiquanDto.getCode());
            yiquan.setStatus(RecordStatus.ACTIVE);
            yiquan.setAccount(account);
            getDomainEntityRepository().save(yiquan);

            // TODO get the initial yiquan amount
            final double yiquanBalance = 2000d;

            final AccountTransactionDto transaction = new AccountTransactionDto();

            transaction.setType(new LookupDto(TransactionType.BIND));
            final AccountPostingDto accountPostingDto = new AccountPostingDto();
            accountPostingDto.setAmount(yiquanBalance);
            accountPostingDto.setBalance(accountBalanceConverter
                    .convert(account.getBalances().stream().filter(item -> item.getCategory() == AccountCategory.YIQUAN).findAny().get()));
            transaction.getAccountPostings().add(accountPostingDto);

            accountTransactionProcessController.processTransaction(transaction);
        }

        client.setYiquan(yiquan);

        serviceReceiverClientRepository.save(client);
    }

    @Override
    @Transactional(rollbackOn = IException.class)
    public void topupMe(final YiquanDto yiquanDto) throws IException {
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
