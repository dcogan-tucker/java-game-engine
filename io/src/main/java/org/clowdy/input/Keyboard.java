package org.clowdy.input;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_LAST;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

/**
 * Manages the keyboard key press callbacks.
 * <p>
 * Protected methods to get the callbacks for use in the Input Class.
 * <p>
 * Public static method to retrieve keyboard key press data.
 *
 * @author Dominic Cogan-Tucker
 */
public class Keyboard {
    /**
     * An array of booleans stating if the given key is being pressed.
     */
    private static boolean[] keysPressed = new boolean[GLFW_KEY_LAST];
    /**
     * The keyboard key callback.
     */
    private GLFWKeyCallback keyCallback;

    /**
     * Constructs a Keyboard Object, initialising the key callback.
     */
    protected Keyboard() {
        keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keysPressed[key] = (action != GLFW_RELEASE);
            }
        };
    }

    /**
     * Returns true if the given key is currently being held down.
     *
     * @param key The key to check.
     * @return True if the given key is currently being held down, false otherwise.
     */
    public static boolean isPressed(int key) {
        return keysPressed[key];
    }

    /**
     * Frees any resources from the keyboard key callback.
     */
    protected void free() {
        keyCallback.free();
    }

    /**
     * Returns the key callback.
     *
     * @return The keyboard key callback.
     */
    protected GLFWKeyCallback getKeyCallback() {
        return keyCallback;
    }
}
