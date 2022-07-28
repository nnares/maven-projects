package nishchay.qn1;

/**
 * Employee class
 *
 * @author Nishchay Naresh
 */
public class Employee extends Person {

    private final String role;

    public Employee(String name, int age, String role) {
        super(name, age);
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        if (!(o instanceof Employee))
            return false;
        if (!super.equals(o))
            return false;

        Employee employee = (Employee) o;

        boolean roleEquals = (this.role == null && employee.role == null)
                || (this.role != null && this.role.equals(employee.role));

        return roleEquals;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        if (role != null) {
            result = 31 * result + role.hashCode();
        }
        return result;
    }

    public String getRole() {
        return role;
    }
}