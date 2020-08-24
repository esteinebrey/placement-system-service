package com.college.placementsystem.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.college.placementsystem.entities.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {
	List<Course> findAll();
	
	Course findById(int id);
}
