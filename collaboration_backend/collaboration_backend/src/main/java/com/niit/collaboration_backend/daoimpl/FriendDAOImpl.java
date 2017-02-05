package com.niit.collaboration_backend.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration_backend.dao.FriendDAO;
import com.niit.collaboration_backend.model.Friend;

@Repository("FriendDAO")
public class FriendDAOImpl implements FriendDAO{

	@Autowired
	SessionFactory sessionFactory;

	public FriendDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean saveOrUpdate(Friend friend) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(friend);
			return true;
		} catch (HibernateException e) {
			return false;
		}

	}

	@Transactional
	public boolean delete(Friend friend) {
		try {
			sessionFactory.getCurrentSession().delete(friend);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}

	@Transactional
	public Friend get(int userId, int friendId) {
		String hql = "from Friend where userId = "+userId+" and friendId = "+friendId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		try {
			return (Friend) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional
	public List<Friend> list() {
		String hql = "from Friend";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Override
	@Transactional
	public List<Friend> getFriends(int userId) { 
		String hql = "from Friend where userId = "+userId+" and status = ACCEPT";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@Override
	@Transactional
	public List<Friend> myFriends(int userId) {
		String hql = "from Friend where friendId = "+userId+" and status = ACCEPT";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@Override
	@Transactional
	public List<Friend> getRequest(int userId) {
		String hql = "from Friend where friendId = "+userId+" and status = PENDING";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	/*@Override
	@Transactional
	public List<Friend> friendList(int userId1, int userId2) {
		String hql = "userId1, userId2 FROM Friend WHERE userId1 = "+userId1+" OR userId2 = "+userId2+" AND status = 'ACCEPTED'";
		List<Friend> friends = sessionFactory.getCurrentSession().createQuery(hql).list();
		friends.get(userId2);
		
	}*/
}
