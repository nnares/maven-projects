package com.nishchay;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MockitoFunctionalityTest {

    @Test
    public void testArgumentCaptureBasic() {
        List<String> mockList = mock(List.class);
        mockList.add("java");

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockList).add(argumentCaptor.capture());

        assertEquals("java", argumentCaptor.getValue());

    }

    @Test
    public void testArgumentCapture() {
        List<String> mockList = mock(List.class);
        mockList.add(Mockito.anyString());

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockList).add(argumentCaptor.capture());

        assertEquals(Mockito.anyString(), argumentCaptor.getValue());
    }

}
