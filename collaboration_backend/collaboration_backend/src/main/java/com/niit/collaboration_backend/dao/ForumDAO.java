package com.niit.collaboration_backend.dao;

import java.util.List;

import com.niit.collaboration_backend.model.Forum;

public interface ForumDAO {
	
	boolean saveOrUpdate(Forum forum);

	boolean delete(Forum forumId);

	Forum get(int id);
	
	Forum getByName(String name);

	List<Forum> list();
	
	List<Forum> getForumsByStatus(String status);
	
	List<Forum> getForumsByUserId(int userId);
	
	List<Forum> getTopForums(int n);

}
