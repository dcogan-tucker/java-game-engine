package org.clowdy.input;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Manages the keyboard key press callbacks.
 * 
 * Protected methods to get the callbacks for use in the Input Class.
 * 
 * Public static method to retrieve keyboard key press data.
 * 
 * @author Dominic Cogan-Tucker
 *
 */
public class Keyboard
{
	/**
	 * The keyboard key callback.
	 */
	private GLFWKeyCallback keyCallback;

	/**
	 * An array of booleans stating if the given key is being pressed.
	 */
	private static boolean[] keysPressed = new boolean[GLFW_KEY_LAST];

	/**
	 * Constructs a Keyboard Object, initialising the key callback.
	 */
	protected Keyboard()
	{
		keyCallback = new GLFWKeyCallback()
		{
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods)
			{
				keysPressed[key] = (action != GLFW_RELEASE);
			}
		};
	}

	/**
	 * Frees any resources from the keyboard key callback.
	 */
	protected void free()
	{
		keyCallback.free();
	}

	/**
	 * Returns the key callback.
	 * 
	 * @return
	 */
	protected GLFWKeyCallback getKeyCallback()
	{
		return keyCallback;
	}

	/**
	 * Returns true if the given key is currently being held down.
	 * 
	 * @param key The key to check.
	 * 
	 * @return True if the given key is currently being held down, false otherwise.
	 */
	public static boolean isPressed(int key)
	{
		return keysPressed[key];
	}
}
