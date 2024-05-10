package com.ty.airportmanagementsystem.dao;

import com.ty.airportmanagementsystem.dto.User;

public interface UserDao {

	public User userRegisterDao(User user);
	public User fetchUserByEmailForLoginDao(String email);
	
	
}
