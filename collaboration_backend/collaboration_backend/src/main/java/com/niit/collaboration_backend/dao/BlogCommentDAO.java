package com.niit.collaboration_backend.dao;

import java.util.List;

import com.niit.collaboration_backend.model.BlogComment;

public interface BlogCommentDAO {

	boolean saveOrUpdate(BlogComment blogCommet);

	boolean delete(BlogComment blogComment);

	BlogComment get(int id);

	List<BlogComment> list();
}
