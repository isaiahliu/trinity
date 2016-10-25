package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.FavoriteDto;
import org.trinity.yqyl.repository.business.entity.Favorite;

@Component
public class FavoriteConverter extends AbstractLookupSupportObjectConverter<Favorite, FavoriteDto> {
    @Autowired
    public FavoriteConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final FavoriteDto source, final Favorite target, final CopyPolicy copyPolicy) {
    }

    @Override
    protected void convertInternal(final Favorite source, final FavoriteDto target, final CopyPolicy copyPolicy) {
    }

    @Override
    protected Favorite createFromInstance() {
        return new Favorite();
    }

    @Override
    protected FavoriteDto createToInstance() {
        return new FavoriteDto();
    }
}
