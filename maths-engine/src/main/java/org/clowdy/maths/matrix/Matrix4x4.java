package org.clowdy.maths.matrix;

/**
 * This class extends the abstract Matrix class creating a 4x4 Matrix. The
 * matrix contains 16 elements in a column major layout.
 * 
 * Matrix4x4 contains four constructors as requested by the Matrix interface,
 * along with set methods to set the value of the whole matrix at run time as
 * well as methods to set individual elements of the matrix.
 * 
 * @author Dominic Cogan-Tucker
 *
 */
public class Matrix4x4 extends AbstractMatrix<Matrix4x4>
{
	{
		/*
		 * Initialise a 4x4 matrix data in an length 16 array in column major
		 * format.
		 * 0	4	8	12
		 * 1 	5 	9 	13
		 * 2 	6 	10	14
		 * 3 	7	11 	15
		 */
		super.data = new float[16];
	}

	/**
	 * Constructs a 4x4 matrix where every element is 0.
	 */
	public Matrix4x4() {}

	/**
	 * Constructs a 4x4 matrix where every elements value is set to the given float
	 * m.
	 *
	 * @param m The float value to set all the elements of the matrix to.
	 */
	public Matrix4x4(float m)
	{
		set(m);
	}

	/**
	 * Constructs a 4x4 matrix as a clone of a given existing Matrix3x3. Each element
	 * of this matrix is set as the corresponding element in the given matrix.
	 *
	 * @param m The Matrix3x3 to clone.
	 */
	public Matrix4x4(Matrix4x4 m)
	{
		set(m);
	}

	/**
	 * Constructs a 4x4 matrix from the 16 given floats in row-major order (starting
	 * from top left, going left to right across each row).
	 * 
	 * @param m00 The top left element of the matrix.
	 * @param m01 The top middle-left element of the matrix.
	 * @param m02 The top middle-right element of the matrix.
	 * @param m03 The top right element of the matrix.
	 * @param m10 The 2nd top left element of the matrix.
	 * @param m11 The 2nd top middle-left element of the matrix.
	 * @param m12 The 2nd top middle-right element of the matrix.
	 * @param m13 The 2nd top right element of the matrix.
	 * @param m20 The 2nd bottom left element of the matrix.
	 * @param m21 The 2nd bottom middle-left element of the matrix.
	 * @param m22 The 2nd bottom middle-right element of the matrix.
	 * @param m23 The 2nd bottom right element of the matrix.
	 * @param m30 The bottom left element of the matrix.
	 * @param m31 The bottom middle-left element of the matrix.
	 * @param m32 The bottom middle-right element of the matrix.
	 * @param m33 The bottom right element of the matrix.
	 */
	public Matrix4x4(float m00, float m01, float m02, float m03, float m10,
					 float m11, float m12, float m13, float m20, float m21, float m22,
					 float m23, float m30, float m31, float m32, float m33)
	{
		set(m00, m01, m02, m03,
			m10, m11, m12, m13,
			m20, m21, m22, m23,
			m30, m31, m32, m33);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws IllegalArgumentException When the input Matrix is null.
	 */
	@Override
	public Matrix4x4 set(Matrix4x4 m)
	{
		illegalArgumentCheck(m);
		return set(m.data[0], m.data[4], m.data[8], m.data[12], m.data[1],
				m.data[5], m.data[9], m.data[13], m.data[2], m.data[6],
				m.data[10], m.data[14], m.data[3], m.data[7], m.data[11],
				m.data[15]);
	}

	@Override
	public Matrix4x4 set(float m)
	{
		return set(m, m, m, m, m, m, m, m, m, m, m, m, m, m, m, m);
	}

	/**
	 * Sets the elements of this Matrix4x4 from the 16 given floats in row-major order
	 * (starting from top left, going left to right across each row).
	 * 
	 * @param m00 The top left element of the matrix.
	 * @param m01 The top middle-left element of the matrix.
	 * @param m02 The top middle-right element of the matrix.
	 * @param m03 The top right element of the matrix.
	 * @param m10 The 2nd top left element of the matrix.
	 * @param m11 The 2nd top middle-left element of the matrix.
	 * @param m12 The 2nd top middle-right element of the matrix.
	 * @param m13 The 2nd top right element of the matrix.
	 * @param m20 The 2nd bottom left element of the matrix.
	 * @param m21 The 2nd bottom middle-left element of the matrix.
	 * @param m22 The 2nd bottom middle-right element of the matrix.
	 * @param m23 The 2nd bottom right element of the matrix.
	 * @param m30 The bottom left element of the matrix.
	 * @param m31 The bottom middle-left element of the matrix.
	 * @param m32 The bottom middle-right element of the matrix.
	 * @param m33 The bottom right element of the matrix.
	 */
	public Matrix4x4 set(float m00, float m01, float m02, float m03, float m10,
			float m11, float m12, float m13, float m20, float m21, float m22,
			float m23, float m30, float m31, float m32, float m33)
	{
		data[0] = m00;
		data[1] = m10;
		data[2] = m20;
		data[3] = m30;
		data[4] = m01;
		data[5] = m11;
		data[6] = m21;
		data[7] = m31;
		data[8] = m02;
		data[9] = m12;
		data[10] = m22;
		data[11] = m32;
		data[12] = m03;
		data[13] = m13;
		data[14] = m23;
		data[15] = m33;
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
		return data[0]
				* new Matrix3x3(data[5], data[9], data[13], data[6], data[10],
						data[14], data[7], data[11], data[15]).determinant()
				- data[4] * new Matrix3x3(data[1], data[9], data[13], data[2],
						data[10], data[14], data[3], data[11], data[15])
								.determinant()
				+ data[8] * new Matrix3x3(data[1], data[5], data[13], data[2],
						data[6], data[14], data[3], data[7], data[15])
								.determinant()
				- data[12] * new Matrix3x3(data[1], data[5], data[9], data[2],
						data[6], data[10], data[3], data[7], data[11])
								.determinant();
	}
	
	/**
	 * Set this matrix as its adjugate.
	 * 
	 * 0 4 8  12
	 * 1 5 9  13
	 * 2 6 10 14
	 * 3 7 11 15
	 * 
	 * @return This matrix as its adjugate .
	 */
	@Override
	public Matrix4x4 adjugate()
	{
		transpose();
		float[] temp = new float[16];
		temp[0] = new Matrix3x3(data[5], data[9], data[13], data[6], data[10], data[14], data[7], data[11], data[15]).determinant();
		temp[1] = - new Matrix3x3(data[4], data[8], data[12], data[6], data[10], data[14], data[7], data[11], data[15]).determinant();
		temp[2] = new Matrix3x3(data[4], data[8], data[12], data[5], data[9], data[13], data[7], data[11], data[15]).determinant();
		temp[3] = - new Matrix3x3(data[4], data[8], data[12], data[5], data[9], data[13], data[6], data[10], data[14]).determinant();
		temp[4] = - new Matrix3x3(data[1], data[9], data[13], data[2], data[10], data[14], data[3], data[11], data[15]).determinant();
		temp[5] = new Matrix3x3(data[0], data[8], data[12], data[2], data[10], data[14], data[3], data[11], data[15]).determinant();
		temp[6] = - new Matrix3x3(data[0], data[8], data[12], data[1], data[9], data[13], data[3], data[11], data[15]).determinant();
		temp[7] = new Matrix3x3(data[0], data[8], data[12], data[1], data[9], data[13], data[2], data[10], data[14]).determinant();
		temp[8] = new Matrix3x3(data[1], data[5], data[13], data[2], data[6], data[14], data[3], data[7], data[15]).determinant();
		temp[9] = - new Matrix3x3(data[0], data[4], data[12], data[2], data[6], data[14], data[3], data[7], data[15]).determinant();
		temp[10] = new Matrix3x3(data[0], data[4], data[12], data[1], data[5], data[13], data[3], data[7], data[15]).determinant();
		temp[11] = - new Matrix3x3(data[0], data[4], data[12], data[1], data[5], data[13], data[2], data[6], data[14]).determinant();
		temp[12] = - new Matrix3x3(data[1], data[5], data[9], data[2], data[6], data[10], data[3], data[7], data[11]).determinant();
		temp[13] = new Matrix3x3(data[0], data[4], data[8], data[2], data[6], data[10], data[3], data[7], data[11]).determinant();
		temp[14] = - new Matrix3x3(data[0], data[4], data[8], data[1], data[5], data[9], data[3], data[7], data[11]).determinant();
		temp[15] = new Matrix3x3(data[0], data[4], data[8], data[1], data[5], data[9], data[2], data[6], data[10]).determinant();
		data = temp;
		return this;
	}

	/**
	 * Returns itself.
	 * 
	 * @return This Matrix3x3.
	 */
	@Override
	public Matrix4x4 self()
	{
		return this;
	}

}
