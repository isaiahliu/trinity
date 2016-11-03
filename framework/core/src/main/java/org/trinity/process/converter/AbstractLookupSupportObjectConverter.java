package org.trinity.process.converter;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.trinity.common.dto.object.LookupDto;
import org.trinity.message.ILookupMessage;
import org.trinity.message.LookupParser;

public abstract class AbstractLookupSupportObjectConverter<T1, T2> extends AbstractObjectConverter<T1, T2> {
    private final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter;

    public AbstractLookupSupportObjectConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        this.lookupConverter = lookupConverter;
    }

    private LookupDto convertLookup(final ILookupMessage<?> lookup) {
        return lookupConverter.convert(lookup);
    }

    private <T extends ILookupMessage<?>> T parseLookup(final Class<T> type, final LookupDto lookupDto) {
        if (lookupDto == null || lookupDto.getCode() == null || lookupDto.getCode().length() == 0) {
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

    protected <T extends ILookupMessage<?>> void copyLookupList(final Supplier<List<LookupDto>> sourceGetter,
            final Supplier<List<T>> targetGetter, final Consumer<List<T>> targetSetter, final Class<T> targetType,
            final CopyPolicy copyPolicy) {
        final List<LookupDto> source = sourceGetter.get();
        if (source == null) {
            copyObject(() -> null, targetGetter, targetSetter, copyPolicy);
        } else {
            copyObject(() -> source.stream().map(item -> parseLookup(targetType, item)).collect(Collectors.toList()), targetGetter,
                    targetSetter, copyPolicy);
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

    protected <T extends ILookupMessage<?>> void copyMessageList(final Supplier<List<T>> sourceGetter,
            final Supplier<List<LookupDto>> targetGetter, final Consumer<List<LookupDto>> targetSetter, final CopyPolicy copyPolicy) {
        final List<T> source = sourceGetter.get();
        if (source == null) {
            copyObject(() -> null, targetGetter, targetSetter, copyPolicy);
        } else {
            copyObject(() -> source.stream().map(item -> convertLookup(item)).collect(Collectors.toList()), targetGetter, targetSetter,
                    copyPolicy);
        }
    }
}
