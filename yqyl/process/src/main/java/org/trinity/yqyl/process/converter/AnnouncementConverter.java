package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.AnnouncementDto;
import org.trinity.yqyl.repository.business.entity.Announcement;

@Component
public class AnnouncementConverter extends AbstractLookupSupportObjectConverter<Announcement, AnnouncementDto> {
    @Autowired
    public AnnouncementConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

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
