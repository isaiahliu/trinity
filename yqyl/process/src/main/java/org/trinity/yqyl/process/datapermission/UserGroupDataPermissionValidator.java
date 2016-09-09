package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.UserGroup;

@Component
public class UserGroupDataPermissionValidator extends AbstractDataPermissionValidator<UserGroup> {
	@Override
	public void checkSpecialPermission() throws IException {
	}

	@Override
	public Class<UserGroup> getEntityType() {
		return UserGroup.class;
	}

	@Override
	protected void validateData(final String username, final Long id) throws IException {
	}
}
