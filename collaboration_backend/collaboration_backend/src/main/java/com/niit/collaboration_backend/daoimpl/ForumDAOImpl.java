package com.niit.collaboration_backend.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration_backend.dao.ForumDAO;
import com.niit.collaboration_backend.model.Blog;
import com.niit.collaboration_backend.model.Forum;
import com.niit.collaboration_backend.model.ForumRequest;
import com.niit.collaboration_backend.model.User;

@Repository("ForumDAO")
public class ForumDAOImpl implements ForumDAO {

	@Autowired
	SessionFactory sessionFactory;

	public ForumDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public boolean saveOrUpdate(Forum forum) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(forum);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean delete(Forum forum) {
		try {
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}

	@Override
	@Transactional
	public Forum get(int forumId) {
		return (Forum) sessionFactory.getCurrentSession().get(Forum.class, forumId);
	}

	@Transactional
	public List<Forum> list() {
		String hql = "from Forum";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Override
	@Transactional
	public List<Forum> getForumsByStatus(String status) {
		String hql = "from Forum where status = '" + status + "'";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Override
	@Transactional
	public List<Forum> getForumsByUserId(int userId) {
		String hql = "from Forum where userId = '" + userId + "'";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Override
	@Transactional
	public Forum getByName(String name) {
		String hql = "from Forum where name = '" + name + "'";
		Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
		try {
			return (Forum) query.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	@Transactional
	public List<Forum> getTopForums(int n) {
		String hql = "FROM Forum WHERE status = 'APPROVE' ORDER BY createdDate DESC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(1);
		query.setMaxResults(n);
		return query.getResultList();
	}
}
