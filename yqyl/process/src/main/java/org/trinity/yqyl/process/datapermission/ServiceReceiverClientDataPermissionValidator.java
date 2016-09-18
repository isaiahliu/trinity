package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClient;

@Component
public class ServiceReceiverClientDataPermissionValidator extends AbstractDataPermissionValidator<ServiceReceiverClient> {
	@Override
	public void checkSpecialPermission() throws IException {
	}

	@Override
	public Class<ServiceReceiverClient> getEntityType() {
		return ServiceReceiverClient.class;
	}

	@Override
	protected void validateData(final String username, final Long id) throws IException {
	}
}