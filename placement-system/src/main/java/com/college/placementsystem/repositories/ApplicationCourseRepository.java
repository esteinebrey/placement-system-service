package com.college.placementsystem.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.college.placementsystem.entities.Application;
import com.college.placementsystem.entities.ApplicationCourse;

public interface ApplicationCourseRepository extends CrudRepository<ApplicationCourse, Integer>{
	List<ApplicationCourse> findByApplication(Application application);
}
