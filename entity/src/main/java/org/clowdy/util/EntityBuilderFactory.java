package org.clowdy.util;

import dagger.Component;
import org.clowdy.entity.EntityBuilder;

import javax.inject.Singleton;

@Singleton
@Component(modules = EntityBuilderModule.class)
public interface EntityBuilderFactory
{
	EntityBuilder getBuilder();
}
