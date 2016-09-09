package org.trinity.process.converter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface IObjectConverter<T1, T2> {
    public static enum CopyPolicy {
        ALWAYS,
        SOURCE_IS_NOT_NULL,
        TARGET_IS_NULL;
    }

    default List<T2> convert(final Iterable<T1> sourceList) {
        return StreamSupport.stream(sourceList.spliterator(), false).map(item -> convert(item))
                .collect(Collectors.toList());
    }

    T2 convert(T1 source);

    T2 convert(T1 source, T2 target, CopyPolicy copyPolicy);

    default List<T1> convertBack(final Iterable<T2> sourceList) {
        return StreamSupport.stream(sourceList.spliterator(), false).map(item -> convertBack(item))
                .collect(Collectors.toList());
    }

    T1 convertBack(T2 source);

    T1 convertBack(T2 source, T1 target, CopyPolicy copyPolicy);
}
