package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.message.ILookupMessage;
import org.trinity.message.IMessageResolverChain;
import org.trinity.process.converter.AbstractObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.LookupDto;

@Component
public class LookupConverter extends AbstractObjectConverter<ILookupMessage<?>, LookupDto> {
    @Autowired
    private IMessageResolverChain messageResolver;

    @Override
    protected void convertBackInternal(final LookupDto source, final ILookupMessage<?> target, final CopyPolicy copyPolicy) {
    }

    @Override
    protected void convertInternal(final ILookupMessage<?> source, final LookupDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getMessageCode, target::getCode, target::setCode, copyPolicy);
        copyObject(() -> messageResolver.getMessage(source), target::getMessage, target::setMessage, copyPolicy);
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
