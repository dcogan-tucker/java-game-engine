package org.clowdy.entity;

import org.clowdy.component.Component;
import org.clowdy.component.Component.PoolType;

import javax.inject.Singleton;
import java.util.*;

/**
 * <p>An entity is an object that exists with given components that define its behaviour. Each instance
 * of an Entity created is assigned a unique id.</p>
 *
 * <p>A given Entity can only contain a single Component of each type. The components are stored in a
 * map structure to allow for easy retrieval of a Component of a given type. Each entity also stores
 * ComponentPools for each PoolType corresponding to the systems that monitor the entities components.</p>
 *
 * <p>Entities should be created through the EntityBuilder class to allow for chain calling the addition
 * of components..</p>
 *
 * @author Dominic Cogan-Tucker
 */
public class Entity {
    // Entity unique identifier.
    private final UUID id;
    // Map limits entity to have one instance of each component class.
    private final Map<Class<? extends Component>, Component> components = new HashMap<>();
    // Map of Component pools.
    private final Map<PoolType, ComponentPool> componentPools = new HashMap<>();
    // Manages all system components.
    private final ComponentManager componentManager;


    // Protected to ensure only EntityBuilder is used to create entities.
    protected Entity(ComponentManager componentManager) {
        id = UUID.randomUUID();
        this.componentManager = componentManager;
    }

    /**
     * Returns the UUID of this Entity.
     *
     * @return The UUID of this Entity.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Returns true if the given component is successfully added to the entity. A component is only
     * added if it is not null and an instance of the given component class doesn't belong to the
     * entity.
     *
     * @param component The component to add.
     * @return true if the given component is successfully added to the entity.
     */
    public boolean addComponent(Component component) {
        Component previous;
        if (component != null) {
            previous = components.putIfAbsent(component.getClass(), component);
            if (previous == null) {
                PoolType[] poolTypes = component.getPoolTypes();
                ComponentPool pool;
                for (PoolType poolType : poolTypes) {
                    pool = componentPools.get(poolType);
                    if (pool == null) {
                        pool = new ComponentPool(poolType);
                        componentPools.put(poolType, pool);
                        componentManager.put(pool);
                    }
                    pool.addComponent(component);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the Entity contains an instance of a Component of the given Component Class.
     *
     * @param componentClass The Component Class to check.
     * @return true if the Entity contains an instance of a Component of the given Component Class.
     */
    public boolean hasComponent(Class<? extends Component> componentClass) {
        return components.containsKey(componentClass);
    }

    /**
     * Returns true if the Entity contains an instance of a Component equal to the given Component.
     *
     * @param component The Component to check.
     * @return true if the Entity contains an instance of a Component equal to the given Component.
     */
    public boolean hasComponent(Component component) {
        return components.containsValue(component);
    }

    /**
     * Returns the instance of the given Component Class that belongs to this Entity if it has one,
     * otherwise returns null.
     *
     * @param componentClass The Component Class of the Component to get.
     * @return The instance of the given Component Class that belongs to this Entity if it has one,
     * otherwise null.
     */
    public Component getComponent(Class<? extends Component> componentClass) {
        return components.get(componentClass);
    }

    /**
     * Returns the ComponentPool of the given type held by this Entity. If there is no pool of the given
     * type null is returned.
     *
     * @param poolType The type of ComponentPool.
     * @return The ComponentPool of the given type, null if there is no pool of that type.
     */
    public ComponentPool getComponentPool(PoolType poolType) {
        return componentPools.get(poolType);
    }

    /**
     * Returns a List of all the Component instances belonging to this Entity.
     *
     * @return A List of all the Component instances belonging to this Entity.
     */
    public List<Component> getAllComponents() {
        return new ArrayList<>(components.values());
    }

    /**
     * Returns a List of all the ComponentPool instances belonging to this Entity.
     *
     * @return A List of all the ComponentPool instances belonging to this Entity.
     */
    public List<ComponentPool> getAllComponentPools() {
        return new ArrayList<>(componentPools.values());
    }

    /**
     * Returns the instance of the Component removed, or null if no Component is removed.
     * The Component removed will be of the given Component Class, if no Component of the
     * given Class exists no Component will be removed.
     *
     * @param componentClass The Component Class of the Component to remove.
     * @return The instance of the Component removed, or null if no Component is removed.
     */
    public Component removeComponent(Class<? extends Component> componentClass) {
        Component currentComponent = components.remove(componentClass);
        if (currentComponent != null) {
            PoolType[] poolTypes = currentComponent.getPoolTypes();
            ComponentPool pool;
            for (PoolType poolType : poolTypes) {
                pool = componentPools.get(poolType);
                if (pool != null) {
                    pool.removeComponent(currentComponent);
                    if (pool.getSize() == 0) {
                        componentPools.remove(poolType);
                        componentManager.remove(pool);
                    }
                }
            }
        }
        return currentComponent;
    }

    /**
     * Removes all components contain in this Entity.
     */
    public void clear() {
        components.clear();
        componentPools.values()
                .forEach(componentManager::remove);
        componentPools.clear();
    }

    /**
     * Returns true if the given Object is an instance of Entity and the Components it contains
     * are equal to the Components of this Entity.
     *
     * @param object The Object to check this is equals with.
     * @return true if the given Object is equal to this Entity, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Entity) {
            Entity otherEntity = (Entity) object;
            return Objects.equals(components, otherEntity.components);
        }
        return false;
    }

    /**
     * Returns the hashcode for this Entity.
     *
     * @return The hashcode for this Entity.
     */
    @Override
    public int hashCode() {
        return Objects.hash(components);
    }

    /**
     *
     */
    public static class ComponentPool {
        // Unique id.
        private final UUID id;
        // PoolType for this ComponentPool.
        private final PoolType poolType;
        // Map of components.
        private final Map<Class<? extends Component>, Component> components = new HashMap<>();
        // Number of components in pool.
        private int size;

        /**
         * Constructs a ComponentPool with the given PoolType.
         *
         * @param poolType The PoolType for this ComponentPool.
         */
        protected ComponentPool(Component.PoolType poolType) {
            id = UUID.randomUUID();
            this.poolType = poolType;
        }

        /**
         * Returns the UUID for this ComponentPool.
         *
         * @return This ComponentPool's UUID.
         */
        public UUID getId() {
            return id;
        }

        /**
         * Returns the type of this ComponentPool.
         *
         * @return The PoolType this ComponentPool is associated with.
         */
        public PoolType getPoolType() {
            return poolType;
        }

        /**
         * Returns the number of Components in this ComponentPool.
         *
         * @return The number of Components in this ComponentPool.
         */
        public int getSize() {
            return size;
        }

        // Add Component to the ComponentPool.
        protected void addComponent(Component component) {
            components.put(component.getClass(), component);
            size++;
        }

        // Remove Component from the ComponentPool.
        protected void removeComponent(Component component) {
            components.remove(component.getClass(), component);
            size--;
        }

        /**
         * Returns the Component of the given Class if it exists within the pool, otherwise returns
         * null.
         *
         * @param componentClass The Component Class to get.
         * @return The Component of the given Class inside the pool, null if one doesn't exist.
         */
        public Component getComponent(Class<? extends Component> componentClass) {
            return components.get(componentClass);
        }

        /**
         * Returns true if the given Object is an instance of Component Pool that contains equal components
         * and is of the same PoolType, false otherwise.
         *
         * @param other The other Object to check against.
         * @return true if the given Object is a ComponentPool of the same PoolType and contains equal components.
         */
        @Override
        public boolean equals(Object other) {
            if (other instanceof ComponentPool) {
                ComponentPool otherComponentPool = (ComponentPool) other;
                return Objects.equals(components, otherComponentPool.components) &&
                        Objects.equals(poolType, otherComponentPool.poolType);
            }
            return false;
        }

        /**
         * Returns the hash code for this ComponentPool.
         *
         * @return the hash code for this ComponentPool.
         */
        @Override
        public int hashCode() {
            return Objects.hash(components, poolType);
        }
    }

    /**
     * <p>A singleton Class that manages the storage of all ComponentPools of all entities. The content
     * of the class is automatically updated when entities are created, modified and deleted. The obtainable
     * map of a given PoolType is unmodifiable, any attempt to modify the map directly or via its
     * iterator will result in an UnsupportedOperationException.
     *
     * @author Dominic Cogan-Tucker
     */
    @Singleton
    public static class ComponentManager {
        // Map of unmodifiable maps of ComponentPools for each PoolType.
        private final Map<PoolType, Map<UUID, ComponentPool>> componentPools = new HashMap<>();
        // Underlying mutable collection.
        private final Map<PoolType, Map<UUID, ComponentPool>> mutableMaps = new HashMap<>();

        // Puts the given ComponentPool into the map.
        protected void put(ComponentPool componentPool) {
            PoolType poolType = componentPool.getPoolType();
            Map<UUID, ComponentPool> mutableMap = mutableMaps.get(poolType);
            // check if mutable map for pool type already exists
            if (mutableMap == null) {
                // initialise mutable map.
                mutableMap = new HashMap<>();
                // put into underlying mutable map.
                mutableMaps.put(poolType, mutableMap);
                // put unmodifiable version into componentPools.
                componentPools.put(poolType, Collections.unmodifiableMap(mutableMap));
            }
            // add component pool to underlying mutable map.
            mutableMap.put(componentPool.getId(), componentPool);
        }

        // Removed the given ComponentPool from the map.
        protected void remove(ComponentPool componentPool) {
            PoolType poolType = componentPool.getPoolType();
            Map<UUID, ComponentPool> mutableMap = mutableMaps.get(poolType);
            mutableMap.remove(componentPool.getId(), componentPool);
        }

        /**
         * Returns a Map of all ComponentPools of the given type. This map is unmodifiable, any attempt
         * to modify the returned map directly or via its iterator will result in an
         * UnsupportedOperationException.
         *
         * @param poolType The type of pool.
         * @return A Map of all ComponentPools of the given type.
         */
        public Map<UUID, ComponentPool> getComponentPoolType(PoolType poolType) {
            return componentPools.get(poolType);
        }
    }
}
