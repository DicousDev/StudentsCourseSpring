package com.api.student.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.student.dto.CourseDTO;
import com.api.student.dto.StudentDTO;
import com.api.student.services.CourseService;
import com.api.student.services.StudentService;

@SpringBootTest
public class StudentsTests {
	
	@Autowired
	private StudentService service;
	
	@Test
	public void findStudentByIdOfDatabase() {
		StudentDTO student = service.findById(1L);
		Boolean studentExists = student.getId() != null;
		assertThat(studentExists).isEqualTo(true);
	}
	
	@Test
	public void create() {
		StudentDTO student = new StudentDTO("create", 21, "create@create.com");
		StudentDTO studentGenerated = service.create(student);
		assertThat(studentGenerated.getId()).isNotNull();
	}
	
	@Test
	public void registerInDatabase() {
		StudentDTO student = new StudentDTO("registerInDatabase", 21, "registerInDatabase@registerInDatabase.com");
		StudentDTO studentGenerated = service.create(student);
		StudentDTO findStudent = service.findById(studentGenerated.getId());
		boolean studentExistsInDatabase = findStudent.getId() != null;
		assertThat(studentExistsInDatabase).isTrue();
	}
	
	@Test
	public void invalidEmail() {
		StudentDTO student = new StudentDTO("invalidEmail", 21, "joaodicu387@gmail.com");
		StudentDTO studentGenerated = service.create(student);
		assertThat(studentGenerated.getId()).isNull();
	}
	
	@Test
	public void containsCourseInStudent() {
		StudentDTO student = service.findById(1L);
		boolean contains = student.containsCourseById(2L);
		assertThat(contains).isTrue();
	}
	
	@Test
	public void insertCourse() {
		StudentDTO student = new StudentDTO("insertCourse", 21, "insertCourse@gmail.com");
		StudentDTO studentGenerated = service.create(student);
		StudentDTO studentUpdated = service.insertCourse(studentGenerated.getId(), 1L);
		boolean containsCourse = studentUpdated.containsCourseById(1L);
		assertThat(containsCourse).isTrue();
	}
	
	@Test
	public void insertCourseInDatabase() {
		StudentDTO student = new StudentDTO("insertCourseInDatabase", 21, "insertCourseInDatabase@gmail.com");
		StudentDTO studentGenerated = service.create(student);
		StudentDTO studentUpdated = service.insertCourse(studentGenerated.getId(), 1L);
		boolean containsCourse = studentUpdated.containsCourseById(1L);
		assertThat(containsCourse).isTrue();
	}
	
	@Test
	public void notInsertCourseRepetitive() {
		StudentDTO studentUpdated = service.insertCourse(1L, 2L);
		boolean containsCourseRepetitive = studentUpdated.hasCourseRepetitive(2L);
		assertThat(containsCourseRepetitive).isFalse();
	}
	
	@Test
	public void removeCourse() {
		Long courseId = 2L;
		StudentDTO student = new StudentDTO("removeCourse", 21, "removeCourse@removeCourse.com");
		StudentDTO studentGenerated = service.create(student);
		StudentDTO studentUpdated = service.insertCourse(studentGenerated.getId(), courseId);
		studentUpdated = service.removeCourse(studentUpdated.getId(), courseId);
		boolean containsCourseInStudent = studentGenerated.containsCourseById(courseId);
		boolean result = !containsCourseInStudent && studentGenerated.getId() == studentUpdated.getId();
		assertThat(result).isTrue();
	}
}
