package org.trinity.yqyl.process.controller;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trinity.common.exception.IException;
import org.trinity.common.exception.factory.IExceptionFactory;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.SecurityDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.AccountBalanceStatus;
import org.trinity.yqyl.common.message.lookup.AccountCategory;
import org.trinity.yqyl.common.message.lookup.AccountStatus;
import org.trinity.yqyl.common.message.lookup.OperatorClientStatus;
import org.trinity.yqyl.common.message.lookup.TokenStatus;
import org.trinity.yqyl.common.message.lookup.UserStatus;
import org.trinity.yqyl.process.controller.base.ISecurityProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IAccountBalanceRepository;
import org.trinity.yqyl.repository.business.dataaccess.IAccountRepository;
import org.trinity.yqyl.repository.business.dataaccess.IOperatorClientRepository;
import org.trinity.yqyl.repository.business.dataaccess.ITokenRepository;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.entity.Account;
import org.trinity.yqyl.repository.business.entity.AccountBalance;
import org.trinity.yqyl.repository.business.entity.OperatorClient;
import org.trinity.yqyl.repository.business.entity.Token;
import org.trinity.yqyl.repository.business.entity.User;

@Service
public class SecurityProcessController implements ISecurityProcessController {
	@Autowired
	private ITokenRepository tokenRepository;
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private IAccountRepository accountRepository;
	@Autowired
	private IAccountBalanceRepository accountBalanceRepository;
	@Autowired
	private IExceptionFactory exceptionFactory;
	@Autowired
	private IObjectConverter<User, SecurityDto> securityConverter;

	@Autowired
	private IOperatorClientRepository operatorClientRepository;

	@Override
	@Transactional
	public SecurityDto authenticate(final String tokenName, final String username, final String password) throws IException {
		final User user = userRepository.findOneByUsername(username);
		if (user == null) {
			throw exceptionFactory.createException(ErrorMessage.UNABLE_TO_FIND_USER);
		}

		if (!user.getPassword().equals(password)) {
			throw exceptionFactory.createException(ErrorMessage.WRONG_PASSWORD);
		}

		final SecurityDto userDto = securityConverter.convert(user);

		final Token token = tokenRepository.findOneByToken(tokenName);
		final Date now = new Date();
		token.setUser(user);
		token.setActiveTimestamp(now);
		token.setLastActiveTimestamp(now);
		token.setStatus(TokenStatus.AUTHENTICATED);
		tokenRepository.save(token);

		// user.getTokens().forEach(item -> {
		// if (!item.getToken().equals(tokenName) && item.getStatus() ==
		// TokenStatus.AUTHENTICATED) {
		// item.setStatus(TokenStatus.LOGGED_BY_OTHERS);
		// tokenRepository.save(item);
		// }
		// });

		return userDto;
	}

	@Override
	@Transactional
	public SecurityDto logout(final String tokenName) throws IException {
		final SecurityDto result = new SecurityDto();
		final Token tokenItem = tokenRepository.findOneByToken(tokenName);

		final User user = tokenItem.getUser();
		if (user != null) {
			result.setUsername(user.getUsername());
		}

		tokenItem.setStatus(TokenStatus.LOGGED_OUT);
		tokenItem.setLastActiveTimestamp(new Date());

		tokenRepository.save(tokenItem);

		return result;
	}

	@Override
	@Transactional
	public void register(final String username, final String password) throws IException {
		User user = userRepository.findOneByUsername(username);
		if (user != null) {
			throw exceptionFactory.createException(ErrorMessage.USERNAME_IS_REGISTERED);
		}

		user = new User();
		user.setUsername(username);
		user.setPassword(password);

		user.setStatus(UserStatus.ACTIVE);

		userRepository.save(user);

		final OperatorClient operatorClient = new OperatorClient();
		operatorClient.setName("");
		operatorClient.setStaffNo("");
		operatorClient.setStatus(OperatorClientStatus.INACTIVE);
		operatorClient.setUser(user);
		operatorClient.setUserId(user.getId());

		operatorClientRepository.save(operatorClient);

		final Account account = new Account();
		account.setStatus(AccountStatus.ACTIVE);
		account.setUser(user);
		account.setUserId(user.getId());

		accountRepository.save(account);

		final AccountBalance accountBalance = new AccountBalance();
		accountBalance.setAccount(account);
		accountBalance.setCategory(AccountCategory.YIQUAN);
		accountBalance.setStatus(AccountBalanceStatus.ACTIVE);

		accountBalanceRepository.save(accountBalance);
	}
}
