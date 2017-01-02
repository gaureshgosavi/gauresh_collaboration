package com.niit.collaboration_backend.dao;

import java.util.List;

import com.niit.collaboration_backend.model.ForumComment;

public interface ForumCommentDAO {
	
	boolean saveOrUpdate(ForumComment forumComment);

	boolean delete(ForumComment forumComment);

	ForumComment get(int id);

	List<ForumComment> list();

}
