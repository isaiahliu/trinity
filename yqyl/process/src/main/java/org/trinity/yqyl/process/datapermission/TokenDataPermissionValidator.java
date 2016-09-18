package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.Token;

@Component
public class TokenDataPermissionValidator extends AbstractDataPermissionValidator<Token> {
	@Override
	public void checkSpecialPermission() throws IException {
	}

	@Override
	public Class<Token> getEntityType() {
		return Token.class;
	}

	@Override
	protected void validateData(final String username, final Long id) throws IException {
	}
}