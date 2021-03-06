package org.clowdy.input;

import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LAST;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

/**
 * Manages the mouse button, position, and scroll callbacks.
 * <p>
 * Protected methods to get the callbacks for use in the Input Class.
 * <p>
 * Public static methods to retrieve mouse button press, position and
 * scroll data.
 *
 * @author Dominic Cogan-Tucker
 */
public class Mouse {
    /**
     * An array of booleans stating if the given button is being pressed.
     */
    private static boolean[] buttonsPressed = new boolean[GLFW_MOUSE_BUTTON_LAST];
    /**
     * The axis coordinate position of the mouse.
     */
    private static double mouseX, mouseY;
    /**
     * The scroll amount along the axis.
     */
    private static double scrollX, scrollY;
    /**
     * The mouse button callback.
     */
    private GLFWMouseButtonCallback buttonCallback;
    /**
     * The mouse position callback.
     */
    private GLFWCursorPosCallback posCallback;
    /**
     * The mouse scroll callback.
     */
    private GLFWScrollCallback scrollCallback;

    /**
     * Constructs a Mouse object, initialising the button, position and scroll
     * callbacks.
     */
    protected Mouse() {
        mouseButtonCallback();
        cursorPositionCallback();
        scrollCallback();
    }

    /**
     * Returns true if the given button is currently being held down.
     *
     * @param button The button to check.
     * @return True if the given button is being held down, false otherwise.
     */
    public static boolean isPressed(int button) {
        return buttonsPressed[button];
    }

    /**
     * Returns the current x coordinate of the mouse's position.
     *
     * @return The current x coordinate of the mouse's position.
     */
    public static double getMousePosX() {
        return mouseX;
    }

    /**
     * Returns the current y coordinate of the mouse's position.
     *
     * @return the current y coordinate of the mouse's position.
     */
    public static double getMousePosY() {
        return mouseY;
    }

    /**
     * Returns the current scroll amount along the x axis.
     *
     * @return the current scroll amount along the x axis.
     */
    public static double getScrollX() {
        return scrollX;
    }

    /**
     * Returns the current scroll amount along the y axis.
     *
     * @return the current scroll amount along the y axis.
     */
    public static double getScrollY() {
        return scrollY;
    }

    /**
     * Creates the mouse button callback.
     */
    private void mouseButtonCallback() {
        buttonCallback = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                buttonsPressed[button] = (action != GLFW_RELEASE);
            }
        };
    }

    /**
     * Creates the mouse position callback.
     */
    private void cursorPositionCallback() {
        posCallback = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseX = xpos;
                mouseY = xpos;
            }
        };
    }

    /**
     * Creates the mouse scroll callback.
     */
    private void scrollCallback() {
        scrollCallback = new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double xoffset, double yoffset) {
                scrollX += xoffset;
                scrollY += yoffset;
            }
        };
    }

    /**
     * Frees any resources associated with all the callbacks contained within Mouse object.
     */
    protected void free() {
        buttonCallback.free();
        posCallback.free();
        scrollCallback.free();
    }

    /**
     * Returns the mouse button callback.
     *
     * @return The mouse button callback.
     */
    protected GLFWMouseButtonCallback getButtonCallback() {
        return buttonCallback;
    }

    /**
     * Returns the mouse position callback.
     *
     * @return The mouse position callback.
     */
    protected GLFWCursorPosCallback getPositionCallback() {
        return posCallback;
    }

    /**
     * Returns the mouse scroll callback.
     *
     * @return The mouse scroll callback.
     */
    protected GLFWScrollCallback getScrollCallback() {
        return scrollCallback;
    }
}
