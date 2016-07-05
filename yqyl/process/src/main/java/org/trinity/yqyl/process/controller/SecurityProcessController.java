package org.trinity.yqyl.process.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trinity.yqyl.common.message.lookup.TokenStatus;
import org.trinity.yqyl.repository.business.dataaccess.ITokenRepository;
import org.trinity.yqyl.repository.business.entity.Token;

@Service
public class SecurityProcessController implements ISecurityProcessController {
	@Autowired
	private ITokenRepository tokenRepository;

	@Override
	@Transactional
	public void logout(final String token) {
		final Token tokenItem = tokenRepository.findOneByToken(token);

		tokenItem.setUser(null);
		tokenItem.setStatus(TokenStatus.UNAUTHENTICATED);
		tokenItem.setActiveTimestamp(null);
		tokenItem.setLastActiveTimestamp(null);

		tokenRepository.save(tokenItem);
	}
}
