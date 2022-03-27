package com.api.student.dto;

import com.api.student.entities.Student;

public class StudentDTO {

	private Long id;
	private String name;
	private Integer age;
	private String email;
	
	public StudentDTO() {
		
	}
	
	public StudentDTO(Student student) {
		id = student.getId();
		name = student.getName();
		age = student.getAge();
		email = student.getEmail();
	}
	
	public StudentDTO(String name, Integer age, String email) {
		this.name = name;
		this.age = age;
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public String getEmail() {
		return email;
	}
}
