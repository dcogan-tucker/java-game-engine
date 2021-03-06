package org.clowdy.util;

public class StringFormatter {
    public static String removeTrailingZero(float value) {
        return value == (long) value ?
                Long.toString((long) value) : Float.toString(value);
    }
}
