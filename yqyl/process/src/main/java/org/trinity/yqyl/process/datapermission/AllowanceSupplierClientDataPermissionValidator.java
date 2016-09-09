package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.AllowanceSupplierClient;

@Component
public class AllowanceSupplierClientDataPermissionValidator extends AbstractDataPermissionValidator<AllowanceSupplierClient> {
	@Override
	public void checkSpecialPermission() throws IException {
	}

	@Override
	public Class<AllowanceSupplierClient> getEntityType() {
		return AllowanceSupplierClient.class;
	}

	@Override
	protected void validateData(final String username, final Long id) throws IException {
	}
}
