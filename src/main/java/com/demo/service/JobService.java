package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.JobDao;
import com.demo.model.Job;

@Service
public class JobService {
	
	@Autowired
	private JobDao jobDao;
	
	public void valIdExist(String id) throws Exception {
		if(!jobDao.isIdExist(id)) {
			throw new Exception("Job not found!");
		}
	}
	
	public void valIdNotNull(Job job) throws Exception {
		if(job.getId().isEmpty()) {
			throw new Exception("Job ID can't empty!");
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
		if(job.getCompany().getId().isEmpty()) {
			throw new Exception("Company can't empty!");
		}
		if(job.getCode().isEmpty()) {
			throw new Exception("Code can't empty!");
		}
	}
	
	public void valBkNotExist(Job job) throws Exception {
		if(jobDao.isBkExist(job.getCode(), job.getCompany().getId())) {
			throw new Exception("Job already exists!");
		}
	}
	
	public void valBkNotChange(Job job) throws Exception {
		String code = findById(job.getId()).getCode();
		String company = findById(job.getId()).getCompany().getId();
		
		if(!(job.getCode().equals(code) && job.getCompany().getId().equals(company))) {
			throw new Exception("BK can't be changed!");
		}
	}
	
	public void valNonBk(Job job) throws Exception {
		if(job.getName().isEmpty()) {
			throw new Exception("Job name can't empty!");
		}
		if(job.getOrdinal() == null) {
			throw new Exception("Ordinal can't empty!");
		}
	}
	
	public void insert(Job job) throws Exception {
		valBkNotNull(job);
		valBkNotExist(job);
		valNonBk(job);
		jobDao.create(job);
	}
	
	public void update(Job job) throws Exception {
		valIdNotNull(job);
		valIdExist(job.getId());
		valBkNotNull(job);
		valBkNotChange(job);
		valNonBk(job);
		jobDao.update(job);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		jobDao.deleteById(id);
	}
	
}
