package com.niit.collaboration_backend.dao;

import java.util.List;

import com.niit.collaboration_backend.model.ForumCategory;

public interface ForumCategoryDAO {

	boolean saveOrUpdate(ForumCategory forumCategory);

	boolean delete(ForumCategory iorumCategory);

	ForumCategory get(int id);

	List<ForumCategory> list();

}
