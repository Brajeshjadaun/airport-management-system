package com.ty.airportmanagementsystem.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.airportmanagementsystem.dao.UserDao;
import com.ty.airportmanagementsystem.dto.User;
import com.ty.airportmanagementsystem.response.ApplicationResponse;
import com.ty.airportmanagementsystem.service.UserService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ApplicationResponse<User> applicationResponse;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private HttpSession httpSession;
	
	@Override
	public ApplicationResponse<User> userRegisterService(User user) {
		User user2 = userDao.userRegisterDao(user);
		if(user2 != null) {
			applicationResponse.setStatusCode(HttpStatus.ACCEPTED.value());
			applicationResponse.setMessage("User---Registered---Successfully");
			applicationResponse.setDescription("u can follow below data what is addedd inside table");
			applicationResponse.setData(user2);
			return applicationResponse;
		}else {
			applicationResponse.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			applicationResponse.setMessage("User---Not---Registered");
			applicationResponse.setDescription("check SQL query or database");
			applicationResponse.setData(null);
			return applicationResponse;
		}
	}

	@Override
	public ApplicationResponse<User> fetchUserByEmailForLoginService(String email, String password) {
		User user = userDao.fetchUserByEmailForLoginDao(email);
		
		if(user != null) {
			if(user.getUserPassword().equals(password)) {
				httpSession.setAttribute("userSession", email);
				
				applicationResponse.setStatusCode(HttpStatus.FOUND.value());
				applicationResponse.setMessage("User---LoggedIn---Successfully");
				applicationResponse.setDescription("User session is created");
				applicationResponse.setData(user);
				return applicationResponse;
			}else {
				applicationResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
				applicationResponse.setMessage("User---Login---failed");
				applicationResponse.setDescription("incorrect user password");
				applicationResponse.setData(user);
				return applicationResponse;
			}
		}else {
			applicationResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
			applicationResponse.setMessage("User---Login---Failed");
			applicationResponse.setDescription("User with given email not found");
			applicationResponse.setData(null);
			return applicationResponse;
		}
	}

}
