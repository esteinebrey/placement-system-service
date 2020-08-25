package com.college.placementsystem.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Application")
public class Application {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;
	
	@Column
	private String name;
	
	@Column
	private String email;
	
	@Column
	private int idNumber;
	
	@Column
	private double gpa;
	
	@Column
	private String graduationDate;
	
	@Column
	private String major;
	
	@ManyToMany 
	@JoinTable(
		name = "ApplicationCourse",
		joinColumns = @JoinColumn(name = "applicationId"),
		inverseJoinColumns = @JoinColumn(name = "courseId"))
	List<Course> takenCourses;
	
	@ManyToMany 
	@JoinTable(
		name = "ApplicationSkill",
		joinColumns = @JoinColumn(name = "applicationId"),
		inverseJoinColumns = @JoinColumn(name = "skillId"))
	List<ProgrammingLanguage> currentSkills;
	
	public List<ProgrammingLanguage> getCurrentSkills() {
		return currentSkills;
	}

	public void setCurrentSkills(List<ProgrammingLanguage> currentSkills) {
		this.currentSkills = currentSkills;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public String getGraduationDate() {
		return graduationDate;
	}

	public void setGraduationDate(String graduationDate) {
		this.graduationDate = graduationDate;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public List<Course> getTakenCourses() {
		return takenCourses;
	}

	public void setTakenCourses(List<Course> takenCourses) {
		this.takenCourses = takenCourses;
	}

	public int getId() {
		return id;
	}
}
