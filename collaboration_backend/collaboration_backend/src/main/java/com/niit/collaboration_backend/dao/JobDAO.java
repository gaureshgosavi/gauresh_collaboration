package com.niit.collaboration_backend.dao;

import java.util.List;

import com.niit.collaboration_backend.model.Job;

public interface JobDAO {

	boolean saveOrUpdate(Job job);

	boolean delete(Job job);

	Job get(int jobId);

	List<Job> list();
	
	Job getByJobProfile(String jobProfile);
}
