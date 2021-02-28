package org.clowdy.maths.vector;

import org.clowdy.util.StringFormatter;

/**
 * Extending the abstract Vector class, Vector2D represents a Vector in 2-dimensional
 * Euclidean Space. The vector contains an x and y component corresponding to the
 * magnitude in each axis respectively.
 * 
 * @author Dominic Cogan-Tucker
 *
 */
public class Vector2D extends AbstractVector<Vector2D>
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
	 * Construct a zero vector.
	 */
	public Vector2D() {}

	/**
	 * Construct a Vector2D that clones a given Vector2D.
	 *
	 * @param vector The Vector2D to clone.
	 */
	public Vector2D(Vector2D vector)
	{
		set(vector);
	}

	/**
	 * Constructs a Vector2D where both the x and y components are equal to the given
	 * value.
	 * 
	 * @param value The float value to set x and y.
	 */
	public Vector2D(float value)
	{
		set(value);
	}

	/**
	 * Constructs a Vector2D with the given x and y components.
	 * 
	 * @param x The value to set x.
	 * @param y The value to set y.
	 */
	public Vector2D(float x, float y)
	{
		set(x, y);
	}

	/**
	 * Return this Vector after setting the x and y components with the given values.
	 *
	 * @param x The value to set x.
	 * @param y The value to set y.
	 *
	 * @return This Vector.
	 */
	public Vector2D set(float x, float y)
	{
		this.x = x;
		this.y = y;
		return this;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalArgumentException When the input Vector is null.
	 */
	@Override
	public Vector2D set(Vector2D vector)
	{
		illegalArgumentCheck(vector);
		return set(vector.x, vector.y);
	}

	@Override
	public Vector2D set(float value)
	{
		return set(value, value);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalArgumentException When the input Vector is null.
	 */
	@Override
	public Vector2D add(Vector2D vector)
	{
		illegalArgumentCheck(vector);
		x += vector.x;
		y += vector.y;
		return this;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalArgumentException When the input Vector is null.
	 */
	@Override
	public Vector2D subtract(Vector2D vector)
	{
		illegalArgumentCheck(vector);
		x -= vector.x;
		y -= vector.y;
		return this;
	}

	@Override
	public Vector2D multiply(float scalar)
	{
		x *= scalar;
		y *= scalar;
		return this;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalArgumentException When the input Vector is null.
	 */
	@Override
	public float dot(Vector2D vector)
	{
		illegalArgumentCheck(vector);
		return x * vector.x + y * vector.y;
	}

	@Override
	public float length()
	{
		return (float) Math.sqrt(x * x + y * y);
	}

	/**
	 * Return the String form of this Vector2D. e.g. (1, 2).
	 *
	 * @return The String form of this Vector2D
	 */
	@Override
	public String toString()
	{
		String xString = StringFormatter.removeTrailingZero(x);
		String yString = StringFormatter.removeTrailingZero(y);
		return "(" + xString + ", " + yString + ")";
	}

	/*
	 * Setter for testing
	 */
	protected Vector2D set(float... values)
	{
		x = values.length > 1 ? values[0] : 0;
		y = values.length > 2 ? values[1] : 0;
		return this;
	}
}
