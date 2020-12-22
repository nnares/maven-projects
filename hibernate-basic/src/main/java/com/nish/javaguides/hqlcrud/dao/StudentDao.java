package com.nish.javaguides.hqlcrud.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nish.javaguides.hqlcrud.entity.Student;
import com.nish.javaguides.hqlcrud.util.HibernateUtil;

public class StudentDao {
	
	public void saveStudent(Student student) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(student);
			
			// operation 2
			session.get(Student.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public void insertStudent() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			String hql = "INSERT INTO Student (firstName, lastName, email) "
					+ "SELECT firstName, lastName, email FROM Student";
			Query query = session.createQuery(hql);
			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void updateStudent(Student student, int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// save the student object
			String hql = "UPDATE Student set firstName = :firstName, lastName = :lastName , email = :email " + "WHERE id = :studentId";
			Query query = session.createQuery(hql);
			query.setParameter("firstName", student.getFirstName());
			query.setParameter("lastName", student.getLastName());
			query.setParameter("email", student.getEmail());
			query.setParameter("studentId", id);
			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteStudent(int id) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a student object
			Student student = session.get(Student.class, id);
			if (student != null) {
				String hql = "DELETE FROM Student " + "WHERE id = :studentId";
				Query query = session.createQuery(hql);
				query.setParameter("studentId", id);
				int result = query.executeUpdate();
				System.out.println("Rows affected: " + result);
			}

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Student getStudent(int id) {

		Transaction transaction = null;
		Student student = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an student object
			String hql = " FROM Student S WHERE S.id = :studentId";
			Query query = session.createQuery(hql);
			query.setParameter("studentId", id);
			List results = query.getResultList();
			
			if (results != null && !results.isEmpty()) {
				student = (Student) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return student;
	}

	public List<Student> getStudents() {
		List<Student> students = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			students = session.createQuery("from Student", Student.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}

}