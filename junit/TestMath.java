package com.mine.test.junit.math;

import org.junit.Assert;
import org.junit.Test;

public class TestMath {

	public TestMath() {
	}

	Math math = new Math();

	@Test
	public void testSum() {
		Assert.assertEquals("Math.sum() is not working fine", 5, math.sum(2, 3));
		Assert.assertEquals("Fail", -5, math.sum(-2, -3));
	}

	@Test
	public void testSubtract() {
		Assert.assertEquals("Fail", -2, math.subtract(3, 5));
		Assert.assertEquals("Fail", 5, math.subtract(8, 3));
	}

	@Test
	public void testMultiply() {
		Assert.assertEquals("Fail", -12, math.multiply(3, -4));
		Assert.assertEquals("Fail", 9, math.multiply(-3, -3));
	}

	@Test
	public void testDivide() {
		Assert.assertEquals("Fail", 3, math.divide(13, 4));
		Assert.assertEquals("Fail", 1, math.divide(6, 6));
	}

}
