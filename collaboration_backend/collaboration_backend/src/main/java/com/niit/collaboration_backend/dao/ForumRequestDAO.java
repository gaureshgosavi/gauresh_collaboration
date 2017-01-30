package com.niit.collaboration_backend.dao;

import java.util.List;

import com.niit.collaboration_backend.model.ForumRequest;

public interface ForumRequestDAO {

	boolean saveOrUpdate(ForumRequest forumRequest);
	
	public List<ForumRequest> getByStatus(String status, int forumId);
	
	ForumRequest getByUserId(int userId);
	
	ForumRequest get(int userId, int forumId);
}
