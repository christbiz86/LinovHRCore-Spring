package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.JobWorkingConditionDao;
import com.demo.model.JobWorkingCondition;

@Service
public class JobWorkingConditionService {
	
	@Autowired
	private JobWorkingConditionDao jwcDao;
	
	private Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void valIdExist(String id) throws Exception {
		if (!jwcDao.isIdExist(id)) {
			throw new Exception("Job Working Condition not found!");
		}
	}
	
	public void valIdNotNull(JobWorkingCondition jobWorkingCond) throws Exception {
		if (jobWorkingCond.getId() == null) {
			throw new Exception("Job Working Condition ID can't empty!");
		}
	}
	
	public List<JobWorkingCondition> findAll() {
		return jwcDao.findAll();
	}
	
	public JobWorkingCondition findById(String id) {
		return jwcDao.findById(id);
	}
	
	public void valNonBk(JobWorkingCondition jobWorkingCond) throws Exception {
		StringBuilder sb = new StringBuilder();
		int error = 0;
		
		if(jobWorkingCond.getDescription() == null) {
			sb.append("Description can't empty!\n");
			error++;
		}
		
		if(error > 0) {
			throw new Exception(sb.toString());
		}	
	}
	
	@Transactional
	public void insert(JobWorkingCondition jobWorkingCond) throws Exception {
		jobWorkingCond.setCreatedAt(getTime());
		valNonBk(jobWorkingCond);
		jwcDao.save(jobWorkingCond);
	}
	
	@Transactional
	public void update(JobWorkingCondition jobWorkingCond) throws Exception {
		jobWorkingCond.setUpdatedAt(getTime());
		valIdNotNull(jobWorkingCond);
		valIdExist(jobWorkingCond.getId());
		valNonBk(jobWorkingCond);
		jwcDao.save(jobWorkingCond);
	}
	
	@Transactional
	public void delete(String id) throws Exception {
		valIdExist(id);
		jwcDao.delete(id);
	}

}
