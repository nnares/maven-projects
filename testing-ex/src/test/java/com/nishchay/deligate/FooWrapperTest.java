package com.nishchay.deligate;

import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.Assert.*;

/*
 * https://stackoverflow.com/questions/22225663/
 * */
public class FooWrapperTest {


    @Test
    public void testAllMethodsAreDelegated() throws InvocationTargetException, IllegalAccessException {

        Foo delegate = Mockito.mock(Foo.class);
        FooWrapper wrapper = new FooWrapper(delegate,"cache-name");

        for (Method fooMethod : Foo.class.getDeclaredMethods()) {
            boolean methodCalled = false;

            for (Method wrapperMethod : FooWrapper.class.getDeclaredMethods()) {
                if (fooMethod.getName().equals(wrapperMethod.getName())
                        && Arrays.equals(wrapperMethod.getParameterTypes(), fooMethod.getParameterTypes())) {

                    // Get parameters for method
                    Class<?>[] parameterTypes = wrapperMethod.getParameterTypes();
                    Object[] parameters = new Object[parameterTypes.length];
                    for (int j = 0; j < parameterTypes.length; j++) {
                        if (parameterTypes[j].isAssignableFrom(String.class)) {
                            parameters[j] = Mockito.any(String.class);
                        } else if (parameterTypes[j].isArray()) {
                            parameters[j] = new Object[]{};
                        } else {
                            parameters[j] = Mockito.mock(parameterTypes[j]);
                        }
                    }

                    // Invoke wrapper method
                    wrapperMethod.invoke(wrapper, parameters);

                    // Ensure method was called on delegate exactly once with the correct arguments
                    fooMethod.invoke(Mockito.verify(delegate, Mockito.times(1)), parameters);

                    // Set flag to indicate that this foo method is wrapped properly.
                    methodCalled = true;
                }
            }

            System.out.println("Foo method '" + fooMethod.getName() + "' has been wrapped correctly in FooWrapper - " + methodCalled);
            assertTrue("Foo method '" + fooMethod.getName() + "' has not been wrapped correctly in Foo wrapper", methodCalled);
        }

    }

}