package com.college.placementsystem.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.college.placementsystem.entities.Application;
import com.college.placementsystem.entities.ApplicationSkill;

public interface ApplicationSkillRepository extends CrudRepository<ApplicationSkill, Integer> {
	List<ApplicationSkill> findByApplication(Application application);
}
