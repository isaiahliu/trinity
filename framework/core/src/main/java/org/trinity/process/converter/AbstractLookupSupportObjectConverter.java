package org.trinity.process.converter;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.util.Tuple2;
import org.trinity.message.ILookupMessage;
import org.trinity.message.LookupParser;

public abstract class AbstractLookupSupportObjectConverter<T1, T2> extends AbstractObjectConverter<T1, T2> {
    private final IObjectConverter<Tuple2<ILookupMessage<?>, String[]>, LookupDto> lookupConverter;

    public AbstractLookupSupportObjectConverter(final IObjectConverter<Tuple2<ILookupMessage<?>, String[]>, LookupDto> lookupConverter) {
        this.lookupConverter = lookupConverter;
    }

    private LookupDto convertLookup(final ILookupMessage<?> lookup, final String[] params) {
        if (params == null) {
            return lookupConverter.convert(new Tuple2<>(lookup, new String[0]));
        } else {
            return lookupConverter.convert(new Tuple2<>(lookup, params));
        }
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
        copyMessage(sourceGetter, () -> null, targetGetter, targetSetter, copyPolicy);
    }

    protected <T extends ILookupMessage<?>> void copyMessage(final Supplier<T> sourceGetter, final Supplier<String[]> paramsGetter,
            final Supplier<LookupDto> targetGetter, final Consumer<LookupDto> targetSetter, final CopyPolicy copyPolicy) {
        final T source = sourceGetter.get();
        if (source == null) {
            copyObject(() -> null, targetGetter, targetSetter, copyPolicy);
        } else {
            copyObject(() -> convertLookup(source, paramsGetter.get()), targetGetter, targetSetter, copyPolicy);
        }
    }

    protected <T extends ILookupMessage<?>> void copyMessageList(final Supplier<List<T>> sourceGetter,
            final Supplier<List<LookupDto>> targetGetter, final Consumer<List<LookupDto>> targetSetter, final CopyPolicy copyPolicy) {
        final List<T> source = sourceGetter.get();
        if (source == null) {
            copyObject(() -> null, targetGetter, targetSetter, copyPolicy);
        } else {
            copyObject(() -> source.stream().map(item -> convertLookup(item, null)).collect(Collectors.toList()), targetGetter,
                    targetSetter, copyPolicy);
        }
    }
}
