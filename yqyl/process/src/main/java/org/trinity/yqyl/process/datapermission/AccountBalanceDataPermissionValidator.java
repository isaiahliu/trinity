package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.AccountBalance;

@Component
public class AccountBalanceDataPermissionValidator extends AbstractDataPermissionValidator<AccountBalance> {
	@Override
	public void checkSpecialPermission() throws IException {
	}

	@Override
	public Class<AccountBalance> getEntityType() {
		return AccountBalance.class;
	}

	@Override
	protected void validateData(final String username, final Long id) throws IException {
	}
}