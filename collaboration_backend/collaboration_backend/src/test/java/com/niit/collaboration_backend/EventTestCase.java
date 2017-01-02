package com.niit.collaboration_backend;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration_backend.dao.EventDAO;
import com.niit.collaboration_backend.model.Event;

import junit.framework.Assert;

public class EventTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static Event event;

	@Autowired
	static EventDAO eventDAO;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.collaboration_backend");
		context.refresh();

		event = (Event) context.getBean(Event.class);

		eventDAO = (EventDAO) context.getBean(EventDAO.class);

	}

	@Test
	public void saveOrUpdateUserTestCase() {
		event.setName("New Year");
		event.setDescription("Dummy event");
		event.setVenue("NIIT");
		event.setMembers(2);
		Date date = new Date();
		long time = date.getTime();
		//DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm a");
		Timestamp timestamp = new Timestamp(time);
		event.setPostDate(timestamp);
		event.setStartDate(timestamp);
		event.setStartTime(timestamp);
		Assert.assertEquals(true, eventDAO.saveOrUpdate(event));
	}

	@Test
	public void deleteTestCase() {
		event.setId(1);
		Assert.assertEquals(true, eventDAO.delete(event));
	}

	@Test
	public void getByIdTestCase() {
		Assert.assertEquals(null, eventDAO.get(1));
	}
	
	@Test
	public void getAllEventsTestCase() {
		
		Assert.assertEquals(1, eventDAO.list().size());
	}
}
