package org.trinity.process.converter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.trinity.common.dto.object.RelationshipExpression;

public interface IObjectConverter<TEntity, TDto> {
    public static enum CopyPolicy {
        ALWAYS,
        SOURCE_IS_NOT_NULL,
        TARGET_IS_NULL;
    }

    default List<TDto> convert(final Iterable<TEntity> sourceList) {
        return convert(sourceList, null);
    }

    default List<TDto> convert(final Iterable<TEntity> sourceList, final RelationshipExpression relationshipExpression) {
        return StreamSupport.stream(sourceList.spliterator(), false).map(item -> convert(item, relationshipExpression))
                .collect(Collectors.toList());
    }

    default TDto convert(final TEntity source) {
        return convert(source, null);
    }

    TDto convert(TEntity source, RelationshipExpression relationshipExpression);

    default TDto convert(final TEntity source, final TDto target, final CopyPolicy copyPolicy) {
        return convert(source, target, copyPolicy, null);
    }

    TDto convert(TEntity source, TDto target, CopyPolicy copyPolicy, RelationshipExpression relationshipExpression);

    default List<TEntity> convertBack(final Iterable<TDto> sourceList) {
        return StreamSupport.stream(sourceList.spliterator(), false).map(item -> convertBack(item)).collect(Collectors.toList());
    }

    TEntity convertBack(TDto source);

    TEntity convertBack(TDto source, TEntity target, CopyPolicy copyPolicy);
}
