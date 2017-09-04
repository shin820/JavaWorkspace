package com.java1234.test;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Test;

import com.java1234.model.Teacher;
import com.java1234.util.HibernateUtil;

public class TeacherTest {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	@Test
	public void testAdd() {
		Teacher teacher = AddTeacher();
	}

	@Test
	public void teteacherpdate() {
		Teacher teacher = AddTeacher();
		teacher.setName("Test_Updated");
		UpdateTeacher(teacher);
	}

	@Test
	public void testDelete() {
		Teacher teacher = AddTeacher();
		DeleteTeacher(teacher);
	}

	@Test
	public void testGet() {
		Teacher teacher = AddTeacher();
		Teacher teacher2 = GetTeacher(teacher.getId());
	}

	@Test
	public void testGetAll() {
		List<Teacher> teachers = GetAllTeachers();
	}

	private Teacher GetTeacher(long id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Teacher teacher = session.get(Teacher.class, Long.valueOf(id));

		session.getTransaction().commit();
		session.close();

		return teacher;
	}

	private List<Teacher> GetAllTeachers() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Teacher");
		List<Teacher> teachers = query.list();

		session.getTransaction().commit();
		session.close();

		return teachers;
	}

	private Teacher DeleteTeacher(Teacher teacher) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.delete(teacher);

		session.getTransaction().commit();
		session.close();

		return teacher;
	}

	private Teacher AddTeacher() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Teacher teacher = new Teacher();
		teacher.setName("James");
		session.save(teacher);

		session.getTransaction().commit();
		session.close();

		return teacher;
	}

	private Teacher UpdateTeacher(Teacher teacher) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(teacher);

		session.getTransaction().commit();
		session.close();

		return teacher;
	}

}
