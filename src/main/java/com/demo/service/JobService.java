package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.JobDao;
import com.demo.exception.ValidationException;
import com.demo.model.Job;

@Service
public class JobService {
	
	@Autowired
	private JobDao jobDao;
	
	private Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void valIdExist(String id) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(!jobDao.isIdExist(id)) {
			listErr.add("Job not found!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valIdNotNull(Job job) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(job.getId().isEmpty()) {
			listErr.add("Job ID can't empty!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public List<Job> findAll() {
		return jobDao.findAll();
	}
	
	public Job findById(String id) {
		return jobDao.findOne(id);
	}
	
	public Job findByCode(String code) {
		return jobDao.findByCode(code);
	}
	
	public void valBkNotNull(Job job) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(job.getCompany().getId().isEmpty()) {
			listErr.add("Company can't empty!");
		}
		if(job.getCode().isEmpty()) {
			listErr.add("Code can't empty!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valBkNotExist(Job job) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(jobDao.isBkExist(job.getCode(), job.getCompany().getId())) {
			listErr.add("Job already exists!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valBkNotChange(Job job) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		String code = findById(job.getId()).getCode();
		String company = findById(job.getId()).getCompany().getId();
		
		if(!(job.getCode().equals(code) && job.getCompany().getId().equals(company))) {
			listErr.add("BK can't be changed!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valNonBk(Job job) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(job.getName().isEmpty()) {
			listErr.add("Job name can't empty!");
		}
		if(job.getOrdinal() == null) {
			listErr.add("Ordinal can't empty!");
		}
		if(job.getCreatedBy().isEmpty()) {
			listErr.add("Created by can't empty!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valCreatedAtNotChange(Job job) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		Timestamp createdAt = findById(job.getId()).getCreatedAt();
		if(!job.getCreatedAt().equals(createdAt)) {
			listErr.add("Created at can't be changed!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void insert(Job job) throws Exception {
		job.setCreatedAt(getTime());
		valBkNotNull(job);
		valBkNotExist(job);
		valNonBk(job);
		jobDao.create(job);
	}
	
	public void update(Job job) throws Exception {
		job.setUpdatedAt(getTime());
		valIdNotNull(job);
		valIdExist(job.getId());
		valBkNotNull(job);
		valBkNotChange(job);
		valNonBk(job);
		valCreatedAtNotChange(job);
		jobDao.update(job);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		jobDao.deleteById(id);
	}

}
