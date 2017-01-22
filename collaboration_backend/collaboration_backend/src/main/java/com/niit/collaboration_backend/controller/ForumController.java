package com.niit.collaboration_backend.controller;

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
import com.niit.collaboration_backend.model.Forum;

@RestController
public class ForumController {
	
	@Autowired
	Forum forum;
	
	@Autowired
	ForumDAO forumDAO;

	@RequestMapping(value = "/forum/list", method = RequestMethod.GET)
	public ResponseEntity<List<Forum>> listAllforums() {
		List<Forum> forums = forumDAO.list();
		if (forums.isEmpty()) {
			forum = new Forum();
			forum.setErrorCode("404");
			forum.setErrorMessage("No forums present.");
			forums.add(forum);
		}
		return new ResponseEntity<List<Forum>>(forums, HttpStatus.OK);
	}

	@RequestMapping(value = "/forum/get/{forumId}", method = RequestMethod.GET)
	public ResponseEntity<Forum> getforum(@PathVariable("forumId") int forumId) {
		System.out.println("Fetching forum");
		Forum forum = forumDAO.get(forumId);
		if (forum == null) {
			forum = new Forum();
			forum.setErrorCode("404");
			forum.setErrorMessage("forum does not exist.");
		}
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}

	@RequestMapping(value = "/forum/create", method = RequestMethod.POST)
	public ResponseEntity<Forum> createforum(@RequestBody Forum currentForum) {

			forum = new Forum();
			//DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
			Date date = new Date();
			currentForum.setCreatedDate(date);
			currentForum.setCategoryId(1);
			if(forumDAO.saveOrUpdate(currentForum) == false){
				forum = new Forum();
				forum.setErrorCode("404");
				forum.setErrorMessage("Failed to create forum. Please try again.");
			}
			else{
				forum.setErrorCode("200");
				forum.setErrorMessage("Forum created successfully successfully.");
			}
				
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}

	@RequestMapping(value = "/forum/get/{forumId}", method = RequestMethod.PUT)
	public ResponseEntity<Forum> updateforum(@PathVariable("forumId") int forumId, @RequestBody Forum updatedforum) {
		forum = forumDAO.get(forumId);
		if(forum == null){
			forum = new Forum();
			forum.setErrorCode("404");
			forum.setErrorMessage("Invalid forum");
		}
		else{
			
			forum.setStatus(updatedforum.getStatus());
			if(forumDAO.saveOrUpdate(forum) == false){
				forum = new Forum();
				forum.setErrorCode("404");
				forum.setErrorMessage("Failed to update forum.");
			}else{
				forum.setErrorCode("200");
				forum.setErrorMessage("forum updated successfully.");
			}
			
		}
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/forum/delete/{forumId}", method = RequestMethod.DELETE)
    public ResponseEntity<Forum> deleteforum(@PathVariable("forumId") int forumId) {
	 forum = forumDAO.get(forumId);
		if(forum == null){
			forum = new Forum();
			forum.setErrorCode("404");
			forum.setErrorMessage("Invalid forum");
		}
		else{
			if(forumDAO.delete(forum)){
				forum = new Forum();
				forum.setErrorCode("200");
				forum.setErrorMessage("forum deleted successfully.");
			}else{
				forum = new Forum();
				forum.setErrorCode("404");
				forum.setErrorMessage("Failed to delete forum.");
			}
		}
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
    }
}
