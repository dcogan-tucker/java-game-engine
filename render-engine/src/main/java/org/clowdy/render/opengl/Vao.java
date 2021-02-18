package org.clowdy.render.opengl;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

/**
 * A Vertex Array Object is an OpenGL Object that stores all of the state needed
 * to supply vertex data. Storing the format of the vertex data as well as the
 * Vbos.
 * 
 * This class provides methods to bind and delete VAOs as well as store vertex
 * data in them. The user is also able to retrieve the id of the VAO as well as
 * the VBOs stored in it.
 * 
 * @author Dominic Cogan-Tucker
 *
 */
public final class Vao
{
	/**
	 * The id of the Vao as an int.
	 */
	private int id;

	/**
	 * The position, textureCoord and index buffer objects of the Vao.
	 */
	private Vbo pbo, tbo, ibo;

	/**
	 * Constructs a Vao, generating an id for it.
	 */
	private Vao()
	{
		id = glGenVertexArrays();
	}

	/**
	 * Bind this Vao for use.
	 */
	public void bind()
	{
		glBindVertexArray(id);
	}

	/**
	 * Stores the given position and indices data to this Vao.
	 * 
	 * @param positions The vertex position data.
	 * @param indices The indices data.
	 */
	public void storeData(float[] positions, int[] indices)
	{
		bind();
		pbo = Vbo.create(GL_ARRAY_BUFFER, positions);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		ibo = Vbo.create(GL_ELEMENT_ARRAY_BUFFER, indices);
	}

	/**
	 * Stores the given position, texture and indices data to this Vao
	 * 
	 * @param positions The vertex position data.
	 * @param textureCoords The vertex texture coords.
	 * @param indices The indices data
	 */
	public void storeData(float[] positions, float[] textureCoords, int[] indices)
	{
		storeData(positions, indices);
		tbo = Vbo.create(GL_ARRAY_BUFFER, textureCoords);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
	}

	/**
	 * Deletes this VAO and all the VBOs contained in it.
	 */
	public void delete()
	{
		Vbo.delete(pbo);
		Vbo.delete(ibo);
		Vbo.delete(tbo);
		glDeleteVertexArrays(id);
	}

	/**
	 * Returns the id of this Vao.
	 * 
	 * @return The id of this Vao.
	 */
	public int getID()
	{
		return id;
	}

	/**
	 * Returns the Position Buffer Object associated with this Vao.
	 * 
	 * @return The Position Buffer Object associated with this Vao.
	 */
	public Vbo getPbo()
	{
		return pbo;
	}

	/**
	 * Returns the indices data associated with this Vao.
	 * 
	 * @return The indices data associated with this Vao.
	 */
	public Vbo getIbo()
	{
		return ibo;
	}
}
