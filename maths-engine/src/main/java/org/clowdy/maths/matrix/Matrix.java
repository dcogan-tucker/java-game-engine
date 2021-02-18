package org.clowdy.maths.matrix;

/**
 * <p>This is the root interface to the Matrix hierarchy. A Matrix is a rectangular array
 * of values for which mathematical operations such as addition and multiplication are
 * defined. The size of a matrix is defined by the number of rows and columns it contains.</p>
 *
 * <p>This interface provides default implementation for methods that can be defined through
 * calling other existing methods within the interface.</p>
 * 
 * <p>Implementing classes of Matrix should represent n-by-n matrices, such that the
 * number of rows and columns are equal. They should also provide four constructors: a
 * default (no arguments) constructor; a constructor that takes a single float argument,
 * setting all elements to it; a clone constructor that takes a instance of the
 * implementing class; and finally a constructor that takes n^2 floats, one for each
 * element in the matrix. The implementation should also contain a set method that takes
 * in n^2 float in the same fashion as the constructor.</p>
 * 
 * <p>The Matrix operations, where appropriate, modify the instance of the Matrix who is
 * calling the function and then returns it. This approach allows for chaining
 * operations, providing clearer and easier to follow code. No method implementation
 * should ever modify any input values.</p>
 *
 * </p>The abstract methods set out in this interface should all be implemented following
 * the method guidelines.</p>
 *
 * @param <M> A type that extends Matrix. In practice should be the class the of the
 *           implementation.
 *
 * @author Dominic Cogan-Tucker
 */
public interface Matrix<M extends Matrix<M>>
{
	/**
	 * Returns this Matrix after setting it as a clone of the give Matrix.
	 *
	 * @param matrix The Matrix to clone.
	 *
	 * @return This Matrix.
	 */
	M set(M matrix);

	/**
	 * Returns this Matrix after setting every element equal to the given float.
	 *
	 * @param value The value to set all elements.
	 *
	 * @return This Vector.
	 */
	M set(float value);

	/**
	 * Return this Matrix after adding the given Matrix m to it.
	 * 
	 * @param matrix The Matrix to add.
	 * 
	 * @return This Matrix.
	 */
	M add(M matrix);

	/**
	 * Return this Matrix after subtracting the given Matrix m from this one.
	 * 
	 * @param matrix The Matrix to subtract.
	 * 
	 * @return m This Matrix.
	 */
	M subtract(M matrix);

	/**
	 * Return this Matrix after multiplying it by the given Matrix.
	 * 
	 * @param matrix The Matrix to multiply by.
	 * 
	 * @return This Matrix.
	 */
	M multiply(M matrix);

	/**
	 * Return this Matrix after multiplying it by the given scalar.
	 *
	 * @param scalar The scalar value to multiply by.
	 * 
	 * @return This Matrix.
	 */
	M multiply(float scalar);

	/**
	 * Return this Matrix after setting its elements so it equals as an identity matrix.
	 *
	 * @return This Matrix.
	 */
	M identity();

	/**
	 * Return this Matrix after setting it as its transpose.
	 *
	 * @return This Matrix.
	 */
	M transpose();

	/**
	 * Returns the determinant of this Matrix.
	 *
	 * @return The determinant of this Matrix.
	 */
	float determinant();

	/**
	 * Return this Matrix after setting it as its adjugate of this Matrix.
	 *
	 * @return This Matrix.
	 */
	M adjugate();

	/**
	 * Return this Matrix after dividing it by the given scalar.
	 * 
	 * @param scalar The scalar value to divide by.
	 * @throws IllegalArgumentException When the given scalar is 0.
	 * 
	 * @return This Matrix.
	 */
	default M divide(float scalar)
	{
		if (scalar == 0)
			throw new IllegalArgumentException("Can't divide by 0.");
		return multiply(1 / scalar);
	}

	/**
	 * Returns this Matrix after setting it as its inverse.
	 *
	 * @throws IllegalStateException When this Matrix doesn't have an inverse.
	 * 
	 * @return This Matrix.
	 */
	default M inverse()
	{
		float det = determinant();
		if (det == 0)
		{
			throw new IllegalStateException("This matrix does not have an inverse.");
		}
		return adjugate().divide(det);
	}
}
