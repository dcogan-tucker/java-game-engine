package org.clowdy.util;

import dagger.Component;
import org.clowdy.entity.EntityBuilder;

import javax.inject.Singleton;

@Singleton
@Component(modules = EntityUtilityModule.class)
public interface EntityUtilityFactory {
    EntityBuilder getBuilder();
}
