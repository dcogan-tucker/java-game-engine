package org.clowdy.maths.matrix;

import java.util.Arrays;

/**
 * <p>An abstract Matrix class that implements the Matrix interface as well as providing
 * a data field as an array of elements. The methods for equals and hashCode have also
 * been overridden.</p>
 *
 * <p>This class provides implementation for the Matrix Interface methods where possible
 * that can rely on the existence of the element array.</p>
 *
 * <p>Classes extending this class should follow the guideline given by the Matrix
 * Interface.</p>
 * 
 * @author Dominic Cogan-Tucker
 *
 * @param <M> The type that extends Matrix, should be of the type of the
 * 			  implementation.
 */
public abstract class AbstractMatrix<M extends AbstractMatrix<M>> implements Matrix<M>
{
	/*
	 * An array to store the elements of the matrix. This arrays should be
	 * initialised by the extending class and should only be set to size n^2,
	 * for a matrix of n rows and columns.
	 */
	protected float[] data;

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalArgumentException When the input Matrix is null.
	 */
	@Override
	public M add(M matrix)
	{
		illegalArgumentCheck(matrix);
		for (int i = 0; i < data.length; i++)
		{
			data[i] += matrix.data[i];
		}
		return self();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalArgumentException When the input Matrix is null.
	 */
	@Override
	public M subtract(M matrix)
	{
		illegalArgumentCheck(matrix);
		for (int i = 0; i < data.length; i++)
		{
			data[i] -= matrix.data[i];
		}
		return self();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalArgumentException When the input Matrix is null.
	 */
	@Override
	public M multiply(M matrix)
	{
		illegalArgumentCheck(matrix);
		float[] temp = new float[data.length];
		int sqrt = (int) Math.sqrt(data.length);
		for (int i = 0; i < data.length; i++)
		{
			temp[i] = 0;
			for (int j = 0; j < sqrt; j++)
			{
				temp[i] += data[(i % sqrt) + (j * sqrt)] 
						* matrix.data[((i / sqrt) * sqrt) + j];
			}
		}
		data = temp;
		return self();
	}

	@Override
	public M multiply(float scalar)
	{
		for (int i = 0; i < data.length; i++)
		{
			data[i] *= scalar;
		}
		return self();
	}

	@Override
	public M identity()
	{
		for (int i = 0; i < data.length; i++)
		{
			data[i] = (i % (Math.sqrt(data.length) + 1)) == 0 ? 1 : 0;
		}
		return self();
	}

	@Override
	public M transpose()
	{
		float[] temp = new float[data.length];
		int sqr = (int) Math.sqrt(data.length);
		for (int i = 0; i < data.length; i++)
		{
			temp[i] = data[((i % sqr) * sqr) + (i / sqr)];
		}
		data = temp;
		return self();
	}

	/**
	 * Returns true if the given matrix is equal to this matrix, false if not.
	 *
	 * @param other The vector to check against.
	 *
	 * @return true if the given matrix is equal to this matrix, false if not.
	 */
	@Override
	public boolean equals(Object other)
	{
		Class<? extends AbstractMatrix> thisClass = this.getClass();
		if (thisClass.isInstance(other))
		{
			return Arrays.equals(data, thisClass.cast(other).data);
		}
		return false;
	}

	/**
	 * Returns a hash code for this matrix.
	 *
	 * @return A hash code for this matrix.
	 */
	@Override
	public int hashCode()
	{
		return Arrays.hashCode(data);
	}

	/*
	 * Auxiliary method used to check if the input matrix is equal to null, and if
	 * so throws an IllegalArgumentException.
	 */
	protected void illegalArgumentCheck(M matrix)
	{
		if (matrix == null)
		{
			throw new IllegalArgumentException("The input Matrix can not be null.");
		}
	}

	/**
	 * Should be implemented to return this instance of the calling type.
	 *
	 * Auxiliary method used in abstract class defined methods to return
	 * self.
	 *
	 * @return Itself.
	 */
	protected abstract M self();
}
