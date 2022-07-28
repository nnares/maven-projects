package nishchay.qn1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private final Person person1 = new Person("John", 20);
    private final Person person2 = new Person("John", 20);

    @Test
    void testEquals() {
        assertEquals(person1, person2);
        assertTrue(person1.equals(person2));
    }

    @Test
    void testHashCode() {
        assertNotEquals(0, person1.hashCode());
        assertNotEquals(0, person2.hashCode());
        assertEquals(person1.hashCode(), person2.hashCode());

        assertEquals(person1.hashCode(), person1.hashCode());
        assertEquals(person2.hashCode(), person2.hashCode());
    }

    @Test
    void testEqualityForNull() {
        Person person3 = null;
        assertFalse(person1.equals(person3));
        assertFalse(person2.equals(person3));
    }

    @Test
    void testEqualityForDiffObject() {
        String str = "java";
        assertFalse(person1.equals(str));
        assertFalse(str.equals(person1));
    }

    @Test
    void testEqualsMethodContract() {
        assertTrue(person1.equals(person2));

        // reflexive
        assertTrue(person1.equals(person1));
        // symmetric
        assertTrue(person1.equals(person2) && person2.equals(person1));
        // transitive
        Person person3 = new Person("John", 20);
        assertTrue(person1.equals(person2) && person2.equals(person3) && person3.equals(person1));
        // consistent
        assertTrue(person1.equals(person2));
    }

    @Test
    void testPersonWithDiffAge() {
        Person person = new Person("John", 25);
        assertNotEquals(person, person1);
        assertNotEquals(person.getAge(), person1.getAge());
        assertNotEquals(person.hashCode(), person1.hashCode());
        assertFalse(person.equals(person1));
    }

    @Test
    void testPersonWithDiffName() {
        Person person = new Person("Smith", 20);
        assertNotEquals(person, person1);
        assertNotEquals(person.getName(), person1.getName());
        assertNotEquals(person.hashCode(), person1.hashCode());
        assertFalse(person.equals(person1));
    }

}