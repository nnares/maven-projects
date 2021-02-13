package com.nish.springannotation.atvalue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"classpath:application.properties"})
public class BeanDefaultValues {

    // assign default value to a class property using @Value annotation.
    @Value("Default DBConfiguration")
    private String defaultName;


    /*
    * @Value annotation argument can be a string only, but spring tries to convert it to the specified type.
    *  Below code will work fine and assign the boolean and integer values to the variable.
    * */
    @Value("true")
    private boolean defaultBoolean;

    @Value("10")
    private int defaultInt;


    @Value("${country.name.ind}")
    private String countryName;
    /*
    * If the key - country.name.us is not found in the spring environment properties,
    * then the property value will be ${country.name.us}.
    * We can assign a default value that will get assigned if the key is missing from spring environment properties.
    * this will only happen if - @PropertySource is not been give,
    * else - Caused by: java.lang.IllegalArgumentException: Could not resolve placeholder 'country.name.us' in value "${country.name.us}"
    * */

    @Value("${country.name.us:Default}")
    private String defaultAppName;

    /*
    * Spring @Value – System Environment
    * MAVEN_HOME=E:\apache-maven-3.2.3
    * JAVA_HOME=C:\Program Files\Java\jdk1.8.0_152
    * */
    @Value("${MAVEN_HOME}")
    private String mavenHome;

    @Value("${JAVA_HOME}")
    private String javaHome;


    /*
    * Spring @Value with methods
    * If the method has multiple arguments, then every argument value is mapped from the method annotation.
    * If we want different values for different arguments then we can use @Value annotation directly with the argument.
    * */

//    @Value("Test") //both 's' and 'v' values will be 'Test'
    public void printValues(String s, String v){
        System.out.printf("s = %s \t v = %s ", s, v);
    }

//    @Value("Test") // s=Test, v=Data
    public void printValues1(String s, @Value("Data") String v){
        System.out.printf("s = %s \t v = %s ", s, v);
    }


    @Override
    public String toString() {
        return "BeanDefaultValues{" +
                "defaultName='" + defaultName + '\'' +
                ", defaultBoolean=" + defaultBoolean +
                ", defaultInt=" + defaultInt +
                ", countryName='" + countryName + '\'' +
                ", defaultAppName='" + defaultAppName + '\'' +
                ", mavenHome='" + mavenHome + '\'' +
                ", javaHome='" + javaHome + '\'' +
                '}';
    }
}
