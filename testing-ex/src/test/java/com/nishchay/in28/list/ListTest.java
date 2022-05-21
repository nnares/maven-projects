package com.nishchay.in28.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

public class ListTest {

    @Test
    public void letsMockListSize() {
        List<?> list = mock(List.class);
        Mockito.when(list.size()).thenReturn(10);
        assertEquals(10, list.size());
    }

    @Test
    public void letsMockListSizeWithMultipleReturnValues() {
        List<?> list = mock(List.class);
        Mockito.when(list.size()).thenReturn(10).thenReturn(20);
        assertEquals(10, list.size()); // First Call
        assertEquals(20, list.size()); // Second Call
    }

    @Test
    public void letsMockListGet() {
        List<String> list = mock(List.class);
        Mockito.when(list.get(0)).thenReturn("java");
        assertEquals("java", list.get(0));
        assertNull(list.get(1));
    }

    /*
     * Argument Matcher - Instead of providing an exact value, you provide an argument matcher for Mockito to match method arguments against.
     *
     * when(passwordEncoder.encode(anyString())).thenReturn("true");
     * */
    @Test
    public void letsMockListGetWithAny() {
        List<String> list = mock(List.class);

        Mockito.when(list.get(Mockito.anyInt())).thenReturn("java");
        // If you are using argument matchers, all arguments have to be provided by matchers.
        assertEquals("java", list.get(0));
        assertEquals("java", list.get(1));
    }


}