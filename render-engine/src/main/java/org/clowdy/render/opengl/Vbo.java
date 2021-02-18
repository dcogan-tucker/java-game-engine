package org.clowdy.render.opengl;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;

/**
 * A Vertex Buffer Object is a data structure in the form of an array used by
 * OpenGl. The data is stored in a buffer in the graphics memory.
 * 
 * This class provides static methods to create and delete VBOs through the
 * LWJGL OpenGL binding.
 * 
 * @author Dominic Cogan-Tucker
 *
 */
final class Vbo
{
	/**
	 * The id of the Vbo as an int.
	 */
	private int id;

	/**
	 * The type of Vbo as an int.
	 */
	private int type;

	/**
	 * Constructs a Vbo, generating an id and setting the type. Private to ensure
	 * the constructor is only called within the create method of this class.
	 * 
	 * @param type
	 */
	private Vbo(int type)
	{
		id = glGenBuffers();
		this.type = type;
	}

	/**
	 * Binds this Vbo for use.
	 */
	private void bind()
	{
		glBindBuffer(type, id);
	}

	/**
	 * Stores the given float array to the Vbo.
	 * 
	 * @param data The float array to store.
	 */
	private void storeData(float[] data)
	{
		FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
		buffer.put(data).flip();
		storeData(buffer);
	}

	/**
	 * Stores the given float buffer to the Vbo
	 * 
	 * @param buffer The float buffer to store.
	 */
	private void storeData(FloatBuffer buffer)
	{
		glBufferData(type, buffer, GL_STATIC_DRAW);
	}

	/**
	 * Stores the given int array to the Vbo.
	 * 
	 * @param data The int array to store
	 */
	private void storeData(int[] data)
	{
		IntBuffer buffer = MemoryUtil.memAllocInt(data.length);
		buffer.put(data).flip();
		storeData(buffer);
	}

	/**
	 * Stores the given int buffer to the Vbo.
	 * 
	 * @param buffer The int buffer to store.
	 */
	private void storeData(IntBuffer buffer)
	{
		glBufferData(type, buffer, GL_STATIC_DRAW);
	}

	/**
	 * Creates a new Vbo of a given type containing the given data.
	 * 
	 * @param type The type of Vbo.
	 * @param data The data to be stored in the vbo.
	 * 
	 * @return The generated Vbo.
	 */
	protected static Vbo create(int type, float[] data)
	{
		Vbo vbo = new Vbo(type);
		vbo.bind();
		vbo.storeData(data);
		return vbo;
	}

	/**
	 * Creates a new Vbo of a given type containing the given data.
	 * 
	 * @param type The type of Vbo.
	 * @param data The data to be stored in the vbo.
	 * 
	 * @return The generated Vbo.
	 */
	protected static Vbo create(int type, int[] data)
	{
		Vbo vbo = new Vbo(type);
		vbo.bind();
		vbo.storeData(data);
		return vbo;
	}

	/**
	 * Deletes the given Vbo.
	 * 
	 * @param vbo The Vbo to delete.
	 */
	protected static void delete(Vbo vbo)
	{
		glDeleteBuffers(vbo.id);
	}
}