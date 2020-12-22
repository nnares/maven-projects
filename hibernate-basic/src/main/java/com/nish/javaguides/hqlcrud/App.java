package com.nish.javaguides.hqlcrud;

import java.util.List;

import com.nish.javaguides.hqlcrud.dao.StudentDao;
import com.nish.javaguides.hqlcrud.entity.Student;

public class App {

	public static void main(String[] args) {

		StudentDao studentDao = new StudentDao();
		Student student = new Student("java", "programming", "java@gmail.com");
		studentDao.saveStudent(student);

		// get single student
		student = studentDao.getStudent(1);
		System.out.println("student with id 1 - " + student);

		// taking out the first inserted record, reinserting it again for id - 2
		studentDao.insertStudent();

		// updating last inserted record(id -2) with below object
		// update student
		student = new Student("java 8", "functional programming", "java8@gmail.com");
		studentDao.updateStudent(student, 2);

		// get students
		List<Student> students = studentDao.getStudents();
		students.forEach(System.out::println);


		// delete student
		studentDao.deleteStudent(1);

		students = studentDao.getStudents();
		students.forEach(System.out::println);

	}
}