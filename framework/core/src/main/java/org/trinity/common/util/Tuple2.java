package org.trinity.common.util;

public class Tuple2<T1, T2> {
    private final T1 item1;
    private final T2 item2;

    public Tuple2(final T1 item1, final T2 item2) {
        super();
        this.item1 = item1;
        this.item2 = item2;
    }

    public T1 getItem1() {
        return item1;
    }

    public T2 getItem2() {
        return item2;
    }
}
