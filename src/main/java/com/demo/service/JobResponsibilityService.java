package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

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
		return jobRespDao.findById(id);
	}
	
	public void valNonBk(JobResponsibility jobResponsibility) throws Exception {
		StringBuilder sb = new StringBuilder();
		int error = 0;
		
		if(jobResponsibility.getDescription() == null) {
			sb.append("Description can't empty!\n");
			error++;
		}
		
		if(error > 0) {
			throw new Exception(sb.toString());
		}	
	}
	
	@Transactional
	public void insert(JobResponsibility jobResponsibility) throws Exception {
		jobResponsibility.setCreatedAt(getTime());
		valNonBk(jobResponsibility);
		jobRespDao.save(jobResponsibility);
	}
	
	@Transactional
	public void update(JobResponsibility jobResponsibility) throws Exception {
		jobResponsibility.setUpdatedAt(getTime());
		valIdNotNull(jobResponsibility);
		valIdExist(jobResponsibility.getId());
		valNonBk(jobResponsibility);
		jobRespDao.save(jobResponsibility);
	}
	
	@Transactional
	public void delete(String id) throws Exception {
		valIdExist(id);
		jobRespDao.delete(id);
	}

}
