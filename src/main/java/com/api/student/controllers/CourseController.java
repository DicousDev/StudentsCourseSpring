package com.api.student.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.student.dto.CourseDTO;
import com.api.student.services.CourseService;

@RestController
@RequestMapping(value = "/courses")
public class CourseController {

	@Autowired
	private CourseService service;
	
	@GetMapping
	public List<CourseDTO> findAll() {
		return service.findAll();
	}
}
