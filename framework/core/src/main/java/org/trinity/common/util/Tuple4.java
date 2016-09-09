package org.trinity.common.util;

/**
 * @author Isaiah Liu
 *
 * @param <T1>
 * @param <T2>
 * @param <T3>
 * @param <T4>
 */
public class Tuple4<T1, T2, T3, T4> {
    private final T1 item1;
    private final T2 item2;
    private final T3 item3;
    private final T4 item4;

    public Tuple4(final T1 item1, final T2 item2, final T3 item3, final T4 item4) {
        super();
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
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

    public T4 getItem4() {
        return item4;
    }
}
