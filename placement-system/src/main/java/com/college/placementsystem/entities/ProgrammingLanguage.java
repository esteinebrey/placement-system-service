package com.college.placementsystem.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ProgrammingLanguage")
public class ProgrammingLanguage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String skill;
	
	@ManyToMany(mappedBy = "currentSkills")
	List<Application> correspondingApplications;
	
	public List<Application> getCorrespondingApplications() {
		return correspondingApplications;
	}

	public void setCorrespondingApplications(List<Application> correspondingApplications) {
		this.correspondingApplications = correspondingApplications;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public int getId() {
		return id;
	}
}
