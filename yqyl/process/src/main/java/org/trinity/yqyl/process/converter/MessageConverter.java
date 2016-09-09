package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.MessageDto;
import org.trinity.yqyl.repository.business.entity.Message;

@Component
public class MessageConverter extends AbstractLookupSupportObjectConverter<Message, MessageDto> {
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
