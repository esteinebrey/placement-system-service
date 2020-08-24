package com.college.placementsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.placementsystem.model.ApplicationModel;
import com.college.placementsystem.model.CourseModel;
import com.college.placementsystem.model.LoginResponse;
import com.college.placementsystem.services.PlacementSystemService;

@CrossOrigin()
@RestController
@RequestMapping({ "/api" })
public class PlacementSystemController {
	@Autowired
	private PlacementSystemService placementSystemService;
	
	
	//@GetMapping(produces = "application/json")
	//@RequestMapping({ "/validateLogin" })
	
	// Validate login
	@GetMapping("/validateLogin")
	public LoginResponse validateLogin() {
		return new LoginResponse("User successfully authenticated");
	}
	
	// Get the application for a specific user, if they have submitted one
	@GetMapping("/application/{userId}")
	public ApplicationModel findApplication(@PathVariable int userId) {
		return placementSystemService.findApplication(userId);
	}
	
	// Get the application for a specific user, if they have submitted one
	@GetMapping("/applications")
	public List<ApplicationModel> findAllApplications() {
		return placementSystemService.findAllApplications();
	}
	
	// Post the application for a specific user when they submit it
	@PostMapping("/application/{userId}")
	public ApplicationModel submitApplication(@PathVariable int userId, @RequestBody ApplicationModel application) {
		return placementSystemService.submitApplication(userId, application);
	}
	
	// Get all courses
	@GetMapping("/courses")
	public List<CourseModel> findAllCourses() {
		return placementSystemService.findAllCourses();
	}
	
}
