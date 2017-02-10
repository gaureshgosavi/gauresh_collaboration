package com.niit.collaboration_backend.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration_backend.dao.ForumRequestDAO;
import com.niit.collaboration_backend.model.ForumRequest;
import com.niit.collaboration_backend.model.User;

@Repository("forumRequestDAO")
public class ForumRequestDAOImpl implements ForumRequestDAO{
	
	@Autowired
	SessionFactory sessionFactory;

	public ForumRequestDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public boolean saveOrUpdate(ForumRequest forumRequest) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(forumRequest);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}

	@Override
	@Transactional
	public List<ForumRequest> getByStatus(String status, int forumId) {
		String hql = "from ForumRequest where forumId = '"+forumId+"' and status = '"+status+"'";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}


	@Override
	@Transactional
	public ForumRequest get(int userId, int forumId) {
		String hql = "from ForumRequest where forumId = '"+forumId+"' and userId = '"+userId+"'";
		Query query =(Query) sessionFactory.getCurrentSession().createQuery(hql);
		try {
			return (ForumRequest) query.getSingleResult();	
		}
		catch(Exception ex) {
			return null;
		}
	}

	@Override
	@Transactional
	public List<ForumRequest> getByUserStatus(int userId) {
		String hql = "from ForumRequest where userId = '"+userId+"' and status = 'APPROVE'";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

}
