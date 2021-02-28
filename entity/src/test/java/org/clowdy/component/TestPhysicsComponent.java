package org.clowdy.component;

public class TestPhysicsComponent extends Component
{
	public float a;

	@Override
	protected PoolType[] setPoolTypes()
	{
		return new PoolType[]{PoolType.PHYSICS, PoolType.TEST};
	}
}
