package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient;

@Component
public class ServiceSupplierClientDataPermissionValidator extends AbstractDataPermissionValidator<ServiceSupplierClient> {
	@Override
	public void checkSpecialPermission() throws IException {
	}

	@Override
	public Class<ServiceSupplierClient> getEntityType() {
		return ServiceSupplierClient.class;
	}

	@Override
	protected void validateData(final String username, final Long id) throws IException {
	}
}