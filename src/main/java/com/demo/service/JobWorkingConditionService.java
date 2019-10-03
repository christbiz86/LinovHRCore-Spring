package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.JobWorkingConditionDao;
import com.demo.exception.ValidationException;
import com.demo.model.JobWorkingCondition;

@Service
public class JobWorkingConditionService {
	
	@Autowired
	private JobWorkingConditionDao jwcDao;
	
	private Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void valIdExist(String id) throws Exception {
		ArrayList<String> listErr = new ArrayList<String>();
		
		if(!jwcDao.isIdExist(id)) {
			listErr.add("Job Working Condition not found!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valIdNotNull(JobWorkingCondition jobWorkingCond) throws Exception {
		ArrayList<String> listErr = new ArrayList<String>();
		
		if(jobWorkingCond.getId().isEmpty()) {
			listErr.add("Job Working Condition ID can't empty!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public List<JobWorkingCondition> findAll() {
		return jwcDao.findAll();
	}
	
	public JobWorkingCondition findById(String id) {
		return jwcDao.findOne(id);
	}
	
	public void valBkNotNull(JobWorkingCondition jobWorkingCond) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(jobWorkingCond.getJob().getId().isEmpty()) {
			listErr.add("Job can't empty!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valBkNotExist(JobWorkingCondition jobWorkingCond) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(jwcDao.isBkExist(jobWorkingCond.getJob().getId())) {
			listErr.add("Job already exists!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valBkNotChange(JobWorkingCondition jobWorkingCond) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		String job = findById(jobWorkingCond.getId()).getJob().getId();
		
		if(!jobWorkingCond.getJob().getId().equals(job)) {
			listErr.add("BK can't be changed!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valNonBk(JobWorkingCondition jobWorkingCond) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(jobWorkingCond.getCreatedBy().isEmpty()) {
			listErr.add("Created by can't empty!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valCreatedAtNotChange(JobWorkingCondition jobWorkingCond) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		Timestamp createdAt = findById(jobWorkingCond.getId()).getCreatedAt();
		if(!jobWorkingCond.getCreatedAt().equals(createdAt)) {
			listErr.add("Created at can't be changed!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void insert(JobWorkingCondition jobWorkingCond) throws Exception {
		jobWorkingCond.setCreatedAt(getTime());
		valBkNotNull(jobWorkingCond);
		valBkNotExist(jobWorkingCond);
		valNonBk(jobWorkingCond);
		jwcDao.create(jobWorkingCond);
	}
	
	public void update(JobWorkingCondition jobWorkingCond) throws Exception {
		jobWorkingCond.setUpdatedAt(getTime());
		valIdNotNull(jobWorkingCond);
		valIdExist(jobWorkingCond.getId());
		valBkNotNull(jobWorkingCond);
		valBkNotChange(jobWorkingCond);
		valNonBk(jobWorkingCond);
		valCreatedAtNotChange(jobWorkingCond);
		jwcDao.update(jobWorkingCond);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		jwcDao.deleteById(id);
	}

}
