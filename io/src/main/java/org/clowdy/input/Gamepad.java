package org.clowdy.input;

import org.lwjgl.glfw.GLFWGamepadState;

import static org.lwjgl.glfw.GLFW.glfwGetGamepadState;

/**
 * Stores the id and state of the Gamepad and whether it is currently connected
 * to the device.
 * <p>
 * Protected methods to retrieve the Gamepad state and whether it is connected,
 * as well as methods to connect and disconnect the Gamepad. These methods are
 * acts as helper functions for the GamepadManager.
 *
 * @author Dominic Cogan-Tucker
 */
public class Gamepad {
    /**
     * The id of the Gamepad. This value should be between 0 - 15 to match the
     * maximum number of Gamepads that can be identified by GLFW.
     */
    private int id;

    /**
     * Boolean stating whether this Gamepad is currently connected.
     */
    private boolean connected = false;

    /**
     * The state of the Gamepad.
     */
    private GLFWGamepadState state;

    /**
     * Constructs a Gamepad with the given id. Protected to ensure only called with
     * the package by the GamepadManager.
     *
     * @param id The id number of the Gamepad.
     */
    protected Gamepad(int id) {
        this.id = id;
    }

    /**
     * Returns the GLFWGamepadState of this Gamepad.
     *
     * @return The GLFWGamepadState of this Gamepad.
     */
    protected GLFWGamepadState getGamepadState() {
        glfwGetGamepadState(id, state);
        return state;
    }

    /**
     * Returns true if this Gamepad is currently connected.
     *
     * @return Returns true if this Gamepad is currently connected, false if not.
     */
    protected boolean isConnected() {
        return connected;
    }

    /**
     * Sets the status of this Gamepad to connected.
     */
    protected void connect() {
        connected = true;
    }

    /**
     * Sets the status of this Gamepad to disconnected.
     */
    protected void disconnect() {
        connected = false;
    }
}