package com.api.student.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.student.dto.CourseDTO;
import com.api.student.entities.Course;
import com.api.student.repositories.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository repository;
	
	@Transactional(readOnly = true)
	public List<CourseDTO> findAll() {
		return repository.findAll().stream()
				.map(x -> new CourseDTO(x)).toList();
	}
	
	@Transactional(readOnly = true)
	public CourseDTO findById(Long id) {
		Course course = repository.findById(id).get();
		return new CourseDTO(course);
	}
}
