package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.JobResponsibilityDao;
import com.demo.exception.ValidationException;
import com.demo.model.JobResponsibility;

@Service
public class JobResponsibilityService {
	
	@Autowired
	private JobResponsibilityDao jobRespDao;
	
	private Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void valIdExist(String id) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if (!jobRespDao.isIdExist(id)) {
			listErr.add("Job Responsibility not found!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valIdNotNull(JobResponsibility jobResponsibility) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if (jobResponsibility.getId() == null) {
			listErr.add("Job Responsibility ID can't empty!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public List<JobResponsibility> findAll() {
		return jobRespDao.findAll();
	}
	
	public JobResponsibility findById(String id) {
		return jobRespDao.findOne(id);
	}
	
	public void valBkNotNull(JobResponsibility jobResponsibility) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(jobResponsibility.getJob().getId().isEmpty()) {
			listErr.add("Job can't empty!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valBkNotExist(JobResponsibility jobResponsibility) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(jobRespDao.isBkExist(jobResponsibility.getJob().getId())) {
			listErr.add("Job already exists!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valBkNotChange(JobResponsibility jobResponsibility) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		String job = findById(jobResponsibility.getId()).getJob().getId();
		
		if(!jobResponsibility.getJob().getId().equals(job)) {
			listErr.add("BK can't be changed!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valNonBk(JobResponsibility jobResponsibility) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(jobResponsibility.getCreatedBy().isEmpty()) {
			listErr.add("Created by can't empty!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valCreatedAtNotChange(JobResponsibility jobResponsibility) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		Timestamp createdAt = findById(jobResponsibility.getId()).getCreatedAt();
		if(!jobResponsibility.getCreatedAt().equals(createdAt)) {
			listErr.add("Created at can't be changed!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
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
