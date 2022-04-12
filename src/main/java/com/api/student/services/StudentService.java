package com.api.student.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.student.dto.CourseDTO;
import com.api.student.dto.StudentDTO;
import com.api.student.entities.Course;
import com.api.student.entities.Student;
import com.api.student.repositories.CourseRepository;
import com.api.student.repositories.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository repository;
	
	@Autowired
	private CourseRepository courseRepository;
	
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
	
	@Transactional(readOnly = true)
	public Set<CourseDTO> findCoursesAll(Long idStudent) {
		StudentDTO student = findById(idStudent);
		return student.getCourses();
	}
	
	@Transactional(readOnly = true)
	public CourseDTO findCourseById(Long idStudent, Long idCourse) {
		StudentDTO student = findById(idStudent);
		CourseDTO course = student.getCourseById(idCourse);
		return course;
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
	
	@Transactional()
	public StudentDTO insertCourse(Long idStudent, Long idCourse) {
		Student findStudent = repository.findById(idStudent).get();
		Course findCourse = courseRepository.findById(idCourse).get();
		
		StudentDTO studentDTO = new StudentDTO(findStudent);
		CourseDTO courseDTO = new CourseDTO(findCourse);
		studentDTO.insertCourse(courseDTO);
		
		// Update student
		Student student = new Student(studentDTO);
		Student saved = repository.save(student);
		return new StudentDTO(saved);
	}
	
	@Transactional()
	public StudentDTO removeCourse(Long idStudent, Long idCourse) {
		
		Student findStudent = repository.findById(idStudent).get();
		Course findCourse = courseRepository.findById(idCourse).get();
		
		StudentDTO studentDTO = new StudentDTO(findStudent);
		CourseDTO courseDTO = new CourseDTO(findCourse);
		studentDTO.removeCourse(courseDTO);
		
		// Update student
		Student student = new Student(studentDTO);
		repository.save(student);
		return studentDTO;
	}
}
