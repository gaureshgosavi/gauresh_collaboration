package com.niit.collaboration_backend.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration_backend.dao.EventDAO;
import com.niit.collaboration_backend.model.Event;

@Repository("eventDAO")
public class EventDAOImpl implements EventDAO{

	@Autowired
	SessionFactory sessionFactory;

	public EventDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean saveOrUpdate(Event event) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(event);
			return true;
		} catch (HibernateException e) {
			return false;
		}

	}

	@Transactional
	public boolean delete(Event event) {
		try {
			sessionFactory.getCurrentSession().delete(event);
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}
	
	@Transactional
	public Event get(int id) {
		String hql = "from Event where id=:id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("id", id);
		try {
			return (Event) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional
	public List<Event> list() {
		String hql = "from Event";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
}
