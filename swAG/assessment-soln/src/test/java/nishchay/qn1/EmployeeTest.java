package nishchay.qn1;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {


    private final Employee emp1 = new Employee("John", 20, "Tester");
    private final Employee emp2 = new Employee("John", 20, "Tester");

    @Test
    void testEquals() {
        assertEquals(emp1, emp2);
        assertTrue(emp1.equals(emp2));
    }

    @Test
    void testHashCode() {
        assertNotEquals(0, emp1.hashCode());
        assertNotEquals(0, emp2.hashCode());
        assertEquals(emp1.hashCode(), emp2.hashCode());

        assertEquals(emp1.hashCode(), emp1.hashCode());
        assertEquals(emp2.hashCode(), emp2.hashCode());
    }

    @Test
    void testEqualityForNull() {
        Employee emp3 = null;
        assertFalse(emp1.equals(emp3));
        assertFalse(emp2.equals(emp3));
    }


    @Test
    void testEqualityForDiffObject() {
        String str = "java";
        assertFalse(emp1.equals(str));
        assertFalse(str.equals(emp1));
    }

    @Test
    void testEqualsMethodContract() {
        assertTrue(emp1.equals(emp2));

        // reflexive
        assertTrue(emp1.equals(emp1));
        // symmetric
        assertTrue(emp1.equals(emp2) && emp2.equals(emp1));
        // transitive
        Employee emp3 = new Employee("John", 20, "Tester");
        assertTrue(emp1.equals(emp2) && emp2.equals(emp3) && emp3.equals(emp1));
        // consistent
        assertTrue(emp1.equals(emp2));
    }


    @Test
    void testEmpWithDiffName() {
        Employee emp = new Employee("Smith", 20, "Tester");
        assertNotEquals(emp, emp1);
        assertNotEquals(emp.getName(), emp1.getName());
        assertNotEquals(emp.hashCode(), emp1.hashCode());
        assertFalse(emp.equals(emp1));
    }

    @Test
    void testEmpWithDiffAge() {
        Employee emp = new Employee("John", 25, "Tester");
        assertNotEquals(emp, emp1);
        assertNotEquals(emp.getAge(), emp1.getAge());
        assertNotEquals(emp.hashCode(), emp1.hashCode());
        assertFalse(emp.equals(emp1));
    }

    @Test
    void testEmpWithDiffRole() {
        Employee emp = new Employee("John", 20, "Coder");
        assertNotEquals(emp, emp1);
        assertNotEquals(emp.getRole(), emp1.getRole());
        assertNotEquals(emp.hashCode(), emp1.hashCode());
        assertFalse(emp.equals(emp1));
    }

    @Test
    void testPersonEmployeeComparision() {
        Person person = new Person("John", 20);
        Employee emp = new Employee("John", 20, "Clerk");

        assertNotEquals(person, emp);
        assertFalse(person.equals(emp));
        assertFalse(emp.equals(person));
        assertNotEquals(person.hashCode(), emp.hashCode());
    }

}