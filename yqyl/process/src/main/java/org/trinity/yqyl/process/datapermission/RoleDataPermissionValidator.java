package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.Role;

@Component
public class RoleDataPermissionValidator extends AbstractDataPermissionValidator<Role> {
	@Override
	public void checkSpecialPermission() throws IException {
	}

	@Override
	public Class<Role> getEntityType() {
		return Role.class;
	}

	@Override
	protected void validateData(final String username, final Long id) throws IException {
	}
}
