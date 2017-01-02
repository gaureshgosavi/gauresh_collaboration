package com.niit.collaboration_backend.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration_backend.dao.ForumCommentDAO;
import com.niit.collaboration_backend.model.ForumComment;

@Repository("ForumCommentDAO")
public class ForumCommentDAOImpl implements ForumCommentDAO{
	
	@Autowired
	SessionFactory sessionFactory;

	public ForumCommentDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean saveOrUpdate(ForumComment forumComment) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(forumComment);
			return true;
		} catch (HibernateException e) {
			return false;
		}

	}

	@Transactional
	public boolean delete(ForumComment forumComment) {
		try {
			sessionFactory.getCurrentSession().delete(forumComment);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}

	@Transactional
	public ForumComment get(int id) {
		String hql = "from ForumComment where id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", id);
		try {
			return (ForumComment) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional
	public List<ForumComment> list() {
		String hql = "from ForumComment";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

}
