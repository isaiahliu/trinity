package org.trinity.common.util;

public class Tuple3<T1, T2, T3> {
    private final T1 item1;
    private final T2 item2;
    private final T3 item3;

    public Tuple3(final T1 item1, final T2 item2, final T3 item3) {
        super();
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }

    public T1 getItem1() {
        return item1;
    }

    public T2 getItem2() {
        return item2;
    }

    public T3 getItem3() {
        return item3;
    }
}
