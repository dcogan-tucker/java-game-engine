package org.clowdy.util;

import dagger.Module;
import dagger.Provides;
import org.clowdy.entity.Entity.ComponentManager;

import javax.inject.Singleton;

@Module
public class EntityBuilderModule
{
	@Provides @Singleton static ComponentManager provideComponentManager()
	{
		return new ComponentManager();
	}
}
