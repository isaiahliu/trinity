package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.trinity.common.accessright.ISecurityUtil;
import org.trinity.common.accessright.ISecurityUtil.CheckMode;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.message.dto.domain.YiquanDto;
import org.trinity.yqyl.common.message.dto.domain.YiquanSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IYiquanProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.dataaccess.IYiquanRepository;
import org.trinity.yqyl.repository.business.entity.User;
import org.trinity.yqyl.repository.business.entity.Yiquan;

@Service
public class YiquanProcessController
        extends AbstractAutowiredCrudProcessController<Yiquan, YiquanDto, YiquanSearchingDto, IYiquanRepository>
        implements IYiquanProcessController {
    @Autowired
    private ISecurityUtil<AccessRight> securityUtil;

    @Autowired
    private IUserRepository userRepository;

    public YiquanProcessController() {
        super(Yiquan.class, ErrorMessage.UNABLE_TO_FIND_YIQUAN);
    }

    @Override
    @Transactional
    public void bindMe(final YiquanDto yiquanCode) throws IException {
        if (StringUtils.isEmpty(yiquanCode.getCode())) {
            return;
        }

        final String username = securityUtil.getCurrentToken().getUsername();

        final User user = userRepository.findOneByUsername(username);

        if (user.getYiquan() != null && user.getYiquanCode().equals(yiquanCode.getCode())) {
            return;
        }

        if (user.getYiquan() != null && !user.getYiquanCode().equals(yiquanCode.getCode())) {
            securityUtil.checkAccessRight(CheckMode.ANY, AccessRight.SUPER_USER);
        }

        final Yiquan yiquan = getDomainEntityRepository().findOneByCode(yiquanCode.getCode());

        user.setYiquan(yiquan);
        user.setYiquanCode(yiquan.getCode());

        userRepository.save(user);
    }

    @Override
    @Transactional
    public List<YiquanDto> getMe() throws IException {
        final String username = securityUtil.getCurrentToken().getUsername();

        final User user = userRepository.findOneByUsername(username);

        final Yiquan yiquan = user.getYiquan();

        if (yiquan == null) {
            return super.getMe();
        }

        final YiquanDto yiquanDto = getDomainObjectConverter().convert(yiquan);

        final List<YiquanDto> result = new ArrayList<>();
        result.add(yiquanDto);

        return result;
    }
}
