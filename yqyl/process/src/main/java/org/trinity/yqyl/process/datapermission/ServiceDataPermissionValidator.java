package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.Service;

@Component
public class ServiceDataPermissionValidator extends AbstractDataPermissionValidator<Service> {
	@Override
	public void checkSpecialPermission() throws IException {
	}

	@Override
	public Class<Service> getEntityType() {
		return Service.class;
	}

	@Override
	protected void validateData(final String username, final Long id) throws IException {
	}
}
