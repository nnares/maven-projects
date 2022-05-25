package com.nishchay.mock;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class VerifyTest {

    @Test
    public void verifyFeatureEx() {

        // Verify simple invocation on mock:
        List<String> mockedList = mock(List.class);
        mockedList.size();
        verify(mockedList).size();

        // Verify number of interactions with mock:
        mockedList = mock(List.class);
        mockedList.size();
        verify(mockedList, times(1)).size();

        // Verify no interaction with the whole mock occurred:
        mockedList = mock(List.class);
        verifyNoInteractions(mockedList);
        verifyNoMoreInteractions(mockedList);

        // Verify no interaction with a specific method occurred:
        mockedList = mock(List.class);
        verify(mockedList, times(0)).size();
        verify(mockedList, never()).clear();

        // Verify order of interactions:
        mockedList = mock(List.class);
        mockedList.size();
        mockedList.add("a parameter");
        mockedList.clear();

        InOrder inOrder = Mockito.inOrder(mockedList);
        inOrder.verify(mockedList).size();
        inOrder.verify(mockedList).add("a parameter");
        inOrder.verify(mockedList).clear();

        // Verify an interaction has occurred at least a certain number of times:
        mockedList = mock(List.class);
        mockedList.clear();
        mockedList.clear();
        mockedList.clear();

        verify(mockedList, atLeast(1)).clear();
        verify(mockedList, atMost(10)).clear();

        // Verify interaction with exact argument:
        mockedList = mock(List.class);
        mockedList.add("test");
        verify(mockedList).add("test");

        // Verify interaction with flexible/any argument:
        mockedList = mock(List.class);
        mockedList.add("test");
        verify(mockedList).add(anyString());


        // Verify interaction using argument capture:

/*        mockedList = mock(List.class);
        mockedList.addAll(Lists.<String> newArrayList("someElement"));
        ArgumentCaptor<List> argumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(mockedList).addAll(argumentCaptor.capture());
        List<String> capturedArgument = argumentCaptor.<List<String>> getValue();
        assertThat(capturedArgument, hasItem("someElement"));
*/

    }
}
