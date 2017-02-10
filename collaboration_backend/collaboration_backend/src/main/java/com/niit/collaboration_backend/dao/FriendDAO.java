package com.niit.collaboration_backend.dao;

import java.util.List;

import com.niit.collaboration_backend.model.Friend;

public interface FriendDAO {

	boolean saveOrUpdate(Friend friend);

	boolean delete(Friend friend);

	Friend get(int userId, int friendId);

	List<Friend> list();
	
	List<Friend> getFriends(int userId);
	
	List<Friend> myFriends(int userId);
	
	List<Friend> getRequest(int userId);
	
	List<Friend> getTopFriends(int n);
	
	//List<Friend> friendList(int userId1, int userId2);
}
