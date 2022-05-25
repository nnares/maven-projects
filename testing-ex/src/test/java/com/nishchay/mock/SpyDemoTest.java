package com.nishchay.mock;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SpyDemoTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Spy
    List<String> spyList = new ArrayList<String>();

    @Test
    public void whenUsingTheSpyAnnotation_thenObjectIsSpied() {
        spyList.add("one");
        spyList.add("two");

        Mockito.verify(spyList).add("one");
        Mockito.verify(spyList).add("two");

        assertEquals(2, spyList.size());
    }

    @Test
    public void whenSpyingOnList_thenCorrect() {
        List<String> realObj = new ArrayList<>();
        List<String> spyList = Mockito.spy(realObj);

        spyList.add("one");
        spyList.add("two");

        Mockito.verify(spyList).add("one");
        Mockito.verify(spyList).add("two");
        assertEquals("one", spyList.get(0));
        assertEquals("two", spyList.get(1));

        assertEquals(2, spyList.size());
        assertNotEquals(2, realObj.size());
    }

    @Test
    public void whenStubASpy_thenStubbed() {
        List<String> list = new ArrayList<>();
        List<String> spyList = Mockito.spy(list);

        assertEquals(0, spyList.size());

        Mockito.doReturn(100).when(spyList).size();
        assertEquals(100, spyList.size());
        assertEquals(0, list.size());
    }

}
