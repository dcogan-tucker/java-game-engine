package org.clowdy.maths.matrix;
/**
 * This class extends the abstract Matrix class creating a 3x3 Matrix. The
 * matrix contains 9 elements in a column major layout.
 * 
 * Matrix3x3 contains four constructors as requested by the Matrix interface,
 * along with set methods to set the value of the whole matrix at run time as
 * well as methods to set individual elements of the matrix.
 * 
 * @author Dominic Cogan-Tucker
 *
 */
public class Matrix3x3 extends AbstractMatrix<Matrix3x3>
{
	{
		/*
		 * Initialises a 3x3 matrix with data in an length 9 array in column major
		 * format.
		 * 0	3	6
		 * 1 	4 	7
		 * 2 	5 	8
		 */
		super.data = new float[9];
	}

	/**
	 * Constructs a 3x3 matrix where every element is 0.
	 */
	public Matrix3x3() {}

	/**
	 * Constructs a 3x3 matrix as a clone of a given existing Matrix3x3. Each element
	 * of this matrix is set as the corresponding element in the given matrix.
	 *
	 * @param matrix The Matrix3x3 to clone.
	 */
	public Matrix3x3(Matrix3x3 matrix)
	{
		set(matrix);
	}

	/**
	 * Constructs a 3x3 matrix where every elements value is set to the given float
	 * m.
	 *
	 * @param value The float value to set all the elements of the matrix to.
	 */
	public Matrix3x3(float value)
	{
		set(value);
	}

	/**
	 * Constructs a 3x3 matrix from the 9 given floats in row-major order (starting
	 * from top left, going left to right across each row).
	 * 
	 * @param m00 The top left element of the matrix.
	 * @param m01 The top middle element of the matrix.
	 * @param m02 The top right element of the matrix.
	 * @param m10 The middle left element of the matrix.
	 * @param m11 The centre element of the matrix.
	 * @param m12 The middle right element of the matrix.
	 * @param m20 The bottom left element of the matrix.
	 * @param m21 The bottom middle element of the matrix.
	 * @param m22 The bottom right element of the matrix.
	 */
	public Matrix3x3(float m00, float m01, float m02, float m10, float m11,
					 float m12, float m20, float m21, float m22)
	{
		set(m00, m01, m02, m10, m11, m12, m20, m21, m22);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalArgumentException When the input Matrix is null.
	 */
	@Override
	public Matrix3x3 set(Matrix3x3 matrix)
	{
		illegalArgumentCheck(matrix);
		return set(matrix.data[0], matrix.data[3], matrix.data[6], matrix.data[1], matrix.data[4],
				matrix.data[7], matrix.data[2], matrix.data[5], matrix.data[8]);
	}

	/**
	 * Set all the elements of this matrix to the given float value and then return
	 * this matrix.
	 * 
	 * @param value The float value to set all the elements of this matrix.
	 * 
	 * @return This Matrix3x3 with all elements set as the given float value.
	 */
	public Matrix3x3 set(float value)
	{
		return set(value, value, value, value, value, value, value, value, value);
	}

	/**
	 * Sets the elements of this Matrix3x3 from the 9 given floats in row-major order
	 * (starting from top left, going left to right across each row).
	 * 
	 * @param m00 The top left element of the matrix.
	 * @param m01 The top middle element of the matrix.
	 * @param m02 The top right element of the matrix.
	 * @param m10 The middle left element of the matrix.
	 * @param m11 The centre element of the matrix.
	 * @param m12 The middle right element of the matrix.
	 * @param m20 The bottom left element of the matrix.
	 * @param m21 The bottom middle element of the matrix.
	 * @param m22 The bottom right element of the matrix.
	 * 
	 * @return This Matrix3x3 with all the elements set as the respective given float.
	 */
	public Matrix3x3 set(float m00, float m01, float m02, float m10, float m11,
			float m12, float m20, float m21, float m22)
	{
		data[0] = m00;
		data[1] = m10;
		data[2] = m20;
		data[3] = m01;
		data[4] = m11;
		data[5] = m21;
		data[6] = m02;
		data[7] = m12;
		data[8] = m22;
		return this;
	}

	/**
	 * Calculates the value of the determinant of this matrix and returns it.
	 * 
	 * @return The determinant of this matrix as a float.
	 */
	@Override
	public float determinant()
	{
		return data[0] * (data[4] * data[8] - data[7] * data[5])
				- data[3] * (data[1] * data[8] - data[7] * data[2])
				+ data[6] * (data[1] * data[5] - data[4] * data[2]);
	}
	
	/**
	 * Set this matrix as its adjugate.
	 * 
	 * @return This matrix as its adjugate.
	 */
	@Override
	public Matrix3x3 adjugate()
	{
		transpose();
		float[] temp = new float[9];
		temp[0] = data[4]*data[8] - data[5]*data[7];
		temp[1] = -(data[3]*data[8] - data[5]*data[6]);
		temp[2] = data[3]*data[7] - data[4]*data[6];
		temp[3] = -(data[1]*data[8] - data[2]*data[7]);
		temp[4] = data[0]*data[8] - data[2]*data[6];
		temp[5] = -(data[0]*data[7] - data[1]*data[6]);
		temp[6] = data[1]*data[5] - data[2]*data[4];
		temp[7] = -(data[0]*data[5] - data[2]*data[3]);
		temp[8] = data[0]*data[4] - data[1]*data[3];
		data = temp;
		return this;
	}
	
	@Override
	public String toString()
	{
		return "(" + data[0] + ", " + data[3] + ", " + data[6] + ",\n" +
				data[1] + ", " + data[4] + ", " + data[7] + ",\n" +
				data[2] + ", " + data[5] + ", " + data[8] + ")";
	}

	/**
	 * Returns itself.
	 * 
	 * @return This Matrix3x3.
	 */
	@Override
	protected Matrix3x3 self()
	{
		return this;
	}
}
