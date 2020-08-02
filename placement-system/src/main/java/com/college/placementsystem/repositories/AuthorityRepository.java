package com.college.placementsystem.repositories;

import org.springframework.data.repository.CrudRepository;

import com.college.placementsystem.entities.Authority;

public interface AuthorityRepository  extends CrudRepository<Authority, Integer>  {
	Authority findByUsername(String username);
}
