package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.trinity.common.accessright.ISecurityUtil.CheckMode;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.message.dto.domain.YiquanDto;
import org.trinity.yqyl.common.message.dto.domain.YiquanSearchingDto;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.AccountBalanceStatus;
import org.trinity.yqyl.common.message.lookup.AccountCategory;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IYiquanProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IAccountBalanceRepository;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.dataaccess.IYiquanRepository;
import org.trinity.yqyl.repository.business.entity.AccountBalance;
import org.trinity.yqyl.repository.business.entity.User;
import org.trinity.yqyl.repository.business.entity.Yiquan;

@Service
public class YiquanProcessController
        extends AbstractAutowiredCrudProcessController<Yiquan, YiquanDto, YiquanSearchingDto, IYiquanRepository>
        implements IYiquanProcessController {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IAccountBalanceRepository accountBalanceRepository;

    @Override
    @Transactional(rollbackOn = IException.class)
    public void bindMe(final YiquanDto yiquanDto) throws IException {
        if (StringUtils.isEmpty(yiquanDto.getCode())) {
            return;
        }

        final String username = getSecurityUtil().getCurrentToken().getUsername();

        final User user = userRepository.findOneByUsername(username);

        if (user.getYiquan() != null && user.getYiquanCode().equals(yiquanDto.getCode())) {
            return;
        }

        if (user.getYiquan() != null && !user.getYiquanCode().equals(yiquanDto.getCode())) {
            getSecurityUtil().checkAccessRight(CheckMode.ANY, AccessRight.SUPER_USER);
        }

        Yiquan yiquan = getDomainEntityRepository().findOneByCode(yiquanDto.getCode());

        // TODO Test code here. If new yiquan code is found. Should query the API to get the yiquan balance.
        yiquan = new Yiquan();
        yiquan.setCellphone("");
        yiquan.setCode(yiquanDto.getCode());
        yiquan.setStatus(RecordStatus.ACTIVE);
        getDomainEntityRepository().save(yiquan);
        final double yiquanBalance = 2000d;

        user.setYiquan(yiquan);
        user.setYiquanCode(yiquan.getCode());

        userRepository.save(user);

        final AccountBalance accountBalance = new AccountBalance();
        accountBalance.setAmount(yiquanBalance);
        accountBalance.setCategory(AccountCategory.YIQUAN);
        accountBalance.setStatus(AccountBalanceStatus.ACTIVE);
        accountBalance.setAccount(user.getAccount());

        accountBalanceRepository.save(accountBalance);
    }

    @Override
    @Transactional(rollbackOn = IException.class)
    public List<YiquanDto> getMe(final YiquanSearchingDto dto) throws IException {
        final String username = getSecurityUtil().getCurrentToken().getUsername();

        final User user = userRepository.findOneByUsername(username);

        final Yiquan yiquan = user.getYiquan();

        if (yiquan == null) {
            return Collections.emptyList();
        }

        final YiquanDto yiquanDto = getDomainObjectConverter().convert(yiquan);

        final List<YiquanDto> result = new ArrayList<>();
        result.add(yiquanDto);

        return result;
    }

    @Override
    protected boolean canAccessAllStatus() {
        return true;
    }
}
