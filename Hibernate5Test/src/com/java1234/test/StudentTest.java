package com.java1234.test;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Test;

import com.java1234.model.Student;
import com.java1234.util.HibernateUtil;

public class StudentTest {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Test
	public void testAdd() {
		Student stu = AddStudent();
	}

	@Test
	public void testupdate() {
		Student stu = AddStudent();
		stu.setName("Test_Updated");
		UpdateStudent(stu);
	}

	@Test
	public void testDelete() {
		Student stu = AddStudent();
		DeleteStudent(stu);
	}

	@Test
	public void testGet() {
		Student stu = AddStudent();
		Student stu2 = GetStudent(stu.getId());
	}

	@Test
	public void testGetAll() {
		List<Student> students = GetAllStudents();
	}

	private Student GetStudent(long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Student stu = session.get(Student.class, Long.valueOf(id));

		session.getTransaction().commit();
		session.close();

		return stu;
	}

	private List<Student> GetAllStudents() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Student");
		List<Student> students = query.list();

		session.getTransaction().commit();
		session.close();

		return students;
	}

	private Student DeleteStudent(Student stu) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.delete(stu);

		session.getTransaction().commit();
		session.close();

		return stu;
	}

	private Student AddStudent() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Student stu = new Student();
		stu.setName("James");
		session.save(stu);

		session.getTransaction().commit();
		session.close();

		return stu;
	}

	private Student UpdateStudent(Student stu) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(stu);

		session.getTransaction().commit();
		session.close();

		return stu;
	}

}
