package com.college.placementsystem.services;

import org.springframework.stereotype.Service;

import com.college.placementsystem.model.ApplicationResponse;

@Service
public class PlacementSystemService {
	public ApplicationResponse findApplication(int userId) {
		return new ApplicationResponse();
	}
}
