package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.JobDao;
import com.demo.model.Job;

@Service
public class JobService {
	
	@Autowired
	private JobDao jobDao;
	
	private Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void valIdExist(Job job) throws Exception {
		if (!jobDao.isIdExist(job.getId())) {
			throw new Exception("ID tidak ada!");
		}
	}
	
	public void valIdNotNull(Job job) throws Exception {
		if (job.getId() == null) {
			throw new Exception("ID tidak boleh kosong!");
		}
	}
	
	public List<Job> findAll() {
		return jobDao.findAll();
	}
	
	public Job findById(String id) {
		return jobDao.findById(id);
	}
	
	public Job findByBk(String code) {
		return jobDao.findByBk(code);
	}
	
	public void valBkNotNull(Job job) throws Exception {
		if(job.getCode() == null) {
			throw new Exception("Job code can't empty!");
		}
	}
	
	public void valBkNotExist(Job job) throws Exception {
		if(jobDao.isBkExist(job.getCode())) {
			throw new Exception("Code already exists!");
		}
	}
	
	public void valBkNotChange(Job job) throws Exception {
		String s = findById(job.getId()).getCode();
		if(!job.getCode().toString().equals(s.toString())) {
			throw new Exception("Job code can't be changed!");
		}
	}
	
	public void valNonBk(Job job) throws Exception {
		StringBuilder sb = new StringBuilder();
		int error = 0;
		
		if(job.getName() == null) {
			sb.append("Job name can't empty!\n");
			error++;
		}
		if(job.getCreatedAt() == null) {
			sb.append("Created at can't empty!\n");
			error++;
		}
		if(job.getUpdatedAt() == null) {
			sb.append("Updated at can't empty!\\n");
			error++;
		}
		
		if(error > 0) {
			throw new Exception(sb.toString());
		}	
	}
	
	@Transactional
	public void insert(Job job) throws Exception {
		job.setCreatedAt(getTime());
		valBkNotNull(job);
		valBkNotExist(job);
		valNonBk(job);
		jobDao.save(job);
	}
	
	@Transactional
	public void update(Job job) throws Exception {
		valIdNotNull(job);
		valIdExist(job);
		valBkNotNull(job);
		valBkNotChange(job);
		valNonBk(job);
		jobDao.save(job);
	}

}
