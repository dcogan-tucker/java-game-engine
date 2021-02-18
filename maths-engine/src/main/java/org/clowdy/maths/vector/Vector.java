package org.clowdy.maths.vector;

/**
 * <p>This is the root interface to the Vector hierarchy. A Vector represents a quantity
 * in a vector space with a direction an magnitude. Vectors in a vector space of
 * n-dimensions should contain n elements, one corresponding to each dimension.</p>
 *
 * <p>This interface provides default implementation for methods that can be defined through
 * calling other existing methods within the interface.</p>
 *
 * <p>All Vector implementation classes should provide four constructors: a default
 * (no arguments) constructor to create a zero vector; a constructor that takes a single
 * float argument, setting all coordinate values to it; a clone constructor that takes an
 * instance of the implementing class; and finally a constructor that takes in n-floats,
 * one for each coordinate contained in the n-dimensional vector. The implementation
 * should also contain an set method that takes in n-floats in the same fashion as the
 * constructor.</p>
 *
 * <p>The Vector operations, where appropriate, modify the instance of the Vector who is
 * calling the function and then returns it. This approach allows for chaining operations,
 * providing clearer and easier to follow code. No method implementation should ever modify
 * any input Vector.</p>
 *
 * </p>The abstract methods set out in this interface should all be implemented following
 * the method guidelines.</p>
 *
 * @param <V> A type that extends Vector. In practice this should be the class the of the
 *           implementation.
 *
 * @author Dominic Cogan-Tucker
 */
public interface Vector<V extends Vector<V>>
{
	/**
	 * Return this Vector after setting it as a clone of the given Vector.
	 *
	 * @param vector The vector to clone.
	 *
	 * @return This Vector.
	 */
	V set(V vector);

	/**
	 * Return this Vector after setting every element equal to the given float.
	 *
	 * @param value The value to set all elements.
	 *
	 * @return This Vector.
	 */
	V set(float value);

	/**
	 * Return this Vector after adding the given Vector v to it.
	 *
	 * @param vector The Vector to add.
	 *
	 * @return This Vector.
	 */
	V add(V vector);

	/**
	 * Return this Vector after subtracting the given Vector v from it.
	 *
	 * @param vector The Vector to subtract.
	 *
	 * @return v This Vector.
	 */
	V subtract(V vector);

	/**
	 * Return this Vector after multiply it by the given scalar.
	 *
	 * @param scalar The scalar value to multiply by.
	 *
	 * @return This Vector.
	 */
	V multiply(float scalar);

	/**
	 * Return the dot product of this Vector with the given Vector
	 *
	 * @param vector The Vector to dot with.
	 *
	 * @return The dot product of this Vector and the given Vector as a float.
	 */
	float dot(V vector);

	/**
	 * Returns the length of this Vector.
	 *
	 * @return The length of this vector as a float.
	 */
	float length();

	/**
	 * Return this Vector after negating it's direction.
	 *
	 * @return This Vector.
	 */
	default V negate()
	{
		return multiply(-1);
	}

	/**
	 * Return this Vector after dividing it by the given scalar.
	 *
	 * @param scalar The scalar to divide by.
	 * @throws IllegalArgumentException When the given scalar is 0.
	 *
	 * @return This Vector.
	 */
	default V divide(float scalar)
	{
		if (scalar == 0)
			throw new IllegalArgumentException("Can't divide a vector by 0.");
		return multiply(1f / scalar);
	}

	/**
	 * Return this Vector after it has been normalised.
	 *
	 * @throws IllegalStateException When this Vector has a length of 0.
	 *
	 * @return This Vector
	 */
	default V normalise()
	{
		float length = length();
		if (length == 0)
			throw new IllegalStateException("Can not normalise a vector of zero length.");
		return divide(length());
	}

	/**
	 * Return the angle between this Vector and the given Vector in degrees.
	 *
	 * @param vector The other Vector.
	 * @throws IllegalArgumentException When this Vector or the given Vector have a length of 0.
	 *
	 * @return The angel between this Vector and the given Vector as a float.
	 */
	default float angle(V vector)
	{
		float l1 = length();
		float l2 = vector.length();
		if (l1 == 0 || l2 == 0)
		{
			throw new IllegalArgumentException("Can't find angle when one or both vectors have length of 0.");
		}
		float result = dot(vector) / (l1 * l2);

		// Clamp value between -1 and 1.
		result = Math.max(-1, Math.min(1, result));

		return (float) Math.acos(result);
	}
}
