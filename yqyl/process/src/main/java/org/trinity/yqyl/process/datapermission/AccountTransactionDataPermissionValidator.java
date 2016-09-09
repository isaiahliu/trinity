package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.AccountTransaction;

@Component
public class AccountTransactionDataPermissionValidator extends AbstractDataPermissionValidator<AccountTransaction> {
	@Override
	public void checkSpecialPermission() throws IException {
	}

	@Override
	public Class<AccountTransaction> getEntityType() {
		return AccountTransaction.class;
	}

	@Override
	protected void validateData(final String username, final Long id) throws IException {
	}
}
