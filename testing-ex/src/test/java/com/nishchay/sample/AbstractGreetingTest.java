package com.nishchay.sample;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbstractGreetingTest {

    @Test
    public void test_AbstractClasses() {

        AbstractGreeting abstract_class = mock(AbstractGreeting.class);

        when(abstract_class.greet()).thenCallRealMethod();
        when(abstract_class.getName()).thenReturn("Java");

        assertEquals("Hii.. Java!!", abstract_class.greet());
    }
}