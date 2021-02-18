package org.clowdy.entity;

import com.rits.cloning.Cloner;
import org.clowdy.entity.component.Component;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class EntityBuilder
{
	// Cloner to create deep copies of components.
	private static final Cloner CLONER = new Cloner();

	// Map of Component to be added to Entity on build call.
	private final Map<Class<? extends Component>, Component> COMPONENTS = new HashMap<>();

	/**
	 * Return this EntityBuilder after deep copying all Components of the given Entity.
	 *
	 * @param entity The Entity to copy.
	 * @return This EntityBuilder.
	 */
	public EntityBuilder copyEntity(Entity entity)
	{
		entity.getAllComponents().forEach(component ->
				COMPONENTS.put(component.getClass(), CLONER.deepClone(component)));
		return this;
	}

	/**
	 * Return this EntityBuilder after adding the given component to it. The given component
	 * will be successfully added if there does not already exists a Component of the same
	 * Component Class.
	 *
	 * @param component The Component to add.
	 * @return This EntityBuilder.k
	 */
	public EntityBuilder withComponent(Component component)
	{
		COMPONENTS.putIfAbsent(component.getClass(), component);
		return this;
	}

	/**
	 * Return a new Entity instance containing the Components added to the EntityBuilder.
	 *
	 * @return A new Entity instance.
	 */
	public Entity buildEntity()
	{
		Entity entity = new Entity();
		COMPONENTS.values().forEach(component ->
				entity.addComponent(component));
		COMPONENTS.clear();
		return entity;
	}
}
