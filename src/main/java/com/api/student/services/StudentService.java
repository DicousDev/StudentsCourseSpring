package com.api.student.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.student.dto.StudentDTO;
import com.api.student.entities.Student;
import com.api.student.repositories.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;
	
	@Transactional(readOnly = true)
	public List<StudentDTO> findAll() {
		return repository.findAll().stream()
				.map(x -> new StudentDTO(x)).toList();
	}
	
	@Transactional(readOnly = true)
	public StudentDTO findById(Long idStudent) {
		Student student = repository.findById(idStudent).get();
		return new StudentDTO(student);
	}
	
	@Transactional()
	public StudentDTO create(StudentDTO dto) {
		Boolean existsEmail = repository.existsByEmail(dto.getEmail());
		if(existsEmail) {
			return new StudentDTO();
		}
		
		Student student = new Student(dto);
		Student saved = repository.save(student);
		return new StudentDTO(saved);
	}
}
