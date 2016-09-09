package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.AnnouncementDto;
import org.trinity.yqyl.repository.business.entity.Announcement;

@Component
public class AnnouncementConverter extends AbstractLookupSupportObjectConverter<Announcement, AnnouncementDto> {
@Override
protected void convertBackInternal(final AnnouncementDto source, final Announcement target, final CopyPolicy copyPolicy) {
}

@Override
protected void convertInternal(final Announcement source, final AnnouncementDto target, final CopyPolicy copyPolicy) {
}

@Override
protected Announcement createFromInstance() {
return new Announcement();
}

@Override
protected AnnouncementDto createToInstance() {
return new AnnouncementDto();
}
}
