package com.api.student.dto;

import com.api.student.entities.Course;

public class CourseDTO {

	private Long id;
	private String name;
	
	public CourseDTO() {
		
	}
	
	public CourseDTO(Long id, String name) {
		setId(id);
		setName(name);
	}
	
	public CourseDTO(String name) {
		setName(name);
	}
	
	public CourseDTO(Course course) {
		setId(course.getId());
		setName(course.getName());
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
