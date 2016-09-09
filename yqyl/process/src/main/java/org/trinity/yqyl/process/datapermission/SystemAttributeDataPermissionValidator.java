package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.SystemAttribute;

@Component
public class SystemAttributeDataPermissionValidator extends AbstractDataPermissionValidator<SystemAttribute> {
	@Override
	public void checkSpecialPermission() throws IException {
	}

	@Override
	public Class<SystemAttribute> getEntityType() {
		return SystemAttribute.class;
	}

	@Override
	protected void validateData(final String username, final Long id) throws IException {
	}
}
