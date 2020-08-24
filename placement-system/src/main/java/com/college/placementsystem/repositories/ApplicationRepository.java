package com.college.placementsystem.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.college.placementsystem.entities.Application;
import com.college.placementsystem.entities.User;

public interface ApplicationRepository extends CrudRepository<Application, Integer> {
	Optional<Application> findByUserId(User userId);
	
	Application findById(int id);
}
