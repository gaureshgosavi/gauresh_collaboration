package com.niit.collaboration_backend.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration_backend.dao.UserDAO;
import com.niit.collaboration_backend.model.BlogComment;
import com.niit.collaboration_backend.model.User;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	User user;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public Boolean saveOrUpdate(User user) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(user);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}

	@Override
	@Transactional
	public Boolean delete(User user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}

	@Override
	@Transactional
	public User getByUsername(String username) {
		String hql="FROM User WHERE username = :username";
		Query query =(Query) sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("username", username);
		try {
			return (User) query.getSingleResult();	
		}
		catch(Exception ex) {
			return null;
		}
	}

	@Override
	@Transactional
	public User getById(int userId) {
		return (User) sessionFactory.getCurrentSession().get(User.class, userId);
	}
	
	@Transactional
	public List<User> list() {
		String hql = "from User";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@Transactional
	public User validate(User user){
		String username = user.getUsername();
		String password = user.getPassword();
		String hql = "from User where username = '" +username+"' and password = '"+password+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		try {
			user = (User) query.getSingleResult();
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	@Transactional
	public List<User> getUsersByStatus(String status) {
		String hql = "from User where status = '"+status+"'";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@Override
	@Transactional
	public boolean updateUserProfileId(String fileName, int userId) {
		String updateQuery = "UPDATE User SET profileId = :fileName WHERE userId = :userId";
		Query query = sessionFactory.getCurrentSession().createQuery(updateQuery);
		query.setParameter("userId", userId);
		query.setParameter("fileName", fileName);
		try {
			query.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}	
		return false;
	}
}
