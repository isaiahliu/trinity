package org.trinity.yqyl.process.controller;

import org.springframework.stereotype.Service;
import org.trinity.yqyl.common.message.dto.domain.UserGroupDto;
import org.trinity.yqyl.common.message.dto.domain.UserGroupSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IUserGroupProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IUserGroupRepository;
import org.trinity.yqyl.repository.business.entity.UserGroup;

@Service
public class UserGroupProcessController
        extends AbstractAutowiredCrudProcessController<UserGroup, UserGroupDto, UserGroupSearchingDto, IUserGroupRepository>
        implements IUserGroupProcessController {
    public UserGroupProcessController() {
        super(UserGroup.class, ErrorMessage.UNABLE_TO_FIND_USER_GROUP);
    }
}
