package org.trinity.process.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.trinity.common.dto.object.RelationshipExpression;

public abstract class AbstractObjectConverter<TEntity, TDto> implements IObjectConverter<TEntity, TDto> {
    @Override
    public TDto convert(final TEntity source, final RelationshipExpression relationshipExpression) {
        return convert(source, createToInstance(), CopyPolicy.ALWAYS, relationshipExpression);
    }

    @Override
    public TDto convert(final TEntity source, final TDto target, final CopyPolicy copyPolicy,
            final RelationshipExpression relationshipExpression) {
        convertInternal(source, target, copyPolicy);
        if (relationshipExpression != null) {
            relationshipExpression.getChildren().forEach(item -> {
                convertRelationshipInternal(source, target, item);
            });
        }
        return target;
    }

    @Override
    public TEntity convertBack(final TDto source) {
        return convertBack(source, createFromInstance(), CopyPolicy.ALWAYS);
    }

    @Override
    public TEntity convertBack(final TDto source, final TEntity target, final CopyPolicy copyPolicy) {
        convertBackInternal(source, target, copyPolicy);
        return target;
    }

    protected abstract void convertBackInternal(TDto source, TEntity target, final CopyPolicy copyPolicy);

    protected abstract void convertInternal(TEntity source, TDto target, final CopyPolicy copyPolicy);

    protected abstract void convertRelationshipInternal(final TEntity source, final TDto target,
            final RelationshipExpression relationshipExpression);

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

    protected <T> void copyObjectList(final Supplier<List<T>> sourceGetter, final Consumer<List<T>> targetSetter) {
        final List<T> enityList = sourceGetter.get();
        if (enityList != null) {
            final List<T> targetList = new ArrayList<>();
            targetList.addAll(enityList);
            targetSetter.accept(targetList);
        }
    }

    protected <TCopyEntity, TCopyDto> void copyRelationship(final Supplier<TCopyEntity> sourceGetter, final Consumer<TCopyDto> targetSetter,
            final IObjectConverter<TCopyEntity, TCopyDto> converter, final RelationshipExpression relationshipExpression) {
        final TCopyEntity enity = sourceGetter.get();
        if (enity != null) {
            targetSetter.accept(converter.convert(enity, relationshipExpression));
        }
    }

    protected <TCopyEntity, TCopyDto> void copyRelationshipList(final Supplier<List<TCopyEntity>> sourceGetter,
            final Consumer<List<TCopyDto>> targetSetter, final IObjectConverter<TCopyEntity, TCopyDto> converter,
            final RelationshipExpression relationshipExpression) {
        final List<TCopyEntity> enityList = sourceGetter.get();
        if (enityList != null) {
            targetSetter.accept(converter.convert(enityList, relationshipExpression));
        }
    }

    protected abstract TEntity createFromInstance();

    protected abstract TDto createToInstance();

    protected <T> Supplier<T> nullGetter() {
        return () -> null;
    }
}
