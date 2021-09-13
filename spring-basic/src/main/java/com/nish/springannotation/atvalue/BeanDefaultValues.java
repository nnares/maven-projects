package com.nish.springannotation.atvalue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
@PropertySource(value = {"classpath:application.properties"})
public class BeanDefaultValues {

    // assign default value to a class property using @Value annotation.
    @Value("Default DBConfiguration")
    private String defaultName;


    /*
    * 1. Basic Assignment
    * @Value annotation argument can be a string only, but spring tries to convert it to the specified type.
    *  Below code will work fine and assign the boolean and integer values to the variable.
    * */
    @Value("true")
    private boolean defaultBoolean;

    @Value("10")
    private int defaultInt;

    /*
    * 2. property file
    * if(prop.key is absent)
    *   Exception -Caused by: java.lang.IllegalArgumentException: Could not resolve placeholder 'country.name.ind' in value "${country.name.ind}"
    * Else
    *   Attribute will populate with the value
    * */
    @Value("${country.name.ind}")
    private String countryName;

    /*
    * 2.5 Nested format in @Value annotation
    *
    * */
    @Value("Welcome to ${country.name.ind}")
    private String welcomeCountryMsg;

    @Value("${some.key:${country.name.ind} is my country}")
    private String myCountryName;

    /*
    * 3. Default Value
    * prop.key:value -> for default value
    * We can avoid the Exception scenario for prop key absent. Assign a default value that will get assigned if the key is missing from property file.
    * */
    @Value("${country.name.us:Default}")
    private String defaultAppName;


    /*
    * propertied file value vs run config value
    * default =>  queueName='dev.activemq.outbound',
    * -Dmq.queue.name=run_config_queue_name =>  queueName='run_config_queue_name',
    * */
    @Value("${mq.queue.name}")
    private String queueName;



    /*
    * varName:prop.key
    * default =>  topicName='kafka.topic.name',
    * -DtopName=run_config_topic_name =>  queueName='run_config_topic_name',
    * */
    @Value("${topName:kafka.topic.name}")
    private String topicName;


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


    /*
    * Passing collection(like list, map) value from prop file
    *
    * */

    @Value("${string.values}")
    private String[] strArray;

    @Value("${string.values}") // list with single element
    private List<String> strList1;

    // SpEL
    @Value("#{'${string.values}'.split(',')}") // list with five element
    private List<String> strList2;

    @Value("#{'${int.values}'.split(',')}") // list with five element
    private List<Integer> intList;

    @Value("#{${map.values}}")
    private Map<String, String> strMap;

    @Override
    public String toString() {
        collectionSize();
        return "BeanDefaultValues{" +
                " \n defaultName='" + defaultName + '\'' +
                ",\n defaultBoolean=" + defaultBoolean +
                ",\n defaultInt=" + defaultInt +
                ",\n countryName='" + countryName + '\'' +
                ",\n welcomeCountryMsg='" + welcomeCountryMsg + '\'' +
                ",\n myCountryName='" + myCountryName + '\'' +
                ",\n queueName='" + queueName + '\'' +
                ",\n topicName='" + topicName + '\'' +
                ",\n defaultAppName='" + defaultAppName + '\'' +
                ",\n mavenHome='" + mavenHome + '\'' +
                ",\n javaHome='" + javaHome + '\'' +
                ",\n strArray='" + Arrays.toString(strArray) + '\'' +
                ",\n strList1='" + strList1 + '\'' +
                ",\n strList2='" + strList2 + '\'' +
                ",\n intList='" + intList + '\'' +
                ",\n strMap='" + strMap + '\'' +
                '}';

    }

    private void collectionSize() {
        System.out.println("strArray.length = " + strArray.length);
        System.out.println("strList1.size() = " + strList1.size());
        System.out.println("strList2.size() = " + strList2.size());
        System.out.println("intList.size() = " + intList.size());
        System.out.println("strMap.size() = " + strMap.size());
        System.out.println("----------------------------------------------");
    }
}
