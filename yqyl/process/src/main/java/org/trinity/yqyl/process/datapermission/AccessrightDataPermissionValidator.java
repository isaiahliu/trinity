package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.Accessright;

@Component
public class AccessrightDataPermissionValidator extends AbstractDataPermissionValidator<Accessright> {
	@Override
	public void checkSpecialPermission() throws IException {
	}

	@Override
	public Class<Accessright> getEntityType() {
		return Accessright.class;
	}

	@Override
	protected void validateData(final String username, final Long id) throws IException {
	}
}
