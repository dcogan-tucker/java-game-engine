package org.clowdy.maths.vector;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vector2DTest extends VectorTest<Vector2D> {
    private static final float PRECISION = 0.000001f;

    @Override
    protected Vector2D newVectorInstance() {
        return new Vector2D();
    }

    @Test
    public void dotProductVectorWithOtherVectorReturnsFloatValue() {
        vector.set(25);
        Vector2D otherVector = new Vector2D(-1);

        float actual = vector.dot(otherVector);

        assertEquals(-50f, actual, PRECISION);
    }

    @Test
    public void lengthOfVectorReturnsFloatValue() {
        vector.set(25);

        float actual = vector.length();
        float expected = 35.355339059f;

        assertEquals(expected, actual, PRECISION);
    }

    @Test
    public void toStringReturnsCorrectFormat() {
        vector.set(-123, 321);

        String actual = vector.toString();
        String expected = "(-123, 321)";

        assertEquals(expected, actual);
    }
}
