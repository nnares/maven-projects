package com.nish.ems.util;

import com.nish.ems.model.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Tester {

    private static SessionFactory factory;

    public static void main(String[] args) {

        Employee employee1 = new Employee("Zara", "Ali", 1000);
        Employee employee2 = new Employee("Daisy", "Das", 5000);
        Employee employee3 = new Employee("John", "Paul", 10000);

        EmployeeUtil employeeUtil = new EmployeeUtil();

        /* Add few employee records in database */
        System.out.println("Added employee with id -" + employeeUtil.addEmployee(employee1));
        System.out.println("Added employee with id -" + employeeUtil.addEmployee(employee2));
        System.out.println("Added employee with id -" + employeeUtil.addEmployee(employee3));

        /* List down all the employees */
        System.out.println("---------------Listing All current employee-------------");
        employeeUtil.listEmployees();

        /* Update employee's records */
/*        Employee employee4 = new Employee();
        employee4.setId(9);
        employee4.setSalary(8000);
        employeeUtil.updateEmployee(employee4);*/



        /* Delete an employee from the database */
/*        int id=7;
        employeeUtil.deleteEmployee(id);*/

        /* List down all the employees */
//        System.out.println("---------------Listing All current employee-------------");
//        employeeUtil.listEmployees();

    }


}
