package nishchay.qn3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IntegerStreamTest {

    private static IntegerStream stream;

    @BeforeAll
    static void setup() {
        stream = new IntegerStream();

        stream.event(5);
        stream.event(8);
        stream.event(1);
        stream.event(4);
        stream.event(2);
        stream.event(8);
    }

    @Test
    void testEvent() {

        IntegerStream mockStream = mock(IntegerStream.class);
        doNothing().when(mockStream).event(anyInt());
        mockStream.event(5);
        verify(mockStream, times(1)).event(5);
        mockStream.event(8);
        verify(mockStream, times(1)).event(8);

        mockStream.event(1);
        mockStream.event(4);
        mockStream.event(2);
        mockStream.event(8);
        verify(mockStream, times(6)).event(anyInt());
    }

    @Test
    void testMean() {
        assertEquals(4.67, stream.mean(), 0.01);
    }

    @Test
    void testMinimum() {
        assertEquals(1, stream.minimum());
    }

    @Test
    void testMaximum() {
        assertEquals(8, stream.maximum());
    }

    @Test
    void variance() {
        assertEquals(8.22, stream.variance(), 0.01);
    }
}