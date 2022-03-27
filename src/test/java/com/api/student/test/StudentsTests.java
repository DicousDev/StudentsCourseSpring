package com.api.student.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.student.dto.StudentDTO;
import com.api.student.services.StudentService;

@SpringBootTest
public class StudentsTests {
	
	@Autowired
	private StudentService service;
	
	@Test
	public void studentExists() {
		StudentDTO student = service.findById(1L);
		Boolean studentExists = student.getId() != null;
		assertThat(studentExists).isEqualTo(true);
	}
	
	@Test
	public void create() {
		StudentDTO student = new StudentDTO("Fim De testes", 21, "joaotestes@joaotestes.com");
		StudentDTO studentGenerated = service.create(student);
		assertThat(studentGenerated.getId()).isNotNull();
	}
	
	@Test
	public void registerInDatabase() {
		StudentDTO student = new StudentDTO("Fim De testes", 21, "testes@testes.com");
		StudentDTO studentGenerated = service.create(student);
		StudentDTO findStudent = service.findById(studentGenerated.getId());
		Boolean studentExistsInDatabase = findStudent.getId() != null;
		assertThat(studentExistsInDatabase).isEqualTo(true);
	}
	
	@Test
	public void validateEmail() {
		StudentDTO student = new StudentDTO("Fim De testes", 21, "joaodicu387@gmail.com");
		StudentDTO studentGenerated = service.create(student);
		assertThat(studentGenerated.getId()).isNull();
	}
}
