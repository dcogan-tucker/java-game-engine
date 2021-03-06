package org.clowdy.component;

import org.clowdy.util.ReflectionEqualsHelper;

/**
 * <p>An abstract root for the Component hierarchy. Each Component part of this hierarchy can store state variables.
 * These state variables can be accessed by the specified system PoolTypes independently of the Entities they belong
 * to.</p>
 *
 * @author Dominic Cogan-Tucker
 */
public abstract class Component {
    // Pool type this Component belongs in.
    private final PoolType[] poolTypes;

    /**
     * Constructs a component with the PoolTypes returned by
     * the setPoolTypes method.
     */
    public Component() {
        poolTypes = setPoolTypes();
    }

    // Abstract method to be defined by extending classes to determine PoolTypes for component.
    protected abstract PoolType[] setPoolTypes();

    /**
     * Returns the PoolTypes of this Component.
     *
     * @return The PoolTypes of this Component as an array.
     */
    public PoolType[] getPoolTypes() {
        return poolTypes;
    }

    /**
     * Returns true if the given Object is equal to this Component. Two components are considered equal if they are
     * of the same class type and their states are equal.
     *
     * @param other The other Object to check this against.
     * @return true if the given Object is equal to this Component.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Component) {
            return ReflectionEqualsHelper.areEquals(this, other);
        }
        return false;
    }

    /**
     * Returns the hash code of this Component.
     * @return The hash code of this Component.
     */
    @Override
    public int hashCode() {
        return ReflectionEqualsHelper.hashcode(this);
    }

    /**
     * <p>The System PoolTypes that a Component can have.</p>
     *
     * @author Dominic Cogan-Tucker
     */
    public enum PoolType {
        RENDER,
        COLLISION,
        PHYSICS,
        TEST
    }
}
