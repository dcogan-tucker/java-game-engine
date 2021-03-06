package org.clowdy.component;

import org.clowdy.component.Component.PoolType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class ComponentPool {
    private final UUID id;
    private final PoolType poolType;
    private final Map<Class<? extends Component>, Component> components = new HashMap<>();
    private int size;

    public ComponentPool(Component.PoolType poolType) {
        id = UUID.randomUUID();
        this.poolType = poolType;
    }

    public UUID getId() {
        return id;
    }

    public PoolType getPoolType() {
        return poolType;
    }

    public int getSize() {
        return size;
    }

    public void addComponent(Component component) {
        components.put(component.getClass(), component);
        size++;
    }

    public void removeComponent(Component component) {
        components.remove(component.getClass(), component);
        size--;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ComponentPool) {
            ComponentPool otherComponentPool = (ComponentPool) obj;
            return Objects.equals(components, otherComponentPool.components) &&
                    Objects.equals(poolType, otherComponentPool.poolType);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(components, poolType);
    }
}