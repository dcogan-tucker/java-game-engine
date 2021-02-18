package org.clowdy.entity.component;

import org.clowdy.ReflectionEqualsHelper;

public abstract class Component
{
	@Override
	public boolean equals(Object obj)
	{
		return ReflectionEqualsHelper.areEquals(this, obj);
	}
}
