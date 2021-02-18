package org.clowdy;

public class StringFormatter
{
	public static String removeTraillingZero(float value)
	{
		return value == (long) value ?
			Long.toString((long) value) : Float.toString(value);
	}
}
