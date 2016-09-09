package org.trinity.yqyl.process.converter;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.trinity.message.ILookupMessage;
import org.trinity.message.LookupParser;
import org.trinity.process.converter.AbstractObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.LookupDto;

public abstract class AbstractLookupSupportObjectConverter<T1, T2> extends AbstractObjectConverter<T1, T2> {
    @Autowired
    private IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter;

    private LookupDto convertLookup(final ILookupMessage<?> lookup) {
        return lookupConverter.convert(lookup);
    }

    private <T extends ILookupMessage<?>> T parseLookup(final Class<T> type, final LookupDto lookupDto) {
        if (lookupDto == null || StringUtils.isEmpty(lookupDto.getCode())) {
            return null;
        }
        return LookupParser.parse(type, lookupDto.getCode());
    }

    protected <T extends ILookupMessage<?>> void copyLookup(final Supplier<LookupDto> sourceGetter, final Supplier<T> targetGetter,
            final Consumer<T> targetSetter, final Class<T> targetType, final CopyPolicy copyPolicy) {
        final LookupDto source = sourceGetter.get();
        if (source == null) {
            copyObject(() -> null, targetGetter, targetSetter, copyPolicy);
        } else {
            final T message = parseLookup(targetType, source);
            copyObject(() -> message, targetGetter, targetSetter, copyPolicy);
        }
    }

    protected <T extends ILookupMessage<?>> void copyMessage(final Supplier<T> sourceGetter, final Supplier<LookupDto> targetGetter,
            final Consumer<LookupDto> targetSetter, final CopyPolicy copyPolicy) {
        final T source = sourceGetter.get();
        if (source == null) {
            copyObject(() -> null, targetGetter, targetSetter, copyPolicy);
        } else {
            copyObject(() -> convertLookup(source), targetGetter, targetSetter, copyPolicy);
        }
    }
}
