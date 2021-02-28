package org.clowdy.component;

public class TestRenderComponent extends Component
{
	public float b;

	@Override
	protected PoolType[] setPoolTypes()
	{
		return new PoolType[]{PoolType.RENDER, PoolType.TEST};
	}
}
