package com.niit.collaboration_backend.dao;

import java.util.List;

import com.niit.collaboration_backend.model.JobApplied;

public interface JobAppliedDAO {

	boolean saveOrUpdate(JobApplied jobApplied);

	boolean delete(JobApplied jobApplied);

	JobApplied get(int id);

	List<JobApplied> list();
}
