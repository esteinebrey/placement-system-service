package com.college.placementsystem.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwtToken;
	private final String permissions;
	private final int userId;

	public JwtResponse(String jwtToken, int userId, String permissions) {
		this.jwtToken = jwtToken;
		this.permissions = permissions;
		this.userId = userId;
	}

	public String getToken() {
		return this.jwtToken;
	}
	
	public String getPermissions() {
		return this.permissions;
	}
	
	public int getUserId() {
		return this.userId;
	}
}
