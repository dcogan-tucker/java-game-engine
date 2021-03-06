package org.clowdy.maths.vector;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vector4DTest extends VectorTest<Vector4D> {
    private static final float PRECISION = 0.000001f;

    @Override
    protected Vector4D newVectorInstance() {
        return new Vector4D();
    }

    @Test
    public void dotProductVectorWithOtherVectorReturnsFloatValue() {
        vector.set(25);
        Vector4D otherVector = new Vector4D(-1);

        float actual = vector.dot(otherVector);

        assertEquals(-100f, actual, PRECISION);
    }

    @Test
    public void lengthOfVectorReturnsFloatValue() {
        vector.set(25);

        float actual = vector.length();
        float expected = 50f;

        assertEquals(expected, actual, PRECISION);
    }

    @Test
    public void toStringReturnsCorrectFormat() {
        vector.set(-123, 321, 0.123f, -0.321f);

        String actual = vector.toString();
        String expected = "(-123, 321, 0.123, -0.321)";

        assertEquals(expected, actual);
    }
}
