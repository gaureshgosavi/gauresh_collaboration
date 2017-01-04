package com.niit.collaboration_backend.dao;

import java.util.List;

import com.niit.collaboration_backend.model.Friend;

public interface FriendDAO {

	boolean saveOrUpdate(Friend Friend);

	boolean delete(Friend Friend);

	Friend get(int id);

	List<Friend> list();
	
	//List<Friend> friendList(int userId1, int userId2);
}
