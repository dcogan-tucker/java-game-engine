package org.clowdy.maths.vector;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class VectorTest<V extends AbstractVector<V>> {
    protected V vector;

    protected abstract V newVectorInstance();

    @BeforeEach
    public void setUp() {
        vector = newVectorInstance();
    }

    // Add Method Tests
    @Test
    public void addOtherVectorToVectorReturnsVector() {
        /* Setting up to 4 values, implementation uses number of values
         * up to their number of dimension.
         */
        vector.set(1, 2, 3, 4);

        V otherVector = newVectorInstance().set(4, 3, 2, 1);

        V actual = vector.add(otherVector);
        V expected = newVectorInstance().set(5, 5, 5, 5);

        // The calling instance should be returned.
        assertSame(actual, vector);
        // The actual field values should equal the expected.
        assertEquals(expected, actual);
    }

    @Test
    public void addNullToVectorThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> vector.add(null),
                "The input Vector cannot be null.");
    }


    // Subtract Method Tests

    @Test
    public void subtractOtherVectorFromVectorReturnsVector() {
        vector.set(-1, 2, -3, 4);

        V otherVector = newVectorInstance().set(1, 2, 3, 4);

        V actual = vector.subtract(otherVector);
        V expected = newVectorInstance().set(-2, 0, -6, 0);

        assertSame(actual, vector);
        assertEquals(expected, actual);
    }

    @Test
    public void subtractNullFromVectorThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> vector.subtract(null),
                "The input Vector cannot be null.");
    }


    // Multiply Method Tests

    @Test
    public void multiplyVectorByScalarValueReturnsVector() {
        vector.set(3, -7, 11, 6);

        V actual = vector.multiply(10);
        V expected = newVectorInstance().set(30, -70, 110, 60);

        assertSame(actual, vector);
        assertEquals(expected, actual);
    }

    @Test
    public void dotProductOfVectorAndNullThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> vector.dot(null),
                "The input Vector cannot be null.");
    }


    // Divide Method Tests

    @Test
    public void divideVectorByNonZeroReturnsVector() {
        vector.set(11, -5, 4, -6);

        float scalar = -1.5f;
        V actual = vector.divide(scalar);
        V expected = newVectorInstance().set(11, -5, 4, -6).multiply(1f / scalar);

        assertSame(actual, vector);
        assertEquals(expected, actual);
    }

    @Test
    public void divideVectorByZeroThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> vector.divide(0),
                "Can't divide a vector by 0.");
    }


    // Negate Method Tests

    @Test
    public void negateVectorReturnsVector() {
        vector.set(-1, 4, -100, 0);

        V actual = vector.negate();
        V expected = newVectorInstance().set(1, -4, 100, 0);

        assertSame(actual, vector);
        assertEquals(expected, actual);
    }


    // Normalise Method Tests

    @Test
    public void normaliseNonZeroVectorReturnsVector() {
        vector.set(25, -4, 3, 2);
        float length = vector.length();

        V actual = vector.normalise();
        V expected = vector.set(25, -4, 3, 2).divide(length);

        assertSame(actual, vector);
        assertEquals(expected, actual);
    }

    @Test
    public void normaliseZeroVectorThrowsIllegalArgumentException() {
        assertThrows(IllegalStateException.class, () -> vector.normalise(),
                "Can not normalise a vector of zero length.");
    }


    // Angle Method Tests

    @Test
    public void angleBetweenTwoNonZeroVectors() {
        vector.set(-2, 4, -3, 2);

        V otherVector = newVectorInstance().set(11, 4, -6, 7);

        float actual = vector.angle(otherVector);

        float length = vector.length();
        float otherLength = otherVector.length();
        float result = vector.dot(otherVector) / (length * otherLength);
        result = Math.max(-1, Math.min(1, result));

        float expected = (float) Math.acos(result);
        float precision = 0.000001f;

        assertEquals(expected, actual, precision);
    }

    @Test
    public void angleBetweenThisVectorAndAZeroVectorThrowsAnIllegalArgumentException() {
        vector.set(1, 2, -4, 8);

        V otherVector = newVectorInstance();

        assertThrows(IllegalArgumentException.class, () -> vector.angle(otherVector),
                "Can't find angle when one or both vectors have length of 0.");


    }

    @Test
    public void angleBetweenThisZeroVectorAndAnotherVectorThrowsAnIllegalArgumentException() {
        V otherVector = newVectorInstance().set(11, 5, -9, 0);

        assertThrows(IllegalArgumentException.class, () -> vector.angle(otherVector),
                "Can't find angle when one or both vectors have length of 0.");
    }

    @Test
    public void angleBetweenZeroVectorsThrowsAnIllegalArgumentException() {
        V otherVector = newVectorInstance();

        assertThrows(IllegalArgumentException.class, () -> vector.angle(otherVector),
                "Can't find angle when one or both vectors have length of 0.");
    }

    @Test
    public void angleBetweenVectorAndNullThrowsAnIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> vector.angle(null),
                "The input Vector cannot be null.");
    }
}
