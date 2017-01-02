package com.niit.collaboration_backend.dao;

import java.util.List;

import com.niit.collaboration_backend.model.Event;

public interface EventDAO {

	boolean saveOrUpdate(Event event);

	boolean delete(Event event);

	Event get(int id);

	List<Event> list();
}
