package com.nishchay.mock;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ArgumentCaptureBasicTest {

    @Test
    public void argumentCaptureBasicTest() {
        List<String> mockList = mock(List.class);
        mockList.add("java");

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockList).add(argumentCaptor.capture());
        assertEquals("java", argumentCaptor.getValue());

    }

    @Test
    public void argumentCaptureGenericTest() {
        List<String> mockList = mock(List.class);
        mockList.add(Mockito.anyString());

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockList).add(argumentCaptor.capture());
        assertEquals(Mockito.anyString(), argumentCaptor.getValue());
    }


    /*
     * ====== Verify as Argument Capture Alternative=======
     * This is more popular in project
     *
     * */
    @Test
    public void argumentCaptureAlternativeVerify() {

        List<String> mockList = mock(List.class);
        mockList.add("java");
        mockList.add("perl");

        // same like argument capture
        verify(mockList).add("java");
        verify(mockList).add("perl");
    }

}
