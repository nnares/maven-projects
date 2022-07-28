package nishchay.qn1;

/**
 * Person class
 *
 * @author Nishchay Naresh
 */
public class Person {

    private final int age;
    private final String name;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!(o instanceof Person))
            return false;

        Person person = (Person) o;
        boolean nameEquals = (this.name == null && person.name == null)
                || (this.name != null && this.name.equals(person.name));
        return this.age == person.age && nameEquals;

    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + age;
        if (name != null) {
            result = 31 * result + name.hashCode();
        }
        return result;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}