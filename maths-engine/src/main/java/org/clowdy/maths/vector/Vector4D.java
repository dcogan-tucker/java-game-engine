package org.clowdy.maths.vector;

import org.clowdy.StringFormatter;

/**
 * Extending the abstract Vector class, Vector4D represents a Vector in 4-dimensional
 * Euclidean Space. The vector contains an x, y, z and w component corresponding to
 * the magnitude in each axis respectively.
 *
 * @author Dominic Cogan-Tucker
 *
 */
public class Vector4D extends AbstractVector<Vector4D>
{
	/**
	 * The x component of this Vector.
	 */
	public float x;

	/**
	 * The y component of this Vector.
	 */
	public float y;

	/**
	 * The z component of this Vector.
	 */
	public float z;

	/**
	 * The w component of this Vector.
	 */
	public float w;

	/**
	 * Constructs a Zero Vector.
	 */
	public Vector4D() {}

	/**
	 * Constructs a Vector4D that clones a given Vector4D.
	 *
	 * @param vector The Vector4D to clone.
	 */
	public Vector4D(Vector4D vector)
	{
		set(vector);
	}

	/**
	 * Constructs a Vector4D where the x, y, z and w components are equal to the
	 * given value.
	 *
	 * @param value The value to set both x, y, z and w.
	 */
	public Vector4D(float value)
	{
		set(value);
	}

	/**
	 * Constructs a Vector3D with the given x, y, z and w components.
	 *
	 * @param x The x value of the Vector4D.
	 * @param y The y value of the Vector4D.
	 * @param z The z value of the Vector4D.
	 * @param w The w value of the Vector4D.
	 */
	public Vector4D(float x, float y, float z, float w)
	{
		set(x, y, z, w);
	}

	/**
	 * Returns this Vector after setting the x, y, z and w components with the given
	 * values.
	 *
	 * @param x The value to set x.
	 * @param y The value to set y.
	 * @param z The value to set z.
	 * @param w The value to set w.
	 *
	 * @return This Vector.
	 */
	public Vector4D set(float x, float y, float z, float w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		return this;
	}

	@Override
	public Vector4D set(float value)
	{
		return set(value, value, value, value);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalArgumentException When the input Vector is null.
	 */
	@Override
	public Vector4D set(Vector4D vector)
	{
		illegalArgumentCheck(vector);
		return set(vector.x, vector.y, vector.z, vector.w);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalArgumentException When the input Vector is null.
	 */
	@Override
	public Vector4D add(Vector4D vector)
	{
		illegalArgumentCheck(vector);
		x += vector.x;
		y += vector.y;
		z += vector.z;
		w += vector.w;
		return this;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalArgumentException When the input Vector is null.
	 */
	@Override
	public Vector4D subtract(Vector4D vector)
	{
		illegalArgumentCheck(vector);
		x -= vector.x;
		y -= vector.y;
		z -= vector.z;
		w -= vector.w;
		return this;
	}

	@Override
	public Vector4D multiply(float scalar)
	{
		x *= scalar;
		y *= scalar;
		z *= scalar;
		w *= scalar;
		return this;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalArgumentException When the input Vector is null.
	 */
	@Override
	public float dot(Vector4D vector)
	{
		illegalArgumentCheck(vector);
		return x * vector.x + y * vector.y + z * vector.z + w * vector.w;
	}

	@Override
	public float length()
	{
		return (float) Math.sqrt(x * x + y * y + z * z + w * w);
	}

	/**
	 * Return the String form of this Vector4D e.g. (1, 2, 3, 4).
	 *
	 * @return The String form of this Vector4D.
	 */
	@Override
	public String toString()
	{
		String xString = StringFormatter.removeTraillingZero(x);
		String yString = StringFormatter.removeTraillingZero(y);
		String zString = StringFormatter.removeTraillingZero(z);
		String wString = StringFormatter.removeTraillingZero(w);
		return "(" + xString + ", " + yString +
				", " + zString + ", " + wString + ")";
	}

	/*
	 * Setter for testing
	 */
	protected Vector4D set(float... values)
	{
		x = values.length > 1 ? values[0] : 0;
		y = values.length > 2 ? values[1] : 0;
		z = values.length > 3 ? values[2] : 0;
		w = values.length > 4 ? values[3] : 0;

		return this;
	}
}
