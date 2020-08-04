package com.college.placementsystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CourseSection")
public class CourseSection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "courseId", referencedColumnName = "id")
	private Course course;
	
	@Column
	private String professor;
	
	@Column
	private String daysOfWeek;
	
	@Column
	private String time;
	
	@Column
	private String building;
	
	@Column
	private String room;
}
