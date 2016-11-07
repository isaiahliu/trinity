package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.common.util.Tuple2;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.UserRealnameDto;
import org.trinity.yqyl.common.message.lookup.CredentialType;
import org.trinity.yqyl.common.message.lookup.RealnameStatus;
import org.trinity.yqyl.repository.business.entity.UserRealname;

@Component
public class UserRealnameConverter extends AbstractLookupSupportObjectConverter<UserRealname, UserRealnameDto> {
    private static enum UserRealnameRelationship {
        NA
    }

    @Autowired
    public UserRealnameConverter(final IObjectConverter<Tuple2<ILookupMessage<?>, String[]>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final UserRealnameDto source, final UserRealname target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getUserId, target::setUserId, copyPolicy);
        copyObject(source::getCredentialCopy, target::getCredentialCopy, target::setCredentialCopy, copyPolicy);
        copyObject(source::getCredentialNo, target::getCredentialNo, target::setCredentialNo, copyPolicy);
        copyObject(source::getName, target::getName, target::setName, copyPolicy);
        copyLookup(source::getStatus, target::getStatus, target::setStatus, RealnameStatus.class, copyPolicy);
        copyLookup(source::getCredentialType, target::getCredentialType, target::setCredentialType, CredentialType.class, copyPolicy);
    }

    @Override
    protected void convertInternal(final UserRealname source, final UserRealnameDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getUserId, target::getId, target::setId, copyPolicy);
        copyObject(source::getCredentialCopy, target::getCredentialCopy, target::setCredentialCopy, copyPolicy);
        copyObject(source::getCredentialNo, target::getCredentialNo, target::setCredentialNo, copyPolicy);
        copyObject(source::getName, target::getName, target::setName, copyPolicy);
        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
        copyMessage(source::getCredentialType, target::getCredentialType, target::setCredentialType, copyPolicy);
    }

    @Override
    protected void convertRelationshipInternal(final UserRealname source, final UserRealnameDto target,
            final RelationshipExpression relationshipExpression) {
        switch (relationshipExpression.getName(UserRealnameRelationship.class)) {
        default:
            break;
        }
    }

    @Override
    protected UserRealname createFromInstance() {
        return new UserRealname();
    }

    @Override
    protected UserRealnameDto createToInstance() {
        return new UserRealnameDto();
    }
}
