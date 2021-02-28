package org.clowdy.component;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class ComponentTest<C extends Component>
{
	private C component1, component2;

	abstract C newComponentInstance();

	@BeforeEach
	void setUp()
	{
		component1 = newComponentInstance();
		component2 = newComponentInstance();
	}

	@Test
	void twoDefaultConstructedComponentsAreEqual()
	{
		assertEquals(component1, component2);
	}

	@Test
	void twoEqualComponentsHashCodesAreEqual()
	{
		assertEquals(component1.hashCode(), component2.hashCode());
	}
}
