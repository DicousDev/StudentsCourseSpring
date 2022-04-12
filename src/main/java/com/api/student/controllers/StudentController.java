package com.api.student.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.student.dto.CourseDTO;
import com.api.student.dto.StudentDTO;
import com.api.student.services.StudentService;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

	@Autowired
	private StudentService service; 
	
	@GetMapping
	public List<StudentDTO> findAll() {
		return service.findAll();
	}
		
	@GetMapping(value = "/{id}")
	public StudentDTO findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PostMapping
	public StudentDTO create(@RequestBody StudentDTO student) {
		return service.create(student);
	}
	
	@GetMapping(value = "{id}/courses")
	public Set<CourseDTO> findCoursesAll(@PathVariable Long id) {
		return service.findCoursesAll(id);
	}
	
	@GetMapping(value = "{idStudent}/courses/{idCourse}")
	public CourseDTO findCourseById(@PathVariable Long idStudent, @PathVariable Long idCourse) {
		return service.findCourseById(idStudent, idCourse);
	}
	
	@PostMapping(value = "{idStudent}/courses/{idCourse}")
	public StudentDTO insertCourse(@PathVariable Long idStudent, @PathVariable Long idCourse) {
		return service.insertCourse(idStudent, idCourse);
	}
	
	@DeleteMapping(value = "{idStudent}/courses/{idCourse}")
	public StudentDTO removeCourse(@PathVariable Long idStudent, @PathVariable Long idCourse) {
		return service.removeCourse(idStudent, idCourse);
	}
}
