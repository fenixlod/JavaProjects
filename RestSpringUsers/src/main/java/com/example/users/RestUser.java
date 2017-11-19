package com.example.users;

public class RestUser {
	private String name;
	private int id;
	private String role;
	
	RestUser() {
		name = "none";
		id = 0;
		role = "user";
	}
	
	RestUser(String userName,int userId,String userRole) {
		name = userName;
		id = userId;
		role = userRole;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setName(String newName) {
		name = newName;
	}
}
