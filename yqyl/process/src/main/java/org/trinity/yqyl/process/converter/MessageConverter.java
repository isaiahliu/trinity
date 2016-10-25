package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.MessageDto;
import org.trinity.yqyl.repository.business.entity.Message;

@Component
public class MessageConverter extends AbstractLookupSupportObjectConverter<Message, MessageDto> {
    @Autowired
    public MessageConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final MessageDto source, final Message target, final CopyPolicy copyPolicy) {
    }

    @Override
    protected void convertInternal(final Message source, final MessageDto target, final CopyPolicy copyPolicy) {
    }

    @Override
    protected Message createFromInstance() {
        return new Message();
    }

    @Override
    protected MessageDto createToInstance() {
        return new MessageDto();
    }
}
