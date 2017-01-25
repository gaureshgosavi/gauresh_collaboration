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
		List<ForumRequestModel> requestlist = new ArrayList<>();

		ForumRequestModel requestModel = null;
		
		for (ForumRequest f : requests) {
			requestModel = new ForumRequestModel();
			requestModel.setForum(forumDAO.get(f.getForumId()));
			requestModel.setUser(userDAO.getById(f.getUserId()));
			requestlist.add(requestModel);

		}
		
		
		if (requestlist.isEmpty()) {
			forumRequest = new ForumRequest();
			forumRequest.setErrorCode("404");
			forumRequest.setErrorMessage("No blogs present.");
			requests.add(forumRequest);
		}
		return new ResponseEntity<List<ForumRequestModel>>(requestlist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/forum/approvedRequest/{forumId}", method = RequestMethod.GET)
	public ResponseEntity<List<ForumRequestModel>> listApprovedRequest(@PathVariable("forumId") int forumId) {
		List<ForumRequest> requests = forumRequestDAO.getByStatus("APPROVE", forumId);
		List<ForumRequestModel> requestlist = new ArrayList<>();

		ForumRequestModel requestModel = null;
		
		for (ForumRequest f : requests) {
			requestModel = new ForumRequestModel();
			requestModel.setForum(forumDAO.get(f.getForumId()));
			requestModel.setUser(userDAO.getById(f.getUserId()));
			requestlist.add(requestModel);

		}
		
		if (requestlist.isEmpty()) {
			forumRequest = new ForumRequest();
			forumRequest.setErrorCode("404");
			forumRequest.setErrorMessage("No blogs present.");
			requests.add(forumRequest);
		}
		return new ResponseEntity<List<ForumRequestModel>>(requestlist, HttpStatus.OK);
	}
}
