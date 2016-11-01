package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.repository.business.entity.Lookup;

@Component
public class LookupConverter extends AbstractLookupSupportObjectConverter<Lookup, LookupDto> {
    private static enum LookupRelationship {
        NA
    }

    @Autowired
    public LookupConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final LookupDto source, final Lookup target, final CopyPolicy copyPolicy) {
    }

    @Override
    protected void convertInternal(final Lookup source, final LookupDto target, final CopyPolicy copyPolicy) {
    }

    @Override
    protected void convertRelationshipInternal(final Lookup source, final LookupDto target,
            final RelationshipExpression relationshipExpression) {
        switch (relationshipExpression.getName(LookupRelationship.class)) {
        default:
            break;
        }
    }

    @Override
    protected Lookup createFromInstance() {
        return new Lookup();
    }

    @Override
    protected LookupDto createToInstance() {
        return new LookupDto();
    }
}
