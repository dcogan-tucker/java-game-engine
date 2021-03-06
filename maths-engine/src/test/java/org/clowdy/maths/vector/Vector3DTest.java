package org.clowdy.maths.vector;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Vector3DTest extends VectorTest<Vector3D> {
    private static final float PRECISION = 0.000001f;

    @Override
    protected Vector3D newVectorInstance() {
        return new Vector3D();
    }

    @Test
    public void crossVectorWithNullThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> vector.cross(null),
                "The input Vector cannot be null.");
    }

    @Test
    public void crossVectorWithOtherVectorReturnsVector() {
        vector.set(4, -6, 9);
        Vector3D otherVector = new Vector3D(11, 5, -3);

        Vector3D actual = vector.cross(otherVector);
        Vector3D expected = new Vector3D(-27, 111, 86);

        assertEquals(vector, actual);
        assertEquals(expected, actual);
    }

    @Test
    public void dotProductVectorWithOtherVectorReturnsFloatValue() {
        vector.set(25);
        Vector3D otherVector = new Vector3D(-1);

        float actual = vector.dot(otherVector);

        assertEquals(-75f, actual, PRECISION);
    }

    @Test
    public void lengthOfVectorReturnsFloatValue() {
        vector.set(25);

        float actual = vector.length();
        float expected = 43.301270189f;

        assertEquals(expected, actual, PRECISION);
    }

    @Test
    public void toStringReturnsCorrectFormat() {
        vector.set(-123, 321, 0.123f);

        String actual = vector.toString();
        String expected = "(-123, 321, 0.123)";

        assertEquals(expected, actual);
    }
}
