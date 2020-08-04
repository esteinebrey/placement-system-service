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
}
