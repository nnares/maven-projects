package com.nishchay.sample;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class DemoTest {

    @Test
    public void testMockObjectDefaultValues() {
        Demo demo = mock(Demo.class);
        assertEquals(0, demo.getInt());
        assertEquals(0, demo.getInteger().intValue());
        assertEquals(0d, demo.getDouble(), 0d);
        assertFalse(demo.getBoolean());
        assertNull(demo.getObject());
        assertEquals(Collections.emptyList(), demo.getCollection());
        assertNull(demo.getArray());
        assertEquals(0L, demo.getStream().count());
        assertFalse(demo.getOptional().isPresent());

    }

}