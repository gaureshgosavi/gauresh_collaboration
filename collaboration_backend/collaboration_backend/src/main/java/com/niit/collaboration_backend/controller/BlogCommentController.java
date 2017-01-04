package com.niit.collaboration_backend.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration_backend.dao.BlogCommentDAO;
import com.niit.collaboration_backend.dao.BlogDAO;
import com.niit.collaboration_backend.model.Blog;
import com.niit.collaboration_backend.model.BlogComment;



@RestController
public class BlogCommentController {

	@Autowired
	BlogComment blogComment;
	
	@Autowired
	BlogCommentDAO blogCommentDAO;
	
	@Autowired
	Blog blog;
	
	@Autowired
	BlogDAO blogDAO;

	@RequestMapping(value = "/blogComment/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<BlogComment> getblogComment(@PathVariable("id") int id) {
		System.out.println("Fetching blogComment");
		BlogComment blogComment = blogCommentDAO.get(id);
		if (blogComment == null) {
			blogComment = new BlogComment();
			blogComment.setErrorCode("404");
			blogComment.setErrorMessage("blogComment does not exist.");
		}
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
	}

	@RequestMapping(value = "/blogComment/create", method = RequestMethod.POST)
	public ResponseEntity<BlogComment> createblogComment(@RequestBody BlogComment currentblogComment) {

			blogComment = new BlogComment();
			//DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
			Date date = new Date();
			blog = blogDAO.get(4);
			currentblogComment.setBlog(blog);
			currentblogComment.setCommentDate(date);
			if(blogCommentDAO.saveOrUpdate(currentblogComment) == false){
				blogComment = new BlogComment();
				blogComment.setErrorCode("404");
				blogComment.setErrorMessage("Failed to create blogComment. Please try again.");
			}
			else{
				blogComment.setErrorCode("200");
				blogComment.setErrorMessage("blogComment created successfully.");
			}
				
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
	}

	
	 @RequestMapping(value = "/blogComment/delete/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<BlogComment> deleteblogComment(@PathVariable("id") int id) {
		 blogComment = blogCommentDAO.get(id);
			if(blogComment == null){
				blogComment = new BlogComment();
				blogComment.setErrorCode("404");
				blogComment.setErrorMessage("Invalid blogComment");
			}
			else{
				if(blogCommentDAO.delete(blogComment)){
					blogComment = new BlogComment();
					blogComment.setErrorCode("200");
					blogComment.setErrorMessage("blogComment deleted successfully.");
				}else{
					blogComment = new BlogComment();
					blogComment.setErrorCode("404");
					blogComment.setErrorMessage("Failed to delete blogComment.");
				}
			}
			return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
	    }
}
