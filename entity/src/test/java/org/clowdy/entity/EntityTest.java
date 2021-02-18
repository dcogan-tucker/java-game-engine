package org.clowdy.entity;

import org.clowdy.entity.component.Component;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Entity Tests")
public class EntityTest
{
	private static class TestComponent extends Component
	{
		public float a;
	}

	private static class TestComponentTwo extends Component {}

	private static Entity entity;
	private static Component testComponent1, testComponent2;
	private static final Component testComponentTwo1 = new TestComponentTwo();

	@BeforeEach
	void setUp()
	{
		entity = new Entity();
		testComponent1 = new TestComponent();
		testComponent2 = new TestComponent();
	}

	@Test
	@DisplayName("ID of Any Two Entities are not Equal")
	void everyEntityHasAUniqueID()
	{
		Entity otherEntity = new Entity();

		assertNotEquals(otherEntity.getId(), entity.getId());
	}

	@Test
	@DisplayName("Adding Component of Class not yet Present in Entity Returns True")
	void addComponentToEntityReturnsTrue()
	{
		assertTrue(entity.addComponent(testComponent1));
	}

	@Test
	@DisplayName("Adding Component of Class Already Present in Entity Returns False")
	void addDuplicateComponentToEntityReturnsFalse()
	{
		entity.addComponent(testComponent1);
		assertFalse(entity.addComponent(testComponent2));
	}

	@Test
	@DisplayName("Adding Null Value Component Returns False")
	void addNullComponentReturnsFalse()
	{
		assertFalse(entity.addComponent(null));
	}

	@Test
	@DisplayName("Check if Has Component of Class Previously Added Returns True")
	void hasComponentOnComponentClassAddedReturnsTrue()
	{
		entity.addComponent(testComponent1);
		assertTrue(entity.hasComponent(TestComponent.class));
	}

	@Test
	@DisplayName("Check if Has Component of Class Not Previously Added Returns False")
	void hasComponentOnComponentClassNotAddedReturnsFalse()
	{
		assertFalse(entity.hasComponent(TestComponent.class));
	}

	@Test
	@DisplayName("Check if Has Null Class Value Returns False")
	void hasComponentOnNullClassValueReturnsFalse()
	{
		Class<? extends Component> nullClass = null;
		assertFalse(entity.hasComponent(nullClass));
	}

	@Test
	@DisplayName("Check if Has Exact Component Added Returns True")
	void hasComponentOnSameComponentAsAddedReturnsTrue()
	{
		entity.addComponent(testComponent1);
		assertTrue(entity.hasComponent(testComponent1));
	}

	@Test
	@DisplayName("Check if Has Component Equal to Component Added Returns True")
	void hasComponentOnEqualComponentAsAddedReturnsTrue()
	{
		entity.addComponent(testComponent1);
		assertTrue(entity.hasComponent(testComponent2));
	}

	@Test
	@DisplayName("Check if Has Component on Component Not Added Returns False")
	void hasComponentOnComponentNotAddedReturnsFalse()
	{
		assertFalse(entity.hasComponent(testComponent1));
	}

	@Test
	@DisplayName("Check if Has Component Not Equal But Same Class of Component Added Returns False")
	void hasComponentOnNotEqualComponentOfSameTypeAddedReturnsFalse()
	{
		entity.addComponent(testComponent1);

		((TestComponent) testComponent2).a = 1f;

		assertFalse(entity.hasComponent(testComponent2));
	}

	@Test
	void hasComponentOnNullComponentReturnsFalse()
	{
		testComponent1 = null;

		assertFalse(entity.hasComponent(testComponent1));
	}

	@Test
	void getComponentAddedReturnsComponent()
	{
		entity.addComponent(testComponent1);
		assertEquals(testComponent1, entity.getComponent(testComponent1.getClass()));
	}

	@Test
	void getComponentNotAddedReturnsNull()
	{
		assertNull(entity.getComponent(testComponent1.getClass()));
	}

	@Test
	void getComponentNullReturnsNull()
	{
		assertNull(entity.getComponent(null));
	}

	@Test
	void getAllComponentsReturnsListOfSingleComponentAdded()
	{
		entity.addComponent(testComponent1);

		List<Component> actual = entity.getAllComponents();
		List<Component> expected = Collections.singletonList(testComponent1);

		MatcherAssert.assertThat(actual, containsInAnyOrder(expected.toArray()));
	}

	@Test
	void getAllComponentsReturnListOfTwoComponentsAdded()
	{
		entity.addComponent(testComponent1);
		entity.addComponent(testComponentTwo1);

		List<Component> actual = entity.getAllComponents();
		List<Component> expected = Arrays.asList(testComponent1, testComponentTwo1);

		MatcherAssert.assertThat(actual, containsInAnyOrder(expected.toArray()));
	}

	@Test
	void getAllComponentsReturnsListOfFirstComponentsOfEachTypeAdded()
	{
		entity.addComponent(testComponent2);
		entity.addComponent(testComponent1);
		entity.addComponent(testComponentTwo1);

		List<Component> actual = entity.getAllComponents();
		List<Component> expected = Arrays.asList(testComponent2, testComponentTwo1);

		MatcherAssert.assertThat(actual, containsInAnyOrder(expected.toArray()));
	}

	@Test
	void removeComponentTypeAddedToEntityReturnsComponent()
	{
		entity.addComponent(testComponent1);

		assertEquals(testComponent1, entity.removeComponent(TestComponent.class));
	}

	@Test
	void removeComponentTypeNotAddedToEntityReturnsNull()
	{
		assertNull(entity.removeComponent(TestComponent.class));
	}

	@Test
	void removeComponentNullTypeReturnsNull()
	{
		assertNull(entity.removeComponent(null));
	}

	@Test
	void clearEntityRemovesAllComponents()
	{
		entity.addComponent(testComponent1);
		entity.addComponent(testComponent2);
		entity.clear();

		assertEquals(0, entity.getAllComponents().size());
	}
}
