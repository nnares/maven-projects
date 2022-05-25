package com.nishchay.mock;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SpyVsMock {

    /*
    * The mock simply creates a bare-bones shell instance of the Class
    * Not having any real interaction over object
    * */
    @Test
    public void mockedObjectBehaviourTest() {
        List<String> mockedList = Mockito.mock(ArrayList.class);

        mockedList.add("one");
        Mockito.verify(mockedList).add("one");

        assertEquals(0, mockedList.size());
        System.out.println("mockedList = " + mockedList); //mockedList = Mock for ArrayList, hashCode: 195984832
    }

    /*
    * the spy will wrap an existing instance. It will still behave in the same way as the normal instance
    *  Having real interaction over object
    * */
    @Test
    public void spiedObjectBehaviourTest() {

        List<String> spyList = Mockito.spy(new ArrayList<>());

        spyList.add("one");
        Mockito.verify(spyList).add("one");

        assertEquals(1, spyList.size());
        System.out.println("spyList = " + spyList); //spyList = [one]
    }

}
