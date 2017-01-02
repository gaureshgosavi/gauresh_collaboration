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
	public Friend get(int id) {
		String hql = "from Friend where id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", id);
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
}
