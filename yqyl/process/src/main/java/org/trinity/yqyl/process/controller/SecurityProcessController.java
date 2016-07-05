package org.trinity.yqyl.process.controller;

import java.util.Date;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.trinity.common.exception.IException;
import org.trinity.common.exception.factory.IExceptionFactory;
import org.trinity.message.IMessageResolverChain;
import org.trinity.yqyl.common.message.dto.domain.LookupDto;
import org.trinity.yqyl.common.message.dto.domain.TokenDto;
import org.trinity.yqyl.common.message.dto.domain.UserDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.TokenStatus;
import org.trinity.yqyl.repository.business.dataaccess.ITokenRepository;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.entity.Token;
import org.trinity.yqyl.repository.business.entity.User;

@Service
@Transactional
public class SecurityProcessController implements ISecurityProcessController {
    @Autowired
    private ITokenRepository tokenRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IMessageResolverChain messageResolver;
    @Autowired
    private IExceptionFactory exceptionFactory;

    @Override
    public UserDto authenticate(final String tokenName, final String username, final String password)
            throws IException {
        final User user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw exceptionFactory.createException(ErrorMessage.UNABLE_TO_FIND_USER);
        }

        if (!user.getPassword().equals(password)) {
            throw exceptionFactory.createException(ErrorMessage.WRONG_PASSWORD);
        }

        final UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());

        final Token token = tokenRepository.findOneByToken(tokenName);
        final Date now = new Date();
        token.setUser(user);
        token.setActiveTimestamp(now);
        token.setLastActiveTimestamp(now);
        token.setStatus(TokenStatus.AUTHENTICATED);
        tokenRepository.save(token);

        user.getTokens().forEach(item -> {
            if (!item.getToken().equals(tokenName) && item.getStatus() == TokenStatus.AUTHENTICATED) {
                item.setStatus(TokenStatus.LOGGED_BY_OTHERS);
                tokenRepository.save(item);
            }
        });

        return userDto;
    }

    @Override
    public TokenDto getToken(String identity) {
        if (StringUtils.isEmpty(identity)) {
            identity = UUID.randomUUID().toString();
        }

        Token token = tokenRepository.findOneByDeviceIdentity(identity);

        if (token == null) {
            token = new Token();
            token.setDeviceIdentity(identity);
            token.setToken(UUID.randomUUID().toString());
            token.setStatus(TokenStatus.UNAUTHENTICATED);

            tokenRepository.save(token);
        }

        final TokenDto tokenDto = new TokenDto();
        tokenDto.setToken(token.getToken());
        tokenDto.setStatus(new LookupDto(token.getStatus().getMessageCode(),
                messageResolver.getMessage(token.getStatus().getMessageCode())));
        if (token.getUser() != null) {
            tokenDto.setUsername(token.getUser().getUsername());
        }

        return tokenDto;
    }

    @Override
    public void logout(final String token) {
        final Token tokenItem = tokenRepository.findOneByToken(token);

        tokenItem.setUser(null);
        tokenItem.setStatus(TokenStatus.UNAUTHENTICATED);
        tokenItem.setActiveTimestamp(null);
        tokenItem.setLastActiveTimestamp(null);

        tokenRepository.save(tokenItem);
    }
}
