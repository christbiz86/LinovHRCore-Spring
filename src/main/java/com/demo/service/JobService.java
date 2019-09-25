package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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
		if(!(job.getCode().equals(code.toString()) && job.getCompany().getId().equals(company.toString()))) {
			throw new ValidationException("BK can't be changed!");
		}
	}
	
	public void valNonBk(Job job) throws Exception {
		if(job.getName().isEmpty()) {
			throw new Exception("Job name can't empty!");
		}
		if(job.getName() == null) {
			throw new Exception("Job name doesn't exists!");
		}
		if(job.getOrdinal() == null) {
			throw new Exception("Ordinal can't empty!");
		}
	}
	
	public void valCreatedAtNotChange(Job job) throws Exception {
		Timestamp createdAt = findById(job.getId()).getCreatedAt();
		if(!job.getCreatedAt().equals(createdAt)) {
			throw new Exception("Created at can't be changed!");
		}
	}
	
	@Transactional
	public void insert(Job job) throws Exception {
		job.setCreatedAt(getTime());
		valBkNotNull(job);
		valBkNotExist(job);
		valNonBk(job);
		jobDao.create(job);
	}
	
	@Transactional
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
	
	@Transactional
	public void delete(String id) throws Exception {
		valIdExist(id);
		jobDao.deleteById(id);
	}

}
