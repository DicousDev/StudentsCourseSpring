package com.api.student.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
