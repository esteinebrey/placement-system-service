package com.college.placementsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.college.placementsystem.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>  {
	User findByUsername(String username);
	
	User findById(int id);
}
