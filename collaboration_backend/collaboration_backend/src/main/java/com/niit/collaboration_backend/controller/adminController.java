package com.niit.collaboration_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration_backend.dao.BlogDAO;
import com.niit.collaboration_backend.dao.UserDAO;
import com.niit.collaboration_backend.model.Blog;
import com.niit.collaboration_backend.model.User;

@RestController
public class adminController {
	
	@Autowired
	Blog blog;
	
	@Autowired
	BlogDAO blogDAO;
	
	@Autowired
	User user;
	
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping(value = "/admin/blogs", method = RequestMethod.GET)
	public ResponseEntity<List<Blog>> listAllblogs() {
		List<Blog> blogs = blogDAO.getblogsByStatus("PENDING");
		if (blogs.isEmpty()) {
			blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("No blogs present.");
			blogs.add(blog);
		}
		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}

	@RequestMapping(value = "/admin/approveBlog/{blogId}", method = RequestMethod.PUT)
	public ResponseEntity<Blog> approveBlog(@PathVariable("blogId") int blogId) {
		blog = blogDAO.get(blogId);
		if(blog == null){
			blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("Invalid blog");
		}
		else{
			
			blog.setStatus("APPROVE");
			if(blogDAO.saveOrUpdate(blog) == false){
				blog = new Blog();
				blog.setErrorCode("404");
				blog.setErrorMessage("Failed to update blog.");
			}else{
				blog.setErrorCode("200");
				blog.setErrorMessage("blog updated successfully.");
			}
			
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/admin/disapproveBlog/{blogId}", method = RequestMethod.PUT)
	public ResponseEntity<Blog> disapproveBlog(@PathVariable("blogId") int blogId) {
		blog = blogDAO.get(blogId);
		if(blog == null){
			blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("Invalid blog");
		}
		else{
			
			blog.setStatus("REJECT");
			if(blogDAO.saveOrUpdate(blog) == false){
				blog = new Blog();
				blog.setErrorCode("404");
				blog.setErrorMessage("Failed to update blog.");
			}else{
				blog.setErrorCode("200");
				blog.setErrorMessage("blog updated successfully.");
			}
			
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllusers() {
		List<User> users = userDAO.getUsersByStatus("PENDING");
		if (users.isEmpty()) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("No users present.");
			users.add(user);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/admin/approveUser/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<User> approveUser(@PathVariable("userId") int userId) {
		user = userDAO.getById(userId);
		if(user == null){
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("Invalid user");
		}
		else{
			
			user.setStatus("APPROVE");
			if(userDAO.saveOrUpdate(user) == false){
				user = new User();
				user.setErrorCode("404");
				user.setErrorMessage("Failed to update user.");
			}else{
				user.setErrorCode("200");
				user.setErrorMessage("user updated successfully.");
			}
			
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/admin/disapproveUser/{userId}", method = RequestMethod.PUT)
	public ResponseEntity<User> disapproveUser(@PathVariable("userId") int userId) {
		user = userDAO.getById(userId);
		if(user == null){
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("Invalid user");
		}
		else{
			
			user.setStatus("REJECT");
			if(userDAO.saveOrUpdate(user) == false){
				user = new User();
				user.setErrorCode("404");
				user.setErrorMessage("Failed to update user.");
			}else{
				user.setErrorCode("200");
				user.setErrorMessage("user updated successfully.");
			}
			
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
