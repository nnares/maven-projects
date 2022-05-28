package com.nishchay.deligate;

import org.junit.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.Marker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class PrefixLoggerTest {


    @Test
    public void testAllMethodsDelegationWithoutEnrichment() throws InvocationTargetException, IllegalAccessException {

        Logger mockLogger = Mockito.mock(Logger.class);
        PrefixLogger prefixLogger = new PrefixLogger(mockLogger, "");

        for (Method logMethod : Logger.class.getDeclaredMethods()) {
            boolean methodCalled = false;

            for (Method prefixLogMethod : PrefixLogger.class.getDeclaredMethods()) {
                if (logMethod.getName().equals(prefixLogMethod.getName()) && Arrays.equals(prefixLogMethod.getParameterTypes(), logMethod.getParameterTypes())) {

                    // Get parameters for method
                    Class<?>[] parameterTypes = prefixLogMethod.getParameterTypes();
                    Object[] parameters = new Object[parameterTypes.length];
                    for (int j = 0; j < parameterTypes.length; j++) {
                        if (String.class.equals(parameterTypes[j])) {
                            parameters[j] = "";
                        } else if (parameterTypes[j].isArray()) {
                            parameters[j] = new Object[]{};
                        } else {
                            parameters[j] = Mockito.mock(parameterTypes[j]);
                        }
                    }

                    // Invoke prefixLogger method
                    prefixLogMethod.invoke(prefixLogger, parameters);
                    // Ensure method was called on mockLogger exactly once with the correct arguments
                    logMethod.invoke(Mockito.verify(mockLogger, Mockito.times(1)), parameters);
                    methodCalled = true;
                }
            }
            assertTrue("Logger clazz method '" + logMethod.getName() + "' has not been wrapped correctly in Foo prefix logger", methodCalled);
        }
    }

  private static final String prefix = "prefix{}";


    @Test
    public void testAllMethodsDelegationWithEnrichment() throws InvocationTargetException, IllegalAccessException{

        Logger mockLogger = mock(Logger.class);
        PrefixLogger prefixLogger = new PrefixLogger(mockLogger, prefix);

        for (Method prefixLogMethod : Logger.class.getDeclaredMethods()) {

            Class<?>[] parameterTypes = prefixLogMethod.getParameterTypes();
            Object[] parameters = new Object[parameterTypes.length];
            for (int j = 0; j < parameterTypes.length; j++) {
                if (String.class.equals(parameterTypes[j])) {
                    parameters[j] = "log-msg";
                } else if (parameterTypes[j].isArray()) {
                    parameters[j] = new Object[]{};
                } else {
                    parameters[j] = mock(parameterTypes[j]);
                }
            }

            prefixLogMethod.invoke(prefixLogger, parameters);
            prefixLogMethod.invoke(verify(mockLogger, times(1)), enrichStringParamValues(parameters, isMsgParameter(prefixLogMethod)));

        }
    }

    private Object[] enrichStringParamValues(Object[] parameters, boolean msgParameter) {

        String enrichPrefix = msgParameter ? prefix : prefix.replace("{}", "\\{}");

        Object[] enrichParams = Arrays.copyOf(parameters, parameters.length);
        for (int i = 0; i < enrichParams.length; i++)
            if (enrichParams[i] instanceof String)
                enrichParams[i] = enrichPrefix + enrichParams[i];

        return enrichParams;
    }

    public boolean isMsgParameter(Method method) {

        Class<?>[] parameterTypes = method.getParameterTypes();

        return parameterTypes.length == 1 && String.class.equals(parameterTypes[0])
                || parameterTypes.length == 2 && String.class.equals(parameterTypes[0]) && Throwable.class.equals(parameterTypes[1])
                ||  parameterTypes.length == 2 && Marker.class.equals(parameterTypes[0]) && String.class.equals(parameterTypes[1])
                ||  parameterTypes.length == 3 && Marker.class.equals(parameterTypes[0]) && String.class.equals(parameterTypes[1]) & Throwable.class.equals(parameterTypes[2]);

    }

}