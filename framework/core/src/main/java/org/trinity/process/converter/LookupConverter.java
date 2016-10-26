package org.trinity.process.converter;

import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.message.ILookupMessage;
import org.trinity.message.IMessageResolverChain;

public class LookupConverter extends AbstractObjectConverter<ILookupMessage<?>, LookupDto> {
    private final IMessageResolverChain messageResolver;

    public LookupConverter(final IMessageResolverChain messageResolver) {
        this.messageResolver = messageResolver;
    }

    @Override
    protected void convertBackInternal(final LookupDto source, final ILookupMessage<?> target, final CopyPolicy copyPolicy) {
    }

    @Override
    protected void convertInternal(final ILookupMessage<?> source, final LookupDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getMessageCode, target::getCode, target::setCode, copyPolicy);
        copyObject(() -> messageResolver.getMessage(source), target::getMessage, target::setMessage, copyPolicy);
    }

    @Override
    protected void convertRelationshipInternal(final ILookupMessage<?> source, final LookupDto target,
            final RelationshipExpression relationshipExpression) {
    }

    @Override
    protected ILookupMessage<?> createFromInstance() {
        return null;
    }

    @Override
    protected LookupDto createToInstance() {
        return new LookupDto();
    }

}
