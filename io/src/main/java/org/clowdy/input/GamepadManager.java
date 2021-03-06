package org.clowdy.input;

import org.lwjgl.glfw.GLFWGamepadState;
import org.lwjgl.glfw.GLFWJoystickCallback;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Manages the Gamepad connection callbacks as well as the Gamepad states of up
 * to 16 devices.
 * <p>
 * Protected methods to get the Gamepad connection callbacks for use in the
 * Input Class.
 * <p>
 * Public static methods to check if a Gamepad is connected and retrieve the
 * GLFWGamepadState, and given button and axis states.
 *
 * @author Dominic Cogan-Tucker
 */
public class GamepadManager {
    /**
     * The Gamepad connection callback.
     */
    private GLFWJoystickCallback gamepadCallback;

    /**
     * An array of Gamepads of size 16 (maximum number of connected Gamepads).
     */
    protected static Gamepad[] gamepads = new Gamepad[GLFW_JOYSTICK_LAST];

    /**
     * Constructs a GamepadManager, initialising a disconnected Gamepad for each
     * element in the gamepads array and initialising the Gamepad connection
     * callback.
     */
    protected GamepadManager() {
        for (int i = GLFW_JOYSTICK_1; i < GLFW_JOYSTICK_LAST; i++) {
            gamepads[i] = new Gamepad(i);
        }

        gamepadCallback = new GLFWJoystickCallback() {
            @Override
            public void invoke(int jid, int event) {
                if (event == GLFW_CONNECTED && glfwJoystickIsGamepad(jid)) {
                    gamepads[jid].connect();
                } else if (event == GLFW_DISCONNECTED || !glfwJoystickIsGamepad(jid)) {
                    gamepads[jid].disconnect();
                }
            }
        };
    }

    /**
     * Returns the Gamepad connection callback.
     *
     * @return The Gamepad connection callback.
     */
    protected GLFWJoystickCallback getJoystickCallback() {
        return gamepadCallback;
    }

    /**
     * Frees the resources from the Gamepad connection callback.
     */
    protected void free() {
        gamepadCallback.free();
    }

    /**
     * Returns true if the Gamepad with the given id is currently connected.
     *
     * @param id The id of Gamepad to check.
     * @return true if a Gamepad with the given id is connected, otherwise false.
     */
    public static boolean isConnected(int id) {
        return gamepads[id].isConnected();
    }

    /**
     * Returns the GLFWGamepadState of the given Gamepad with the given id. Returns
     * null if no Gamepad exists with that id.
     *
     * @param id The id of the Gamepad to check.
     * @return The GLFWGamepadState of the given Gamepad with the given id. Returns
     * null if no Gamepad exists with that id.
     */
    public static GLFWGamepadState getGamepadState(int id) {
        return gamepads[id].getGamepadState();
    }

    /**
     * Returns true if the given Gamepad has the given button currently pressed.
     *
     * @param id     The id of the Gamepad to check.
     * @param button The button to check.
     * @return true if the given Gamepad has the given button currently pressed,
     * false if it is not.
     */
    public static boolean isPressed(int id, int button) {
        return getGamepadState(id).buttons(button) != GLFW_RELEASE;
    }

    /**
     * Returns the float value between -1.0 and 1.0, representing the position of
     * the given axis on the given Gamepad.
     *
     * @param id   The id of the Gamepad to check.
     * @param axis The axis to check.
     * @return The value of the given axis on the given Gamepad.
     */
    public static float axisState(int id, int axis) {
        return getGamepadState(id).axes(axis);
    }
}
