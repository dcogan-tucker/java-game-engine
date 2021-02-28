package org.clowdy.component;

import org.clowdy.util.ReflectionEqualsHelper;

public abstract class Component
{
	private final PoolType[] poolTypes;

	public Component()
	{
		poolTypes = setPoolTypes();
	}

	protected abstract PoolType[] setPoolTypes();

	public PoolType[] getPoolTypes()
	{
		return poolTypes;
	}

	@Override
	public boolean equals(Object obj)
	{
		return ReflectionEqualsHelper.areEquals(this, obj);
	}

	@Override
	public int hashCode()
	{
		return ReflectionEqualsHelper.hashcode(this);
	}

	public enum PoolType
	{
		RENDER,
		COLLISION,
		PHYSICS,
		TEST
	}
}
