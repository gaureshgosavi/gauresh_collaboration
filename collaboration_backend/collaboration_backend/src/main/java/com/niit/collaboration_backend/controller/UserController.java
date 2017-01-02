package com.niit.collaboration_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration_backend.dao.UserDAO;
import com.niit.collaboration_backend.model.User;

@RestController
public class UserController {

	@Autowired
	User user;

	@Autowired
	UserDAO userDAO;

	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userDAO.list();
		if (users.isEmpty()) {
			user.setErrorCode("404");
			user.setErrorMessage("No Users present.");
			users.add(user);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/get/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("userId") int userId) {
		System.out.println("Fetching User");
		User user = userDAO.getById(userId);
		if (user == null) {
			user.setErrorCode("404");
			user.setErrorMessage("User does not exist.");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user) {

		user = userDAO.getByUsername(user.getUsername());
		if (user == null) {
			if(userDAO.saveOrUpdate(user) == false){
				user.setErrorCode("404");
				user.setErrorMessage("Failed to register. Please try again.");
			}
			else{
				user.setErrorCode("200");
				user.setErrorMessage("You are registered successfully.");
			}
				
			
		} else {
			user.setErrorCode("404");
			user.setErrorMessage("User already present with this username.");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/update/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		if(userDAO.saveOrUpdate(user) == false){
			user.setErrorCode("404");
			user.setErrorMessage("Failed to update. Please try again.");
		}
		else{
			user.setErrorCode("200");
			user.setErrorMessage("You are updated successfully.");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public ResponseEntity<User> validateUser(@RequestBody User user) {

		user = userDAO.validate(user);
		if(user == null){
			user.setErrorCode("404");
			user.setErrorMessage("Incorrect username or password.");
		}else{
			user.setErrorCode("200");
			user.setErrorMessage("WELCOME!");
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
