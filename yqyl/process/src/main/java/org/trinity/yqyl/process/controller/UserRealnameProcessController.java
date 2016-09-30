package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trinity.common.accessright.ISecurityUtil;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.message.dto.domain.UserRealnameDto;
import org.trinity.yqyl.common.message.dto.domain.UserRealnameSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.CredentialType;
import org.trinity.yqyl.common.message.lookup.RealnameStatus;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IUserRealnameProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IContentRepository;
import org.trinity.yqyl.repository.business.dataaccess.IUserRealnameRepository;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.entity.Content;
import org.trinity.yqyl.repository.business.entity.User;
import org.trinity.yqyl.repository.business.entity.UserRealname;

@Service
public class UserRealnameProcessController
        extends AbstractAutowiredCrudProcessController<UserRealname, UserRealnameDto, UserRealnameSearchingDto, IUserRealnameRepository>
        implements IUserRealnameProcessController {
    @Autowired
    private ISecurityUtil<AccessRight> securityUtil;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IContentRepository contentRepository;

    public UserRealnameProcessController() {
        super(UserRealname.class, ErrorMessage.UNABLE_TO_FIND_USER_REALNAME);
    }

    @Override
    @Transactional
    public List<UserRealnameDto> getMe() throws IException {
        final String username = securityUtil.getCurrentToken().getUsername();
        final User user = userRepository.findOneByUsername(username);

        UserRealname userRealname = getDomainEntityRepository().findOne(user.getId());

        if (userRealname == null) {
            final Content credentialCopy = new Content();
            credentialCopy.setUuid(UUID.randomUUID().toString());
            credentialCopy.setContent(new byte[0]);
            credentialCopy.setStatus(RecordStatus.ACTIVE);

            contentRepository.save(credentialCopy);

            userRealname = new UserRealname();
            userRealname.setUser(user);
            userRealname.setUserId(user.getId());
            userRealname.setCredentialType(CredentialType.IDENTITY_CARD);
            userRealname.setCredentialNo("");
            userRealname.setCredentialCopy(credentialCopy.getUuid());
            userRealname.setName("");
            userRealname.setStatus(RealnameStatus.NOT_READY);

            getDomainEntityRepository().save(userRealname);
        }

        final UserRealnameDto userRealnameDto = getDomainObjectConverter().convert(userRealname);

        final List<UserRealnameDto> result = new ArrayList<>();
        result.add(userRealnameDto);
        return result;
    }
}
