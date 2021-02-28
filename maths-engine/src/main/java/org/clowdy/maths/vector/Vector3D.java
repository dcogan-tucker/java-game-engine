package org.clowdy.maths.vector;

import org.clowdy.util.StringFormatter;

/**
 * Extending the abstract Vector class, Vector3D represents a Vector in 3-dimensional
 * Euclidean Space. The vector contains an x, y and z component corresponding to the
 * magnitude in axis respectively.
 * 
 * @author Dominic Cogan-Tucker
 *
 */
public class Vector3D extends AbstractVector<Vector3D>
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
	 * Constructs a zero vector.
	 */
	public Vector3D() {}

	/**
	 * Constructs a Vector3D that clones a given Vector3D.
	 *
	 * @param vector The Vector3D to clone.
	 */
	public Vector3D(Vector3D vector)
	{
		set(vector);
	}

	/**
	 * Constructs a Vector3D where the x, y and z components are equal to the given
	 * value.
	 * 
	 * @param value The float value to set x, y and z.
	 */
	public Vector3D(float value)
	{
		set(value);
	}

	/**
	 * Constructs a Vector3D with the given x, y and z components.
	 *
	 * @param x The x value to set x.
	 * @param y The y value to set y.
	 * @param z The z value to set z.
	 */
	public Vector3D(float x, float y, float z)
	{
		set(x, y, z);
	}

	/**
	 * Returns this Vector after setting the x, y and z components with the given
	 * values.
	 *
	 * @param x The value to set x.
	 * @param y The value to set y.
	 * @param z The value to set z.
	 *
	 * @return This Vector.
	 */
	public Vector3D set(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	@Override
	public Vector3D set(float value)
	{
		return set(value, value, value);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalArgumentException When the input Vector is null.
	 */
	@Override
	public Vector3D set(Vector3D vector)
	{
		illegalArgumentCheck(vector);
		return set(vector.x, vector.y, vector.z);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalArgumentException When the input Vector is null.
	 */
	@Override
	public Vector3D add(Vector3D vector)
	{
		illegalArgumentCheck(vector);
		x += vector.x;
		y += vector.y;
		z += vector.z;
		return this;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalArgumentException When the input Vector is null.
	 */
	@Override
	public Vector3D subtract(Vector3D vector)
	{
		illegalArgumentCheck(vector);
		x -= vector.x;
		y -= vector.y;
		z -= vector.z;
		return this;
	}

	@Override
	public Vector3D multiply(float scalar)
	{
		x *= scalar;
		y *= scalar;
		z *= scalar;
		return this;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalArgumentException When the input Vector is null.
	 */
	@Override
	public float dot(Vector3D vector)
	{
		illegalArgumentCheck(vector);
		return x * vector.x + y * vector.y + z * vector.z;
	}

	@Override
	public float length()
	{
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	/**
	 * Returns this vector after crossing it with a given Vector.
	 *
	 * @param vector The vector to cross with.
	 * @throws IllegalArgumentException When the input Vector is null.
	 *
	 * @return This Vector.
	 */
	public Vector3D cross(Vector3D vector)
	{
		illegalArgumentCheck(vector);
		Vector3D result = new Vector3D();
		result.x = y * vector.z - z * vector.y;
		result.y = z * vector.x - x * vector.z;
		result.z = x * vector.y - y * vector.x;
		return set(result);
	}

	/**
	 * Return the string form of this Vector3D e.g. (1, 2, 3).
	 *
	 * @return The String form of this Vector3D.
	 */
	@Override
	public String toString()
	{
		String xString = StringFormatter.removeTrailingZero(x);
		String yString = StringFormatter.removeTrailingZero(y);
		String zString = StringFormatter.removeTrailingZero(z);
		return "(" + xString + ", " + yString + ", " + zString + ")";
	}

	/*
	 * Setter for testing
	 */
	protected Vector3D set(float... values)
	{
		x = values.length > 1 ? values[0] : 0;
		y = values.length > 2 ? values[1] : 0;
		x = values.length > 3 ? values[2] : 0;
		return this;
	}
}
