package com.niit.collaboration_backend.dao;

import java.util.List;

import com.niit.collaboration_backend.model.Blog;

public interface BlogDAO {

	boolean saveOrUpdate(Blog blog);

	boolean delete(Blog blog);

	Blog get(int blogId);

	List<Blog> list();
	
	public List<Blog> getblogsByStatus(String status);
}
