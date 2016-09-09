package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.trinity.yqyl.common.message.dto.domain.ContentDto;
import org.trinity.yqyl.common.message.dto.domain.ContentSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IContentProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IContentRepository;
import org.trinity.yqyl.repository.business.entity.Content;

@Service
public class ContentProcessController
        extends AbstractAutowiredCrudProcessController<Content, ContentDto, ContentSearchingDto, IContentRepository>
        implements IContentProcessController {
    public ContentProcessController() {
        super(Content.class, ErrorMessage.UNABLE_TO_FIND_CONTENT);
    }

    @Override
    @Transactional
    public List<ContentDto> addUpdateAll(final List<ContentDto> data) {
        final List<Content> items = new ArrayList<>();

        for (final ContentDto item : data) {
            Content content;
            if (StringUtils.isEmpty(item.getUuid())) {
                content = new Content();
                content.setUuid(UUID.randomUUID().toString());
                content.setStatus(RecordStatus.ACTIVE);
            } else {
                content = getDomainEntityRepository().findOneByUuid(item.getUuid());
            }

            content.setContent(item.getContent());

            items.add(content);
        }

        getDomainEntityRepository().save(items);

        return items.stream().map(item -> {
            final ContentDto dto = new ContentDto();
            dto.setUuid(item.getUuid());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public ContentDto getOneByUuid(final String uuid) {
        Content content = getDomainEntityRepository().findOneByUuid(uuid);
        return getDomainObjectConverter().convert(content);
    }
}
