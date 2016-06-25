package com.utils;

public interface Function<F, T> {

    /**
     * Returns the result of applying this function to {@code input}.
     */
    T apply(F input);

    /**
     * Indicates whether another object is equal to this function.
     */
    boolean equals(Object obj);
}
