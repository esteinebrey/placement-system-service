package com.college.placementsystem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ApplicationSkill")
public class ApplicationSkill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToMany
	@JoinColumn(name = "applicationId", referencedColumnName = "id")
	private Application application;
	
	@ManyToMany
	@JoinColumn(name = "skillId", referencedColumnName = "id")
	private ProgrammingLanguage skill;

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public ProgrammingLanguage getSkill() {
		return skill;
	}

	public void setSkill(ProgrammingLanguage skill) {
		this.skill = skill;
	}
}
