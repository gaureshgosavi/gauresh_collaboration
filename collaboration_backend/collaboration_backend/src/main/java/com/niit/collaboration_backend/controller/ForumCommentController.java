package com.niit.collaboration_backend.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.collaboration_backend.dao.ForumCommentDAO;
import com.niit.collaboration_backend.dao.ForumDAO;
import com.niit.collaboration_backend.model.Forum;
import com.niit.collaboration_backend.model.ForumComment;



public class ForumCommentController {

	
	@Autowired
	ForumComment forumComment;
	
	@Autowired
	ForumCommentDAO forumCommentDAO;
	
	@Autowired
	Forum forum;
	
	@Autowired
	ForumDAO forumDAO;

	@RequestMapping(value = "/forumComment/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<ForumComment> getforumComment(@PathVariable("id") int id) {
		System.out.println("Fetching forumComment");
		ForumComment forumComment = forumCommentDAO.get(id);
		if (forumComment == null) {
			forumComment = new ForumComment();
			forumComment.setErrorCode("404");
			forumComment.setErrorMessage("forumComment does not exist.");
		}
		return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
	}

	@RequestMapping(value = "/forumComment/create", method = RequestMethod.POST)
	public ResponseEntity<ForumComment> createforumComment(@RequestBody ForumComment currentforumComment) {

			forumComment = new ForumComment();
			//DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
			Date date = new Date();
			forum = forumDAO.get(4);
			currentforumComment.setForum(forum);
			currentforumComment.setCommentDate(date);
			if(forumCommentDAO.saveOrUpdate(currentforumComment) == false){
				forumComment = new ForumComment();
				forumComment.setErrorCode("404");
				forumComment.setErrorMessage("Failed to create forumComment. Please try again.");
			}
			else{
				forumComment.setErrorCode("200");
				forumComment.setErrorMessage("forumComment created successfully.");
			}
				
		return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
	}

	
	 @RequestMapping(value = "/forumComment/delete/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<ForumComment> deleteforumComment(@PathVariable("id") int id) {
		 forumComment = forumCommentDAO.get(id);
			if(forumComment == null){
				forumComment = new ForumComment();
				forumComment.setErrorCode("404");
				forumComment.setErrorMessage("Invalid forumComment");
			}
			else{
				if(forumCommentDAO.delete(forumComment)){
					forumComment = new ForumComment();
					forumComment.setErrorCode("200");
					forumComment.setErrorMessage("forumComment deleted successfully.");
				}else{
					forumComment = new ForumComment();
					forumComment.setErrorCode("404");
					forumComment.setErrorMessage("Failed to delete forumComment.");
				}
			}
			return new ResponseEntity<ForumComment>(forumComment, HttpStatus.OK);
	    }
}
