package org.clowdy.entity;

import org.clowdy.component.Component;
import org.clowdy.component.TestPhysicsComponent;
import org.clowdy.entity.Entity.ComponentManager;
import org.clowdy.util.DaggerEntityUtilityFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("EntityBuilder Tests")
public class EntityBuilderTest {
    private static EntityBuilder entityBuilder = DaggerEntityUtilityFactory.create().getBuilder();
    private static Component physicsComponent;

    private static Entity entity;

    @BeforeEach
    void setUp() {
        entityBuilder = new EntityBuilder(new ComponentManager());
        physicsComponent = new TestPhysicsComponent();
    }

    @Test
    @DisplayName("Each Built Entity Has a Different ID")
    void buildEntityCreatesEntityWithUniqueID() {
        entity = entityBuilder.buildEntity();
        Entity entity1 = entityBuilder.buildEntity();

        assertNotEquals(entity1.getId(), entity.getId());
    }

    @Test
    @DisplayName("EntityBuilder with a Component Adds it to the Entity")
    void buildEntityWithAComponentCreatesEntityContainingThatComponent() {
        entity = entityBuilder.withComponent(physicsComponent)
                .buildEntity();

        assertTrue(entity.hasComponent(TestPhysicsComponent.class));
    }

    @Test
    @DisplayName("EntityBuilder with Multiple Instances of a Component Class Adds Only the First to the Entity")
    void buildEntityWithComponentsOfSameComponentClassCreatesEntityContainingFirstComponent() {
        TestPhysicsComponent secondPhysicsComponent = new TestPhysicsComponent();
        secondPhysicsComponent.a = 0.1f;

        entity = entityBuilder.withComponent(physicsComponent)
                .withComponent(secondPhysicsComponent)
                .buildEntity();

        assertTrue(entity.hasComponent(TestPhysicsComponent.class));
        assertTrue(entity.hasComponent(physicsComponent));
        assertFalse(entity.hasComponent(secondPhysicsComponent));
    }

    @Test
    @DisplayName("Copy Entity with EntityBuilder Creates Entity with Equal Components but Different ID")
    void copyOfEntityIsEqualToOriginalButHasDifferentID() {
        Entity entityToClone = entityBuilder.withComponent(physicsComponent)
                .buildEntity();

        entity = entityBuilder.copyEntity(entityToClone)
                .buildEntity();

        assertEquals(entityToClone, entity);
        assertNotEquals(entityToClone.getId(), entity.getId());
    }
}
