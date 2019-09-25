package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.JobResponsibilityDao;
import com.demo.model.JobResponsibility;

@Service
public class JobResponsibilityService {
	
	@Autowired
	private JobResponsibilityDao jobRespDao;
	
	private Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void valIdExist(String id) throws Exception {
		if (!jobRespDao.isIdExist(id)) {
			throw new Exception("Job Responsibility not found!");
		}
	}
	
	public void valIdNotNull(JobResponsibility jobResponsibility) throws Exception {
		if (jobResponsibility.getId() == null) {
			throw new Exception("Job Responsibility ID can't empty!");
		}
	}
	
	public List<JobResponsibility> findAll() {
		return jobRespDao.findAll();
	}
	
	public JobResponsibility findById(String id) {
		return jobRespDao.findOne(id);
	}
	
	public void valBkNotNull(JobResponsibility jobResponsibility) throws Exception {
		if(jobResponsibility.getJob().getId().isEmpty()) {
			throw new Exception("Job can't empty!");
		}
	}
	
	public void valBkNotExist(JobResponsibility jobResponsibility) throws Exception {
		if(jobRespDao.isBkExist(jobResponsibility.getJob().getId())) {
			throw new Exception("Job already exists!");
		}
	}
	
	public void valBkNotChange(JobResponsibility jobResponsibility) throws Exception {
		String job = findById(jobResponsibility.getId()).getJob().getId();
		if(!jobResponsibility.getJob().getId().equals(job)) {
			throw new Exception("BK can't be changed!");
		}
	}
	
	public void valNonBk(JobResponsibility jobResponsibility) throws Exception {
		if(jobResponsibility.getCreatedBy().isEmpty()) {
			throw new Exception("Created by can't empty!");
		}
	}
	
	public void valCreatedAtNotChange(JobResponsibility jobResponsibility) throws Exception {
		Timestamp createdAt = findById(jobResponsibility.getId()).getCreatedAt();
		if(!jobResponsibility.getCreatedAt().equals(createdAt)) {
			throw new Exception("Created at can't be changed!");
		}
	}
	
	public void insert(JobResponsibility jobResponsibility) throws Exception {
		jobResponsibility.setCreatedAt(getTime());
		valBkNotNull(jobResponsibility);
		valBkNotExist(jobResponsibility);
		valNonBk(jobResponsibility);
		jobRespDao.create(jobResponsibility);
	}
	
	public void update(JobResponsibility jobResponsibility) throws Exception {
		jobResponsibility.setUpdatedAt(getTime());
		valIdNotNull(jobResponsibility);
		valIdExist(jobResponsibility.getId());
		valBkNotNull(jobResponsibility);
		valBkNotChange(jobResponsibility);
		valNonBk(jobResponsibility);
		valCreatedAtNotChange(jobResponsibility);
		jobRespDao.update(jobResponsibility);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		jobRespDao.deleteById(id);
	}

}
