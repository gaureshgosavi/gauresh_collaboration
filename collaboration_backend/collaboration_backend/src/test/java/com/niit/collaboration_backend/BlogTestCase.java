package com.niit.collaboration_backend;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration_backend.dao.BlogDAO;
import com.niit.collaboration_backend.model.Blog;

import junit.framework.Assert;

public class BlogTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static Blog blog;

	@Autowired
	static BlogDAO blogDAO;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collaboration_backend");
		context.refresh();

		blog = (Blog) context.getBean(Blog.class);

		blogDAO = (BlogDAO) context.getBean(BlogDAO.class);

	}

	@Test
	public void saveOrUpdateUserTestCase() {
		blog.setTitle("programming");
		blog.setDescription("dummy blog");
		blog.setLikes(3);
		Date date = new Date();
		long time = date.getTime();
		Timestamp timestamp = new Timestamp(time);
		blog.setPostDate(timestamp);
		blog.setUserId(3);
		blog.setStatus("Approved");
		Assert.assertEquals(true, blogDAO.saveOrUpdate(blog));
	}

	@Test
	public void deleteTestCase() {
		blog.setBlogId(2);
		Assert.assertEquals(true, blogDAO.delete(blog));
	}

	@Test
	public void getByIdTestCase() {
		Assert.assertEquals(null, blogDAO.get(3));
	}
	
	@Test
	public void getAllEventsTestCase() {
		
		Assert.assertEquals(0, blogDAO.list().size());
	}

}
