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
@Table(name = "Course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String deptCode;
	
	@Column
	private String courseNumber;
	
	@Column
	private String courseName;
	
	@ManyToMany(mappedBy = "takenCourses")
	List<Application> correspondingApplications;

	public List<Application> getCorrespondingApplications() {
		return correspondingApplications;
	}

	public void setCorrespondingApplications(List<Application> correspondingApplications) {
		this.correspondingApplications = correspondingApplications;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getId() {
		return id;
	}
}
