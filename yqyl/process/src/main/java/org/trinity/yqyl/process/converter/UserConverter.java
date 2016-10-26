package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.UserDto;
import org.trinity.yqyl.common.message.lookup.UserStatus;
import org.trinity.yqyl.repository.business.entity.User;

@Component
public class UserConverter extends AbstractLookupSupportObjectConverter<User, UserDto> {
    private static enum TokenRelationship {
    }

    @Autowired
    public UserConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final UserDto source, final User target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getUsername, target::getUsername, target::setUsername, copyPolicy);
        copyObject(source::getPassword, target::getPassword, target::setPassword, copyPolicy);
        copyObject(source::getCellphone, target::getCellphone, target::setCellphone, copyPolicy);
        copyObject(source::getEmail, target::getEmail, target::setEmail, copyPolicy);
        copyObject(source::getYiquanCode, target::getYiquanCode, target::setYiquanCode, copyPolicy);
        copyLookup(source::getStatus, target::getStatus, target::setStatus, UserStatus.class, copyPolicy);
    }

    @Override
    protected void convertInternal(final User source, final UserDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getUsername, target::getUsername, target::setUsername, copyPolicy);
        copyObject(source::getPassword, target::getPassword, target::setPassword, copyPolicy);
        copyObject(source::getCellphone, target::getCellphone, target::setCellphone, copyPolicy);
        copyObject(source::getEmail, target::getEmail, target::setEmail, copyPolicy);
        copyObject(source::getYiquanCode, target::getYiquanCode, target::setYiquanCode, copyPolicy);
        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
    }

    @Override
    protected void convertRelationshipInternal(final User source, final UserDto target,
            final RelationshipExpression relationshipExpression) {
        switch (relationshipExpression.getName(TokenRelationship.class)) {
        default:
            break;
        }
    }

    @Override
    protected User createFromInstance() {
        return new User();
    }

    @Override
    protected UserDto createToInstance() {
        return new UserDto();
    }
}
