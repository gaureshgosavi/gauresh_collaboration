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

import com.niit.collaboration_backend.dao.BlogDAO;
import com.niit.collaboration_backend.dao.UserDAO;
import com.niit.collaboration_backend.model.Blog;
import com.niit.collaboration_backend.model.User;
import com.niit.collaboration_backend.model.blogListModel;

@RestController
public class BlogController {

	@Autowired
	Blog blog;
	
	@Autowired
	BlogDAO blogDAO;
	
	@Autowired
	User user;
	
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping(value = "/blog/list", method = RequestMethod.GET)
	public ResponseEntity<List<blogListModel>> listApprovedblogs() {
		List<Blog> blogs = blogDAO.getblogsByStatus("APPROVE");
		List<blogListModel> bloglist = new ArrayList<>();

		blogListModel blogModel = null;
		
		for (Blog b : blogs) {
			blogModel = new blogListModel();
			blogModel.setBlog(b);
			blogModel.setFirstName(userDAO.getById(b.getUserId()).getFirstName());
			blogModel.setLastname(userDAO.getById(b.getUserId()).getLastName());
			blogModel.setNoOfComments(b.getBlogComment().size());
			bloglist.add(blogModel);

		}
		
		if (bloglist.isEmpty()) {
			blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("No blogs present.");
			blogs.add(blog);
		}
		return new ResponseEntity<List<blogListModel>>(bloglist, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/blog/myBlogs/{userId}", method = RequestMethod.GET)
	public ResponseEntity<List<blogListModel>> listMyblogs(@PathVariable("userId") int userId) {
		List<Blog> blogs = blogDAO.getByUserId(userId);
		List<blogListModel> bloglist = new ArrayList<>();

		blogListModel blogModel = null;
		
		for (Blog b : blogs) {
			blogModel = new blogListModel();
			blogModel.setBlog(b);
			blogModel.setFirstName(userDAO.getById(b.getUserId()).getFirstName());
			blogModel.setLastname(userDAO.getById(b.getUserId()).getLastName());
			blogModel.setNoOfComments(b.getBlogComment().size());
			bloglist.add(blogModel);

		}
		
		if (bloglist.isEmpty()) {
			blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("No blogs present.");
			blogs.add(blog);
		}
		return new ResponseEntity<List<blogListModel>>(bloglist, HttpStatus.OK);
	}

	@RequestMapping(value = "/blog/get/{blogId}", method = RequestMethod.GET)
	public ResponseEntity<blogListModel> getblog(@PathVariable("blogId") int blogId) {
		System.out.println("Fetching blog");
		
		blogListModel blogModel = new blogListModel();
		blog = blogDAO.get(blogId);
		blogModel.setBlog(blog);
		blogModel.setFirstName(userDAO.getById(blog.getUserId()).getFirstName());
		blogModel.setLastname(userDAO.getById(blog.getUserId()).getLastName());
		blogModel.setNoOfComments(blog.getBlogComment().size());

		if (blog == null) {
			blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("blog does not exist.");
		}
		return new ResponseEntity<blogListModel>(blogModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/blog/create", method = RequestMethod.POST)
	public ResponseEntity<Blog> createblog(@RequestBody Blog currentBlog) {

			blog = new Blog();
			//DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
			Date date = new Date();
			currentBlog.setPostDate(date);
			currentBlog.setStatus("PENDING");
			currentBlog.setLikes(0);
			if(blogDAO.saveOrUpdate(currentBlog) == false){
				blog = new Blog();
				blog.setErrorCode("404");
				blog.setErrorMessage("Failed to create blog. Please try again.");
			}
			else{
				blog.setErrorCode("200");
				blog.setErrorMessage("Blog created successfully.");
			}
				
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/blog/like/{blogId}", method = RequestMethod.PUT)
	public ResponseEntity<Blog> approveBlog(@PathVariable("blogId") int blogId) {
		blog = blogDAO.get(blogId);
		if(blog == null){
			blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("Invalid blog");
		}
		else{
			
			blog.setLikes(blog.getLikes()+1);
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

	@RequestMapping(value = "/blog/get/{blogId}", method = RequestMethod.PUT)
	public ResponseEntity<Blog> updateblog(@PathVariable("blogId") int blogId, @RequestBody Blog updatedblog) {
		blog = blogDAO.get(blogId);
		if(blog == null){
			blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("Invalid blog");
		}
		else{
			
			blog.setLikes(updatedblog.getLikes());
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
	
	 @RequestMapping(value = "/blog/delete/{blogId}", method = RequestMethod.DELETE)
	    public ResponseEntity<Blog> deleteBlog(@PathVariable("blogId") int blogId) {
		 blog = blogDAO.get(blogId);
			if(blog == null){
				blog = new Blog();
				blog.setErrorCode("404");
				blog.setErrorMessage("Invalid blog");
			}
			else{
				if(blogDAO.delete(blog)){
					blog = new Blog();
					blog.setErrorCode("200");
					blog.setErrorMessage("blog deleted successfully.");
				}else{
					blog = new Blog();
					blog.setErrorCode("404");
					blog.setErrorMessage("Failed to delete blog.");
				}
			}
			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	    }
	 
	 @RequestMapping(value = "/latestBlogs", method = RequestMethod.GET)
		public ResponseEntity<List<blogListModel>> listLatestblogs() {
			List<Blog> blogs = blogDAO.getTopBlogs(3);
			List<blogListModel> bloglist = new ArrayList<>();

			blogListModel blogModel = null;
			
			for (Blog b : blogs) {
				blogModel = new blogListModel();
				blogModel.setBlog(b);
				blogModel.setFirstName(userDAO.getById(b.getUserId()).getFirstName());
				blogModel.setLastname(userDAO.getById(b.getUserId()).getLastName());
				blogModel.setNoOfComments(b.getBlogComment().size());
				bloglist.add(blogModel);

			}
			
			if (bloglist.isEmpty()) {
				blog = new Blog();
				blog.setErrorCode("404");
				blog.setErrorMessage("No blogs present.");
				blogs.add(blog);
			}
			return new ResponseEntity<List<blogListModel>>(bloglist, HttpStatus.OK);
		}
	 
}
