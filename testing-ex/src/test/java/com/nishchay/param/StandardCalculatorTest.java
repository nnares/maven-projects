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

@RunWith(Parameterized.class)
public class StandardCalculatorTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 0, 0},
                {1, 1, 2},
                {5, 10, 15}
        });
    }

    private final MathUtils mathUtils;
    private final int first;
    private final int second;
    private final int expectedSum;

    public StandardCalculatorTest(int first,
                                  int second,
                                  int expectedSum) {

        this.mathUtils = new MathUtils();
        this.first = first;
        this.second = second;
        this.expectedSum = expectedSum;
    }

    @Test
    public void shouldReturnCorrectSum() {
        MatcherAssert.assertThat(mathUtils.sum(first, second), is(expectedSum));
    }
}