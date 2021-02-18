package org.clowdy.input;

import org.lwjgl.glfw.*;

/**
 * Manages Mouse, Keyboard and Gamepad inputs and callbacks.

 * 
 * @author Dominic Cogan-Tucker
 *
 */
public class Input
{
	/**
	 * The keyboard connected to the device.
	 */
	private static Keyboard keyboard = new Keyboard();
	
	/**
	 * The mouse connected to the device.
	 */
	private static Mouse mouse = new Mouse();
	
	/**
	 * The GamepadManager for the program.
	 */
	private static GamepadManager gamepadManager = new GamepadManager();
	
	/**
	 * Returns the keyboard key callback.
	 * 
	 * @return The keyboard key callback.
	 */
	public static GLFWKeyCallback keyboardKeyCallback()
	{
		return keyboard.getKeyCallback();
	}
	
	/**
	 * Returns the mouse button callback.
	 * 
	 * @return The mouse button callback.
	 */
	public static GLFWMouseButtonCallback mouseButtonCallback()
	{
		return mouse.getButtonCallback();
	}
	
	/**
	 * Returns the cursor position callback.
	 * 
	 * @return The cursor position callback.
	 */
	public static GLFWCursorPosCallback cursorPositionCallback()
	{
		return mouse.getPositionCallback();
	}
	
	/**
	 * Returns the mouse scroll callback.
	 * 
	 * @return The mouse scroll callback.
	 */
	public static GLFWScrollCallback mouseScrollCallback()
	{
		return mouse.getScrollCallback();
	}
	
	/**
	 * Returns the gamepad connections callback.
	 * 
	 * @return The gamepad connections callback.
	 */
	public static GLFWJoystickCallback gamepadCallback()
	{
		return gamepadManager.getJoystickCallback();
	}
	
	/**
	 * Free the resources from all the input callbacks.
	 */
	public static void freeAllCallbacks()
	{
		mouse.free();
		keyboard.free();
		gamepadManager.free();
	}
}
