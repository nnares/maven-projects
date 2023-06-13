package com.nishchay.sample;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;

public class SpyTest {

    @Test
    public void givenUsingSpyMethod_whenSpyingOnList_thenCorrect() {
        List<String> list = new ArrayList<>();
        List<String> spyList = Mockito.spy(list);

        spyList.add("one");
        spyList.add("two");

        verify(spyList).add("one");
        verify(spyList).add("two");

        assertThat(spyList, hasSize(2));
    }

}
