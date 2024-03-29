package com.nishchay.param;

import com.nishchay.sample.MathUtils;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;

// Parameterized via Constructor
@RunWith(value = Parameterized.class)
public class ParameterizedTestConstructor {

    private final int numberA;
    private final int numberB;
    private final int expected;

    // Inject via constructor
    // for {8, 2, 10}, numberA = 8, numberB = 2, expected = 10
    public ParameterizedTestConstructor(int numberA, int numberB, int expected) {
        this.numberA = numberA;
        this.numberB = numberB;
        this.expected = expected;
    }

	// name attribute is optional, provide an unique name for test
	// multiple parameters, uses Collection<Object[]>
    @Parameters(name = "{index} : testAdd({0}+{1}) = {2}")
    public static Collection<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {1, 1, 2},
                {2, 2, 4},
                {8, 2, 10},
                {4, 5, 9},
                {5, 5, 10}
        });

    }

    @Test
    public void test_addTwoNumbers() {
        MatcherAssert.assertThat(MathUtils.add(numberA, numberB), is(expected));
    }

}