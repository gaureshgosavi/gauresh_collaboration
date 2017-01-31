package com.niit.collaboration_backend.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration_backend.dao.BlogDAO;
import com.niit.collaboration_backend.model.Blog;

@Repository("blogDAO")
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	SessionFactory sessionFactory;

	public BlogDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean saveOrUpdate(Blog blog) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(blog);
			return true;
		} catch (HibernateException e) {
			return false;
		}

	}

	@Transactional
	public boolean delete(Blog blog) {
		try {
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}

	@Transactional
	public Blog get(int blogId) {
		String hql = "from Blog where blogId = :blogId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("blogId", blogId);
		try {
			return (Blog) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional
	public List<Blog> list() {
		String hql = "from Blog";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@Override
	@Transactional
	public List<Blog> getblogsByStatus(String status) {
		String hql = "from Blog where status = '"+status+"'";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

	@Override
	@Transactional
	public List<Blog> getByUserId(int userId) {
		String hql = "from Blog where userId = '"+userId+"' and status = 'APPROVE'";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	

}
