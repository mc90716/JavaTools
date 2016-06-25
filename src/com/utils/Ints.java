package com.utils;


public final class Ints {

    /**
     * The largest power of two that can be represented as an int.
     */
    public static final int MAX_POWER_OF_TWO = 1 << (Integer.SIZE - 2);

    /**
     * Returns the {@code int} value that is equal to {@code value}, if possible.
     */
    public static int checkedCast(long value) {
        int result = (int) value;
        return result;
    }

    /**
     * Returns the {@code int} nearest in value to {@code value}.
     */
    public static int saturatedCast(long value) {
        if (value > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (value < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) value;
    }

    private Ints() {}
}
