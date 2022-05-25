package com.nishchay.mock;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/*
 * Difference between @Mock and @InjectMocks
 * In mockito based junit tests, @Mock annotation creates mocks and @InjectMocks creates class objects.
 *
 * @Mock – creates mocks
 * @InjectMocks – creates objects and inject mocked dependencies
* */

//@RunWith(MockitoJUnitRunner.class)
public class ApplicationClazzTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    DatabaseDAO dependentClassOne;

    @Mock
    NetworkDAO dependentClassTwo;

    @InjectMocks
    ApplicationClazz application;

/*
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
*/


    @Test
    public void fileSaveTest() {
        boolean saved = application.save("temp.txt");
        assertEquals(true, saved);

        verify(dependentClassOne, times(1)).save("temp.txt");
        verify(dependentClassTwo, times(1)).save("temp.txt");
    }

}