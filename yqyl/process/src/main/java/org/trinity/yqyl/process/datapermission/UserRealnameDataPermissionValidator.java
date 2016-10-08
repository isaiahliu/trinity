package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.UserRealname;

@Component
public class UserRealnameDataPermissionValidator
        extends AbstractDataPermissionValidator<UserRealname> {
    @Override
    public void checkSpecialPermission() throws IException {
    }

    @Override
    public Class<UserRealname> getEntityType() {
        return UserRealname.class;
    }

    @Override
    protected void validateData(final String username, final Long id) throws IException {
    }
}
