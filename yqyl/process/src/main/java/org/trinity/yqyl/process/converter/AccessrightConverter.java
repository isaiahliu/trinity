package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.AccessrightDto;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.repository.business.entity.Accessright;

@Component
public class AccessrightConverter extends AbstractLookupSupportObjectConverter<Accessright, AccessrightDto> {
    private static enum AccessrightRelationship {
        CHILDREN
    }

    @Autowired
    public AccessrightConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final AccessrightDto source, final Accessright target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyLookup(source::getName, target::getName, target::setName, AccessRight.class, copyPolicy);
        copyObject(source::getDescription, target::getDescription, target::setDescription, copyPolicy);
        copyLookup(source::getStatus, target::getStatus, target::setStatus, RecordStatus.class, copyPolicy);
    }

    @Override
    protected void convertInternal(final Accessright source, final AccessrightDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyMessage(source::getName, target::getName, target::setName, copyPolicy);
        copyObject(source::getDescription, target::getDescription, target::setDescription, copyPolicy);
        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
    }

    @Override
    protected void convertRelationshipInternal(final Accessright source, final AccessrightDto target,
            final RelationshipExpression relationshipExpression) {
        switch (relationshipExpression.getName(AccessrightRelationship.class)) {
        case CHILDREN:
            copyRelationshipList(source::getChildren, target::setChildren, this, relationshipExpression);
            break;
        default:
            break;
        }
    };

    @Override
    protected Accessright createFromInstance() {
        return new Accessright();
    }

    @Override
    protected AccessrightDto createToInstance() {
        return new AccessrightDto();
    }
}
