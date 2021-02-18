package org.clowdy.entity;

import org.clowdy.entity.component.Component;

import java.util.*;

/**
 *
 */
public class Entity
{
	// Entity unique identifier.
	private final UUID ID;
	// Map limits entity to have one instance of each component class.
	private final Map<Class<? extends Component>, Component> COMPONENTS = new HashMap<>();

	// Protected to ensure only EntityBuilder is used to create entities.
	protected Entity()
	{
		ID = UUID.randomUUID();
	}

	/**
	 * Returns the UUID of this Entity.
	 *
	 * @return The UUID of this Entity.
	 */
	public UUID getId()
	{
		return ID;
	}

	/**
	 * Returns true if the given component is successfully added to the entity. A component is only
	 * added if it is not null and an instance of the given component class doesn't belong to the
	 * entity.
	 *
	 * @param component The component to add.
	 * @return true if the given component is successfully added to the entity.
	 */
	public boolean addComponent(Component component)
	{
		Component previous;
		if (component != null)
		{
			previous = COMPONENTS.putIfAbsent(component.getClass(), component);
			return previous == null;
		}
		return false;
	}

	/**
	 * Returns true if the Entity contains an instance of a Component of the given Component Class.
	 *
	 * @param componentClass The Component Class to check.
	 * @return true if the Entity contains an instance of a Component of the given Component Class.
	 */
	public boolean hasComponent(Class<? extends Component> componentClass)
	{
		return COMPONENTS.containsKey(componentClass);
	}

	/**
	 * Returns true if the Entity contains an instance of a Component equal to the given Component.
	 *
	 * @param component The Component to check.
	 * @return true if the Entity contains an instance of a Component equal to the given Component.
	 */
	public boolean hasComponent(Component component)
	{
		return COMPONENTS.containsValue(component);
	}

	/**
	 * Returns the instance of the given Component Class that belongs to this Entity if it has one,
	 * otherwise returns null.
	 *
	 * @param componentClass The Component Class of the Component to get.
	 * @return The instance of the given Component Class that belongs to this Entity if it has one,
	 * otherwise null.
	 */
	public Component getComponent(Class<? extends Component> componentClass)
	{
		return COMPONENTS.get(componentClass);
	}

	/**
	 * Returns a List of all the Component instances belonging to this Entity.
	 *
	 * @return A List of all the Component instances belonging to this Entity.
	 */
	public List<Component> getAllComponents()
	{
		return new ArrayList<>(COMPONENTS.values());
	}

	/**
	 * Returns the instance of the Component removed, or null if no Component is removed.
	 * The Component removed will be of the given Component Class, if no Component of the
	 * given Class exists no Component will be removed.
	 *
	 * @param componentClass The Component Class of the Component to remove.
	 * @return The instance of the Component removed, or null if no Component is removed.
	 */
	public Component removeComponent(Class<? extends Component> componentClass)
	{
		return COMPONENTS.remove(componentClass);
	}

	/**
	 * Removes all components contain in this Entity.
	 */
	public void clear()
	{
		COMPONENTS.clear();
	}

	/**
	 * Returns true if the given Object is an instance of Entity and the Components it contains
	 * are equal to the Components of this Entity.
	 *
	 * @param object The Object to check this is equals with.
	 * @return true if the given Object is equal to this Entity, false otherwise.
	 */
	@Override
	public boolean equals(Object object)
	{
		Entity otherEntity = (Entity) object;
		return Objects.equals(COMPONENTS, otherEntity.COMPONENTS);
	}

	/**
	 * Returns the hashcode for this Entity.
	 *
	 * @return The hashcode for this Entity.
	 */
	@Override
	public int hashCode()
	{
		return Objects.hash(COMPONENTS);
	}
}
