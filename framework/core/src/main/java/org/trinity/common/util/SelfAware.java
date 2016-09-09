package org.trinity.common.util;

/**
 * @author Isaiah Liu
 * @param <T>
 */
public interface SelfAware<T> {
    void setSelf(T selfProxy);
}
