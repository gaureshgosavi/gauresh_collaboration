package com.niit.collaboration_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration_backend.dao.FriendDAO;
import com.niit.collaboration_backend.model.Forum;
import com.niit.collaboration_backend.model.Friend;



@RestController
public class FriendController {

	@Autowired
	Friend friend;
	
	@Autowired
	FriendDAO friendDAO;
	
	/*@RequestMapping(value = "/friend/list", method = RequestMethod.GET)
	public ResponseEntity<List<Friend>> listAllfriends() {
		
		return new ResponseEntity<List<Friend>>(friend, HttpStatus.OK);
	}*/
}
