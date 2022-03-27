package com.api.student.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.student.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	boolean existsByEmail(String email);
}
