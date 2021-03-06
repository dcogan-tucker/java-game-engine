package org.clowdy.entity;

import org.clowdy.component.Component;
import org.clowdy.component.ComponentPool;
import org.clowdy.component.TestPhysicsComponent;
import org.clowdy.component.TestRenderComponent;
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
public class EntityTest {
    private static Entity entity1, entity2;
    private static Component physicsComponent1, physicsComponent2;
    private static final Component renderComponent = new TestRenderComponent();

    @BeforeEach
    void setUp() {
        entity1 = new Entity(new Entity.ComponentManager());
        entity2 = new Entity(new Entity.ComponentManager());
        physicsComponent1 = new TestPhysicsComponent();
        physicsComponent2 = new TestPhysicsComponent();
    }

    @Test
    @DisplayName("ID of Any Two Entities are not Equal")
    void everyEntityHasAUniqueID() {
        Entity otherEntity = new Entity(new Entity.ComponentManager());

        assertNotEquals(otherEntity.getId(), entity1.getId());
    }

    @Test
    @DisplayName("Adding Component of Class not yet Present in Entity Returns True")
    void addComponentToEntityReturnsTrue() {
        assertTrue(entity1.addComponent(physicsComponent1));
    }

    @Test
    @DisplayName("Adding Component of Class Already Present in Entity Returns False")
    void addDuplicateComponentToEntityReturnsFalse() {
        entity1.addComponent(physicsComponent1);
        assertFalse(entity1.addComponent(physicsComponent2));
    }

    @Test
    @DisplayName("Adding Null Value Component Returns False")
    void addNullComponentReturnsFalse() {
        assertFalse(entity1.addComponent(null));
    }

    @Test
    @DisplayName("Check if Has Component of Class Previously Added Returns True")
    void hasComponentOnComponentClassAddedReturnsTrue() {
        entity1.addComponent(physicsComponent1);
        assertTrue(entity1.hasComponent(TestPhysicsComponent.class));
    }

    @Test
    @DisplayName("Check if Has Component of Class Not Previously Added Returns False")
    void hasComponentOnComponentClassNotAddedReturnsFalse() {
        assertFalse(entity1.hasComponent(TestPhysicsComponent.class));
    }

    @Test
    @DisplayName("Check if Has Null Class Value Returns False")
    void hasComponentOnNullClassValueReturnsFalse() {
        Class<? extends Component> nullClass = null;
        assertFalse(entity1.hasComponent(nullClass));
    }

    @Test
    @DisplayName("Check if Has Exact Component Added Returns True")
    void hasComponentOnSameComponentAsAddedReturnsTrue() {
        entity1.addComponent(physicsComponent1);
        assertTrue(entity1.hasComponent(physicsComponent1));
    }

    @Test
    @DisplayName("Check if Has Component Equal to Component Added Returns True")
    void hasComponentOnEqualComponentAsAddedReturnsTrue() {
        entity1.addComponent(physicsComponent1);
        assertTrue(entity1.hasComponent(physicsComponent2));
    }

    @Test
    @DisplayName("Check if Has Component on Component Not Added Returns False")
    void hasComponentOnComponentNotAddedReturnsFalse() {
        assertFalse(entity1.hasComponent(physicsComponent1));
    }

    @Test
    @DisplayName("Check if Has Component Not Equal But Same Class of Component Added Returns False")
    void hasComponentOnNotEqualComponentOfSameTypeAddedReturnsFalse() {
        entity1.addComponent(physicsComponent1);

        ((TestPhysicsComponent) physicsComponent2).a = 1f;

        assertFalse(entity1.hasComponent(physicsComponent2));
    }

    @Test
    @DisplayName("Check if Has Component That is Null Returns False")
    void hasComponentOnNullComponentReturnsFalse() {
        physicsComponent1 = null;

        assertFalse(entity1.hasComponent(physicsComponent1));
    }

    @Test
    @DisplayName("Get Component of Class Instance Added Returns That Component")
    void getComponentAddedReturnsComponent() {
        entity1.addComponent(physicsComponent1);
        assertEquals(physicsComponent1, entity1.getComponent(physicsComponent1.getClass()));
    }

    @Test
    @DisplayName("Get Component of Class Instance Not Added Returns Null")
    void getComponentNotAddedReturnsNull() {
        assertNull(entity1.getComponent(physicsComponent1.getClass()));
    }

    @Test
    @DisplayName("Get Component of Null Value Returns Null")
    void getComponentNullReturnsNull() {
        assertNull(entity1.getComponent(null));
    }

    @Test
    @DisplayName("Get All Components After Adding One Component Returns List of Single Component")
    void getAllComponentsReturnsListOfSingleComponentAdded() {
        entity1.addComponent(physicsComponent1);

        List<Component> actual = entity1.getAllComponents();
        List<Component> expected = Collections.singletonList(physicsComponent1);

        MatcherAssert.assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    @DisplayName("Get All Components After Adding Two Unique Class Components Returns List of Both Added")
    void getAllComponentsReturnListOfTwoComponentsAdded() {
        entity1.addComponent(physicsComponent1);
        entity1.addComponent(renderComponent);

        List<Component> actual = entity1.getAllComponents();
        List<Component> expected = Arrays.asList(physicsComponent1, renderComponent);

        MatcherAssert.assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    @DisplayName("Get All Components After Adding A Duplicate Class Returns List of First Instance of Each Class Added")
    void getAllComponentsReturnsListOfFirstComponentsOfEachTypeAdded() {
        entity1.addComponent(physicsComponent2);
        entity1.addComponent(physicsComponent1);
        entity1.addComponent(renderComponent);

        List<Component> actual = entity1.getAllComponents();
        List<Component> expected = Arrays.asList(physicsComponent2, renderComponent);

        MatcherAssert.assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    @DisplayName("Get ComponentPool of a Type of an Added Component Returns Component Pool Containing That Entity")
    void getComponentPoolOfTypeOfAnAddedComponentReturnsComponentPoolWithThatEntity() {
        entity1.addComponent(physicsComponent1);

        ComponentPool actual = entity1.getComponentPool(Component.PoolType.PHYSICS);

        ComponentPool expected = new ComponentPool(Component.PoolType.PHYSICS);
        expected.addComponent(physicsComponent1);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get ComponentPool Not of a Type of an Added Component Returns Null")
    void getComponentPoolOfTypeNotOfAnAddedComponentReturnsNull() {
        entity1.addComponent(physicsComponent1);

        assertNull(entity1.getComponentPool(Component.PoolType.RENDER));
    }

    @Test
    @DisplayName("Get ComponentPool of Null Returns Null")
    void getComponentPoolOfNullReturnsNull() {
        assertNull(entity1.getComponentPool(null));
    }

    @Test
    @DisplayName("Get All Component Pools After Adding Component Returns Correct Component Pools")
    void getAllComponentPoolsReturnsListOfComponentsAddedInComponentPools() {
        entity1.addComponent(physicsComponent1);

        List<ComponentPool> actual = entity1.getAllComponentPools();

        ComponentPool physicPool = new ComponentPool(Component.PoolType.PHYSICS);
        physicPool.addComponent(physicsComponent1);
        ComponentPool testPool = new ComponentPool(Component.PoolType.TEST);
        testPool.addComponent(physicsComponent1);
        List<ComponentPool> expected = Arrays.asList(physicPool, testPool);

        MatcherAssert.assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    @DisplayName("Remove Component of Class Added Returns Instance of Component")
    void removeComponentTypeAddedToEntityReturnsComponent() {
        entity1.addComponent(physicsComponent1);

        assertEquals(physicsComponent1, entity1.removeComponent(TestPhysicsComponent.class));
    }

    @Test
    @DisplayName("Remove Component of Class Not Added Returns Null")
    void removeComponentTypeNotAddedToEntityReturnsNull() {
        assertNull(entity1.removeComponent(TestPhysicsComponent.class));
    }

    @Test
    @DisplayName("Remove Component of Null Class Returns Null")
    void removeComponentNullTypeReturnsNull() {
        assertNull(entity1.removeComponent(null));
    }

    @Test
    @DisplayName("Clear Entity Removes All Entities Added So Get Component Returns List of Size Zero")
    void clearEntityRemovesAllComponentsGetComponentReturnsListOfSizeZero() {
        entity1.addComponent(physicsComponent1);
        entity1.addComponent(physicsComponent2);
        entity1.clear();

        assertEquals(0, entity1.getAllComponents().size());
    }

    @Test
    @DisplayName("Two Entities With Equal Components are Equal")
    void entitiesWithEqualComponentsAreEqual() {
        entity1.addComponent(physicsComponent1);
        entity2.addComponent(physicsComponent2);

        assertEquals(entity2, entity1);
    }

    @Test
    @DisplayName("Two Entities With Non Equal Components are Not Equal")
    void entitiesWithNonEqualComponentsAreNotEqual() {
        entity1.addComponent(physicsComponent1);
        entity2.addComponent(renderComponent);

        assertNotEquals(entity2, entity1);
    }

    @Test
    @DisplayName("Entity is Not Equal to a Non Entity Object")
    void nonEntityObjectNotEqualToAnEntity() {
        assertNotEquals(new Object(), entity1);
    }

    @Test
    @DisplayName("Two Entities That are Equal Have Equal HashCodes")
    void equalEntitiesHaveEqualHashcode() {
        entity1.addComponent(physicsComponent1);
        entity2.addComponent(physicsComponent2);

        assertEquals(entity2.hashCode(), entity1.hashCode());
    }
}
