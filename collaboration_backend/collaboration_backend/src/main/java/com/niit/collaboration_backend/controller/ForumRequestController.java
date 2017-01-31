package com.niit.collaboration_backend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration_backend.dao.ForumDAO;
import com.niit.collaboration_backend.dao.ForumRequestDAO;
import com.niit.collaboration_backend.dao.UserDAO;
import com.niit.collaboration_backend.model.Blog;
import com.niit.collaboration_backend.model.Forum;
import com.niit.collaboration_backend.model.ForumRequest;
import com.niit.collaboration_backend.model.ForumRequestModel;
import com.niit.collaboration_backend.model.User;

@RestController
public class ForumRequestController {
	
	@Autowired
	ForumRequest forumRequest;
	
	@Autowired
	ForumRequestDAO forumRequestDAO;
	
	@Autowired 
	ForumDAO forumDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	User user;

	@RequestMapping(value = "/forum/request", method = RequestMethod.POST)
	public ResponseEntity<ForumRequest> createRequest(@RequestBody ForumRequest request) {
		
		forumRequest = forumRequestDAO.getByUserId(request.getUserId());
		
		if(forumRequest == null){
			//DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
			Date date = new Date();
			request.setStatus("PENDING");
			if(forumRequestDAO.saveOrUpdate(request) == false){
				forumRequest = new ForumRequest();
				forumRequest.setErrorCode("404");
				forumRequest.setErrorMessage("Failed to make a request. Please try again.");
			}
			else{
				forumRequest = forumRequestDAO.getByUserId(request.getUserId());
				forumRequest.setErrorCode("200");
				forumRequest.setErrorMessage("request created successfully.");
			}
		}else{
			forumRequest = new ForumRequest();
			forumRequest.setErrorCode("404");
			forumRequest.setErrorMessage("You already sent a request.");
		}
				
		return new ResponseEntity<ForumRequest>(forumRequest, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/forum/pendingRequest/{forumId}", method = RequestMethod.GET)
	public ResponseEntity<List<ForumRequestModel>> listPendingRequest(@PathVariable("forumId") int forumId) {
		List<ForumRequest> requests = forumRequestDAO.getByStatus("PENDING", forumId);
		List<ForumRequestModel> forumRequestList = new ArrayList<>();

		ForumRequestModel forumRequestModel = null;
		
		for (ForumRequest f : requests) {
			forumRequestModel = new ForumRequestModel();
			forumRequestModel.setUser(userDAO.getById(f.getUserId()));
			forumRequestModel.setForum(forumDAO.get(f.getUserId()));
			forumRequestList.add(forumRequestModel);

		}
		
		
		if (forumRequestList.isEmpty()) {
			forumRequestModel = new ForumRequestModel();
			forumRequestModel.setErrorCode("404");
			forumRequestModel.setErrorMessage("No blogs present.");
			forumRequestList.add(forumRequestModel);
		}
		return new ResponseEntity<List<ForumRequestModel>>(forumRequestList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/forum/approvedRequest/{forumId}", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listForumMembers(@PathVariable("forumId") int forumId) {
		List<ForumRequest> requests = forumRequestDAO.getByStatus("APPROVE", forumId);
		List<User> userlist = new ArrayList<>();

		User user = null;
		
		for (ForumRequest f : requests) {
			user = userDAO.getById(f.getUserId());
			userlist.add(user);

		}
		
		
		if (userlist.isEmpty()) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("No blogs present.");
			userlist.add(user);
		}
		return new ResponseEntity<List<User>>(userlist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/forum/approveMember/{forumId}", method = RequestMethod.PUT)
	public ResponseEntity<User> approveBlog(@PathVariable("forumId") int forumId, @RequestBody User userRequest) {
		forumRequest = forumRequestDAO.get(userRequest.getUserId(), forumId);
		forumRequest.setStatus("APPROVE");
			if(forumRequestDAO.saveOrUpdate(forumRequest) == false){
				user = new User();
				user.setErrorCode("404");
				user.setErrorMessage("Failed to approve blog.");
			}else{
				user = userDAO.getById(userRequest.getUserId());
				user.setErrorCode("200");
				user.setErrorMessage("user approved successfully.");
			}
			
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
