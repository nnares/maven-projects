package com.nishchay.sample;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.text.IsEmptyString.emptyOrNullString;
import static org.hamcrest.text.IsEmptyString.emptyString;

public class HamcrestMatcherTest {

    @Test
    public void basicHamcrestBooleanMatcher() {
        boolean a = true;
        boolean b = true;

        // the is method is a thin wrapper for equalTo(value)
        assertThat(a, equalTo(b));
        assertThat(a, is(equalTo(b)));
        assertThat(a, is(b));
    }

    @Test
    public void basicHamcrestNumberMatcher() {
        assertThat(5, greaterThan(2));
        assertThat(5, greaterThanOrEqualTo(5));
        assertThat(-1, lessThan(0));
        assertThat(-1, lessThanOrEqualTo(5));
        assertThat(1.2, closeTo(1, 0.5));
        assertThat(1.2, greaterThan(1.0));


        assertThat(Integer.valueOf(1), instanceOf(Integer.class));
        // shortcut for instanceOf
        assertThat(Integer.valueOf(1), isA(Integer.class));

        Integer aInteger = 4;
        assertThat(aInteger, hasToString("4"));

        Double aDouble = 3.14;
        assertThat(aDouble, hasToString(containsString(".")));
    }


    @Test
    public void basicHamcrestTextMatcher() {

        assertThat("", emptyString());
        assertThat(null, emptyOrNullString());
        assertThat(null, nullValue());
        assertThat("apple", not("orange"));

        String str1 = "foo";
        assertThat(str1, is(any(String.class))); // Matches any variable of the given type
        String str2 = "FOO";
        assertThat(str1, equalToIgnoringCase(str2));

        str2 = "  foo  ";
        assertThat(str1, equalToCompressingWhiteSpace(str2));

        str1 = "java";
        str2 = null;
        assertThat(str1, notNullValue());
        assertThat(str2, nullValue());
        assertThat(str1, not(str2));


        str2 = "java";
        assertThat(str1, equalTo(str2));
        // the is method is a thin wrapper for equalTo(value)
        assertThat(str1, is(str2));
        assertThat(str1, is(equalTo(str2)));

        assertThat("programming language", stringContainsInOrder(Arrays.asList("program", "language")));
        assertThat("programming language", containsString("program"));
        assertThat("programming language", startsWith("program"));
        assertThat("programming language", startsWith("prog"));
        assertThat("programming language", endsWith("guage"));
        assertThat("programming language", endsWith("language"));

        assertThat(String.class, typeCompatibleWith(Object.class));

    }


    @Test
    public void basicHamcrestCollectionMatcher() {

        List<Integer> scores = Arrays.asList(99, 100, 101, 105);
        assertThat(scores, hasSize(4));
        assertThat(scores, hasItem(100));
        assertThat(scores, hasItem(is(greaterThan(104))));
        assertThat(scores, hasItems(100, 101));
        assertThat(scores, everyItem(greaterThan(90)));
        assertThat(scores, everyItem(lessThan(200)));
        assertThat(scores, hasItems(greaterThan(90), lessThan(110)));

        // Array
        Integer[] marks = {1, 2, 7, 5, 4, 8};
        assertThat(marks, arrayWithSize(6));
        assertThat(marks, hasItemInArray(4));
        assertThat(marks, hasItemInArray(is(greaterThan(6))));
        assertThat(marks, arrayContainingInAnyOrder(7, 2, 5, 4, 1, 8));


        String[] strings = {"why", "hello", "there"};
        assertThat(strings, is(arrayWithSize(3)));
        assertThat(strings, is(arrayContaining(startsWith("wh"), equalTo("hello"), endsWith("here"))));
        assertThat(strings, is(arrayContainingInAnyOrder(endsWith("lo"), startsWith("the"), equalTo("why"))));
        String[] specifiedValues = {"why", "hello", "there"};
        assertThat(strings, equalTo(specifiedValues));


        List<String> strList = Arrays.asList("boolean", "beans", "text", "number");
        assertThat(strList, hasSize(4));
        assertThat(strList, containsInAnyOrder("beans", "text", "boolean", "number"));
        // validating elements and their order as well
        assertThat(strList, contains("boolean", "beans", "text", "number"));


        Map<String, String> map = new HashMap<>();
        map.put("blogname", "baeldung");
        assertThat(map, hasKey("blogname"));
        assertThat(map, hasValue("baeldung"));
        assertThat(map, hasEntry("blogname", "baeldung"));

        Set<String> set = new HashSet<>();
        assertThat(set, is(empty()));
        assertThat(set, is(emptyIterable()));


        String[] testArray = {};
        assertThat(testArray, is(emptyArray()));
        testArray = new String[0];
        assertThat(testArray, is(emptyArray()));
    }


    @Test
    public void basicHamcrestBeanOrObjectsMatcherTest() {

        Person person = null;
        assertThat(person, nullValue());

        person = new Person();
        assertThat(person, notNullValue());
        assertThat(person, sameInstance(person));
        assertThat(person, instanceOf(Person.class));


        /*
         *  when a bean is having a specific property/attribute or not
         *  to validate the hasProperty, bean class must have setter & getter for those property/attribute
         * */
        person = new Person("Rohit Sharma", "Mumbai");
        assertThat(person, hasProperty("name"));

        person = new Person("Rohit Sharma", "Mumbai");
        assertThat(person, hasProperty("address", equalTo("Mumbai")));


        // when teo beans having same values
        Person person1 = new Person("Iron Man", "New York");
        Person person2 = new Person("Iron Man", "New York");
        assertThat(person1, samePropertyValuesAs(person2));

    }

}
