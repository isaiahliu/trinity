package org.trinity.process.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class AbstractObjectConverter<T1, T2> implements IObjectConverter<T1, T2> {
    @Override
    public T2 convert(final T1 source) {
        return convert(source, createToInstance(), CopyPolicy.ALWAYS);
    }

    @Override
    public T2 convert(final T1 source, final T2 target, final CopyPolicy copyPolicy) {
        convertInternal(source, target, copyPolicy);
        return target;
    }

    @Override
    public T1 convertBack(final T2 source) {
        return convertBack(source, createFromInstance(), CopyPolicy.ALWAYS);
    }

    @Override
    public T1 convertBack(final T2 source, final T1 target, final CopyPolicy copyPolicy) {
        convertBackInternal(source, target, copyPolicy);
        return target;
    }

    protected abstract void convertBackInternal(T2 source, T1 target, final CopyPolicy copyPolicy);

    protected abstract void convertInternal(T1 source, T2 target, final CopyPolicy copyPolicy);

    protected void copyDate(final Supplier<Date> sourceGetter, final Supplier<String> targetGetter, final Consumer<String> targetSetter,
            final String dateFormat, final CopyPolicy copyPolicy) {
        final Date source = sourceGetter.get();
        final DateFormat format = new SimpleDateFormat(dateFormat);

        String temp = null;

        if (source != null) {
            temp = format.format(source);
        }

        final String sourceDate = temp;
        copyObject(() -> sourceDate, targetGetter, targetSetter, copyPolicy);
    }

    protected void copyDateString(final Supplier<String> sourceGetter, final Supplier<Date> targetGetter, final Consumer<Date> targetSetter,
            final String dateFormat, final CopyPolicy copyPolicy) {
        final String source = sourceGetter.get();
        final DateFormat format = new SimpleDateFormat(dateFormat);

        Date temp = null;

        if (source != null) {
            try {
                temp = format.parse(source);
            } catch (final ParseException e) {
            }
        }

        final Date sourceDate = temp;
        copyObject(() -> sourceDate, targetGetter, targetSetter, copyPolicy);
    }

    protected <T> void copyObject(final Supplier<T> sourceGetter, final Supplier<T> targetGetter, final Consumer<T> targetSetter,
            CopyPolicy copyPolicy) {
        if (copyPolicy == null) {
            copyPolicy = CopyPolicy.ALWAYS;
        }

        final T sourceValue = sourceGetter.get();
        final T targetValue = targetGetter.get();
        switch (copyPolicy) {
        case SOURCE_IS_NOT_NULL:
            if (sourceValue != null) {
                targetSetter.accept(sourceValue);
            }
            break;
        case TARGET_IS_NULL:
            if (targetValue == null) {
                targetSetter.accept(sourceValue);
            }
            break;
        case ALWAYS:
        default:
            targetSetter.accept(sourceValue);
            break;
        }
    }

    protected abstract T1 createFromInstance();

    protected abstract T2 createToInstance();

    protected <T> Supplier<T> nullGetter() {
        return () -> null;
    }
}
