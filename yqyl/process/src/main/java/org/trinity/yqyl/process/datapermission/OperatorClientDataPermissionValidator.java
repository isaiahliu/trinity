package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.OperatorClient;

@Component
public class OperatorClientDataPermissionValidator extends AbstractDataPermissionValidator<OperatorClient> {
	@Override
	public void checkSpecialPermission() throws IException {
	}

	@Override
	public Class<OperatorClient> getEntityType() {
		return OperatorClient.class;
	}

	@Override
	protected void validateData(final String username, final Long id) throws IException {
	}
}
