package org.clowdy.maths.vector;

import org.clowdy.util.ReflectionEqualsHelper;

/**
 * <p>An abstract Vector class that implements the Vector interface as well as providing
 * overridden methods for equals and hashCode.</p>
 *
 * <p>Classes extending this class should follow the guidelines given by the
 * Vector Interface.</p>
 *
 * @param <V> The type that extends Vector, should be of the type of the
 *            implementation.
 * @author Dominic Cogan-Tucker
 */
public abstract class AbstractVector<V extends AbstractVector<V>> implements Vector<V> {
    /**
     * {@inheritDoc}
     *
     * @throws IllegalArgumentException When the input Vector is null.
     */
    @Override
    public float angle(V vector) {
        illegalArgumentCheck(vector);
        return Vector.super.angle(vector);
    }

    /**
     * Returns true if the given vector is equal to this vector, false if not.
     *
     * @param vector The vector to check against.
     * @return true if the given vector is equal to this vector, false if not.
     */
    @Override
    public boolean equals(Object vector) {
        if (vector instanceof Vector) {
            return ReflectionEqualsHelper.areEquals(this, vector);
        }
        return false;
    }

    /**
     * Returns a hash code for this vector.
     *
     * @return A hash code for this vector.
     */
    @Override
    public int hashCode() {
        return ReflectionEqualsHelper.hashcode(this);
    }

    /*
     * Auxiliary method used to check if the input vector is equal to null, and if
     * so throws an IllegalArgumentException with a relevant message.
     */
    protected void illegalArgumentCheck(V vector) {
        if (vector == null) {
            throw new IllegalArgumentException("The input Vector cannot be null.");
        }
    }

    /*
     * Setter for making generalised test cases.
     */
    protected abstract V set(float... values);
}
