package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.AccountPosting;

@Component
public class AccountPostingDataPermissionValidator extends AbstractDataPermissionValidator<AccountPosting> {
	@Override
	public void checkSpecialPermission() throws IException {
	}

	@Override
	public Class<AccountPosting> getEntityType() {
		return AccountPosting.class;
	}

	@Override
	protected void validateData(final String username, final Long id) throws IException {
	}
}
