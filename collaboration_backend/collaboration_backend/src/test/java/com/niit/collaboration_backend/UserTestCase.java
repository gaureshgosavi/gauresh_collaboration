package com.niit.collaboration_backend;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration_backend.dao.UserDAO;
import com.niit.collaboration_backend.model.User;

import junit.framework.Assert;

public class UserTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@Autowired
	static User user;
	
	@Autowired
	static UserDAO userDAO;
	
	@BeforeClass
	public static void init() {
		context= new AnnotationConfigApplicationContext();
		context.scan("com.niit.collaboration_backend");
		context.refresh();
		
		user = (User)context.getBean(User.class);
		
		userDAO= (UserDAO)context.getBean(UserDAO.class);
		
	}
	
	@Test
	public void saveOrUpdateUserTestCase() {
		user.setFirstName("Gauresh");
		user.setLastName("Gosavi");
		user.setEmailId("gauresh123@gmail.com");
		user.setPassword("1234");
		user.setRole("ADMIN");
		user.setStatus("A");
		user.setIsOnline("Y");
		user.setUsername("Gau095");
		Assert.assertEquals(Boolean.valueOf(true), userDAO.saveOrUpdate(user));
	}
	
	@Test
	public void deleteTestCase(){
		user.setUserId(1);
		Assert.assertEquals(Boolean.valueOf(true), userDAO.delete(user));
	}
	
	@Test
	public void getByIdTestCase(){
		Assert.assertEquals(null, userDAO.getById(3));
	}
	
	@Test
	public void getByUsernameTestCase(){
		Assert.assertEquals(null, userDAO.getByUsername("D"));
	}
}
