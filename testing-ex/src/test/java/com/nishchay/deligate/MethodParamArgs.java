package com.nishchay.deligate;

import org.junit.Test;
import org.slf4j.Marker;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class MethodParamArgs {


    @Test
    public void methodParamsPrint() {

        for (Method prefixLogMethod : PrefixLogger.class.getDeclaredMethods()) {

            if(isLogEnrichmentMethod(prefixLogMethod)){
                printMethodDetails(prefixLogMethod);
            }

        }

    }

    public boolean isLogEnrichmentMethod(Method method) {

        Class<?>[] parameterTypes = method.getParameterTypes();

        return parameterTypes.length == 1 && String.class.equals(parameterTypes[0])
                || parameterTypes.length == 2 && String.class.equals(parameterTypes[0]) && Throwable.class.equals(parameterTypes[1])
                ||  parameterTypes.length == 2 && Marker.class.equals(parameterTypes[0]) && String.class.equals(parameterTypes[1])
                ||  parameterTypes.length == 3 && Marker.class.equals(parameterTypes[0]) && String.class.equals(parameterTypes[1]) & Throwable.class.equals(parameterTypes[2]);

    }

    public void printMethodDetails(Method method) {

        System.out.print("\n MethodName - " + method.getName());

        System.out.print("\n parameterTypes - ");
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (Class<?> e: parameterTypes ) {
            System.out.print(e.getName() + " ");
        }

        System.out.print("\n parameter names - ");
        Parameter[] parameters_name = method.getParameters();
        for (Parameter e: parameters_name ) {
            System.out.print(e.getName() + " ");
        }

        System.out.print("\n ----------------------------------");
    }


}
