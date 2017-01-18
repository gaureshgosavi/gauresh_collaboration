package com.niit.collaboration_backend.dao;

import java.util.List;

import com.niit.collaboration_backend.model.User;

public interface UserDAO {
	public Boolean saveOrUpdate(User user);

	public Boolean delete(User user);
	
	public User getByUsername(String username);
	
	public User getById(int userId);
	
	public List<User> list();
	
	public List<User> getUsersByStatus(String status);
	
	public User validate(User user);
}
