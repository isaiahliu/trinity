package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.ContentDto;
import org.trinity.yqyl.repository.business.entity.Content;

@Component
public class ContentConverter extends AbstractLookupSupportObjectConverter<Content, ContentDto> {
    @Override
    protected void convertBackInternal(final ContentDto source, final Content target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getUuid, target::getUuid, target::setUuid, copyPolicy);
        copyObject(source::getContent, target::getContent, target::setContent, copyPolicy);
    }

    @Override
    protected void convertInternal(final Content source, final ContentDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getUuid, target::getUuid, target::setUuid, copyPolicy);
        copyObject(source::getContent, target::getContent, target::setContent, copyPolicy);
    }

    @Override
    protected Content createFromInstance() {
        return new Content();
    }

    @Override
    protected ContentDto createToInstance() {
        return new ContentDto();
    }
}
