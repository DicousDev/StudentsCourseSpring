package com.api.student.entities;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.api.student.dto.CourseDTO;
import com.api.student.dto.StudentDTO;

@Entity
@Table(name = "tb_student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer age;
	private String email;
	
	@ManyToMany()
	@JoinTable(name = "tb_student_course", joinColumns= {@JoinColumn(name = "student_id")}, 
	inverseJoinColumns= {@JoinColumn(name = "course_id")})
	private Set<Course> courses;
	
	public Student() {
		
	}
	
	public Student(StudentDTO dto) {
		setId(dto.getId());
		setName(dto.getName());
		setAge(dto.getAge());
		setEmail(dto.getEmail());
		setCourses(dto.getCourses());
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<CourseDTO> courses) {
		this.courses = courses.stream().map(x -> new Course(x)).collect(Collectors.toSet());
	}
}
