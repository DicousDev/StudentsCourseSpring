package com.api.student.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.api.student.entities.Course;
import com.api.student.entities.Student;

public class StudentDTO {

	private Long id;
	private String name;
	private Integer age;
	private String email;
	private Set<CourseDTO> courses;
	
	public StudentDTO() {
		courses = new HashSet<CourseDTO>();
	}
	
	public StudentDTO(Student student) {
		id = student.getId();
		name = student.getName();
		age = student.getAge();
		email = student.getEmail();
		setCourses(student.getCourses());
	}
	
	public StudentDTO(String name, Integer age, String email) {
		this.name = name;
		this.age = age;
		this.email = email;
		courses = new HashSet<CourseDTO>();
	}
	
	public void insertCourse(CourseDTO course) {
		courses.add(course);
	}
	
	public void removeCourse(CourseDTO course) {
		courses.remove(course);
	}
	
	public boolean containsCourseById(Long idCourse) {

		for(CourseDTO j : courses) {
			
			if(idCourse == j.getId()) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean hasCourseRepetitive(Long idCourse) {
		int size = courses.stream().filter(x -> idCourse == x.getId()).collect(Collectors.toSet()).size();
		return size > 1;
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
	
	public CourseDTO getCourseById(Long idCourse) {
		
		for(CourseDTO course : courses) {
			
			if(course.getId() == idCourse) {
				return course;
			}
		}
		
		return new CourseDTO();
	}

	public Set<CourseDTO> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses.stream()
				.map(x -> new CourseDTO(x)).collect(Collectors.toSet());
	}
}
