package org.clowdy.entity;

import org.clowdy.entity.component.Component;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("EntityBuilder Tests")
public class EntityBuilderTest
{
	private static class TestComponent extends Component
	{
		public float a;
	}

	private static final EntityBuilder entityBuilder = new EntityBuilder();
	private static Component component = new TestComponent();

	private static Entity entity;

	@BeforeEach
	void setUp()
	{
		component = new TestComponent();
	}

	@Test
	@DisplayName("Two Built Entities Have Different IDs")
	void buildEntityCreatesEntityWithUniqueID()
	{
		entity = entityBuilder.buildEntity();
		Entity entity1 = entityBuilder.buildEntity();

		assertNotEquals(entity1.getId(), entity.getId());
	}

	@Test
	@DisplayName("EntityBuilder with a Component Adds it to the Entity")
	void buildEntityWithAComponentCreatesEntityContainingThatComponent()
	{
		entity = entityBuilder.withComponent(component)
				.buildEntity();

		assertTrue(entity.hasComponent(TestComponent.class));
	}

	@Test
	@DisplayName("EntityBuilder with Multiple Instances of a Component Class Adds Only the First to the Entity")
	void buildEntityWithComponentsOfSameComponentClassCreatesEntityContainingFirstComponent()
	{
		Component component2 = new TestComponent();
		((TestComponent) component2).a = 0.1f;

		entity = entityBuilder.withComponent(component)
				.withComponent(component2)
				.buildEntity();

		assertTrue(entity.hasComponent(TestComponent.class));
		assertTrue(entity.hasComponent(component));
		assertFalse(entity.hasComponent(component2));
	}

	@Test
	@DisplayName("Copy Entity with EntityBuilder Creates Entity with Equal Components but Different ID")
	void copyOfEntityIsEqualToOriginalButHasDifferentID()
	{
		Entity entityToClone = entityBuilder.withComponent(component)
				.buildEntity();

		entity = entityBuilder.copyEntity(entityToClone)
				.buildEntity();

		assertEquals(entityToClone, entity);
		assertNotEquals(entityToClone.getId(), entity.getId());
	}
}
