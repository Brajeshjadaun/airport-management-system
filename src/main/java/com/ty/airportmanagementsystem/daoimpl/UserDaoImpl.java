package com.ty.airportmanagementsystem.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.airportmanagementsystem.dao.UserDao;
import com.ty.airportmanagementsystem.dto.User;
import com.ty.airportmanagementsystem.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User userRegisterDao(User user) {
		return userRepository.save(user);
	}

	@Override
	public User fetchUserByEmailForLoginDao(String email) {
		return userRepository.findByUserEmail(email);
	}

}
