package com.college.placementsystem.repositories;

import org.springframework.data.repository.CrudRepository;

import com.college.placementsystem.entities.ProgrammingLanguage;

public interface ProgrammingLanguageRepository extends CrudRepository<ProgrammingLanguage, Integer> {
	ProgrammingLanguage findById(int id);
}
