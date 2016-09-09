package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.TokenDto;
import org.trinity.yqyl.common.message.lookup.TokenStatus;
import org.trinity.yqyl.repository.business.entity.Token;
import org.trinity.yqyl.repository.business.entity.User;

@Component
public class TokenConverter extends AbstractLookupSupportObjectConverter<Token, TokenDto> {
    @Override
    protected void convertBackInternal(final TokenDto source, final Token target, final CopyPolicy copyPolicy) {
        copyObject(source::getToken, target::getToken, target::setToken, copyPolicy);
        copyLookup(source::getStatus, target::getStatus, target::setStatus, TokenStatus.class, copyPolicy);
    }

    @Override
    protected void convertInternal(final Token source, final TokenDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getToken, target::getToken, target::setToken, copyPolicy);
        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
        final User user = source.getUser();
        if (user != null) {
            copyObject(user::getUsername, target::getUsername, target::setUsername, copyPolicy);
        }
    }

    @Override
    protected Token createFromInstance() {
        return new Token();
    }

    @Override
    protected TokenDto createToInstance() {
        return new TokenDto();
    }

}
