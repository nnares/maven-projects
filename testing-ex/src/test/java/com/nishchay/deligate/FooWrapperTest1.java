package com.nishchay.deligate;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertTrue;

public class FooWrapperTest1 {


    @Mock
    Foo mockFoo;

    @InjectMocks
    FooWrapper mockWrapper;



    ArgumentCaptor<String> argumentCaptor;

    @Test
    public void testAllMethodsAreDelegated() throws InvocationTargetException, IllegalAccessException {

//        Foo delegate = Mockito.mock(Foo.class);
//        FooWrapper wrapper = new FooWrapper(delegate);

        // For each method in the Foo class...
        for (Method fooMethod : Foo.class.getDeclaredMethods()) {
            boolean methodCalled = false;

            // Find matching method in wrapper class and call it
            for (Method wrapperMethod : FooWrapper.class.getDeclaredMethods()) {
                if (fooMethod.getName().equals(wrapperMethod.getName())) {

                    // Get parameters for method
                    Class<?>[] parameterTypes = wrapperMethod.getParameterTypes();
                    Object[] parameters = new Object[parameterTypes.length];
                    for (int j = 0; j < parameterTypes.length; j++) {
                        if (String.class.equals(parameterTypes[j])) {

                            parameters[j] = Mockito.anyString();
//                            verify(mockWrapper).currMethodName(argumentCaptor.capture());
                        }
                        parameters[j] = Mockito.mock(parameterTypes[j]);
                    }
//                    Object[] parameters = Arrays.stream(wrapperMethod.getParameterTypes()).map(Mockito::mock).toArray();



                    // Invoke wrapper method
                    wrapperMethod.invoke(mockFoo, parameters);

                    // Ensure method was called on delegate exactly once with the correct arguments
                    fooMethod.invoke(Mockito.verify(mockWrapper, Mockito.times(1)), parameters);

                    // Set flag to indicate that this foo method is wrapped properly.
                    methodCalled = true;
                }
            }

            System.out.println("Foo method '" + fooMethod.getName() + "' has not been wrapped correctly in FooWrapper - " + methodCalled);
            assertTrue("Foo method '" + fooMethod.getName() + "' has not been wrapped correctly in Foo wrapper", methodCalled);
        }

    }


    @Test
    public void testAllMethodsCallAreDelegated(){


        Foo delegate = Mockito.mock(Foo.class);



        FooWrapper wrapper = new FooWrapper(delegate);




    }



}