package com.college.placementsystem.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.placementsystem.model.LoginResponse;

@CrossOrigin()
@RestController
@RequestMapping({ "/api" })
public class PlacementSystemController {
	@GetMapping(produces = "application/json")
	@RequestMapping({ "/validateLogin" })
	public LoginResponse validateLogin() {
		return new LoginResponse("User successfully authenticated");
	}
}
