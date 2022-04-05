package com.api.student.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.student.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
