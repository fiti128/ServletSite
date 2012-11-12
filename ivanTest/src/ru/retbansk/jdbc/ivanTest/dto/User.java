package ru.retbansk.jdbc.ivanTest.dto;

import ru.retbansk.jdbc.ivanTest.Role;

public class User {
	private int user_id;
	private String name;
	private String password;
	private Role role;
	
	public User() {}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUser_id() {
		return user_id;
	}
	
}
