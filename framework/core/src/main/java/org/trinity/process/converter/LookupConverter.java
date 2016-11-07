package org.trinity.process.converter;

import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.common.util.Tuple2;
import org.trinity.message.ILookupMessage;
import org.trinity.message.IMessageResolverChain;

public class LookupConverter extends AbstractObjectConverter<Tuple2<ILookupMessage<?>, String[]>, LookupDto> {
    private final IMessageResolverChain messageResolver;

    public LookupConverter(final IMessageResolverChain messageResolver) {
        this.messageResolver = messageResolver;
    }

    @Override
    protected void convertBackInternal(final LookupDto source, final Tuple2<ILookupMessage<?>, String[]> target,
            final CopyPolicy copyPolicy) {
    }

    @Override
    protected void convertInternal(final Tuple2<ILookupMessage<?>, String[]> source, final LookupDto target, final CopyPolicy copyPolicy) {
        copyObject(source.getItem1()::getMessageCode, target::getCode, target::setCode, copyPolicy);
        copyObject(() -> messageResolver.getMessage(source.getItem1(), source.getItem2()), target::getMessage, target::setMessage,
                copyPolicy);
    }

    @Override
    protected void convertRelationshipInternal(final Tuple2<ILookupMessage<?>, String[]> source, final LookupDto target,
            final RelationshipExpression relationshipExpression) {
    }

    @Override
    protected Tuple2<ILookupMessage<?>, String[]> createFromInstance() {
        return null;
    }

    @Override
    protected LookupDto createToInstance() {
        return new LookupDto();
    }
}
