package com.nishchay;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ArgumentCaptureBasicTest {

    @Test
    public void argumentCaptureBasicTest() {
        List<String> mockList = mock(List.class);
        mockList.add("java");//m1->m2->m3->add

//        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockList).add("java1"); // same like argument capture


//        assertEquals("java", argumentCaptor.getValue());

    }

    @Test
    public void argumentCaptureGenericTest() {
        List<String> mockList = mock(List.class);
        mockList.add(Mockito.anyString());

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockList).add(argumentCaptor.capture());

        assertEquals(Mockito.anyString(), argumentCaptor.getValue());
    }


    @Test
    public void whenMockFinalClassMockWorks() {

        String finalClazzObject = new String();

        String mockString = mock(String.class);
        Mockito.when(mockString.length()).thenReturn(2);

        assertNotEquals(mockString.length(), finalClazzObject.length());
    }
    /*
        org.mockito.exceptions.base.MockitoException:
        Cannot mock/spy class java.lang.String
        Mockito cannot mock/spy because :
            - final class
    * */

}
