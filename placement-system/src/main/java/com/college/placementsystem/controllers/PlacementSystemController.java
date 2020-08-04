package com.college.placementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.placementsystem.model.ApplicationResponse;
import com.college.placementsystem.model.LoginResponse;
import com.college.placementsystem.services.PlacementSystemService;

@CrossOrigin()
@RestController
@RequestMapping({ "/api" })
public class PlacementSystemController {
	@Autowired
	private PlacementSystemService placementSystemService;
	
	@GetMapping(produces = "application/json")
	@RequestMapping({ "/validateLogin" })
	public LoginResponse validateLogin() {
		return new LoginResponse("User successfully authenticated");
	}
	
	@GetMapping("/application/{userId}")
	public ApplicationResponse findApplication(@PathVariable int userId) {
		return placementSystemService.findApplication(userId);
	}
	
}
