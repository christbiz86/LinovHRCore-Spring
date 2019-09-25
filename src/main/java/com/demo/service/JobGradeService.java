package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.JobGradeDao;
import com.demo.model.JobGrade;

@Service
public class JobGradeService {
	
	@Autowired
	private JobGradeDao jobGradeDao;
	
	private Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void valIdExist(String id) throws Exception {
		if (!jobGradeDao.isIdExist(id)) {
			throw new Exception("Job Grade not found!");
		}
	}
	
	public void valIdNotNull(JobGrade jobGrade) throws Exception {
		if (jobGrade.getId().isEmpty()) {
			throw new Exception("Job Grade ID can't empty!");
		}
	}
	
	public List<JobGrade> findAll() {
		return jobGradeDao.findAll();
	}
	
	public JobGrade findById(String id) {
		return jobGradeDao.findOne(id);
	}
	
	public void valBkNotNull(JobGrade jobGrade) throws Exception {
		if(jobGrade.getJob().getId().isEmpty()) {
			throw new Exception("Job can't empty!");
		}
		if(jobGrade.getGrade().getId().isEmpty()) {
			throw new Exception("Grade can't empty!");
		}
	}
	
	public void valBkNotExist(JobGrade jobGrade) throws Exception {
		if(jobGradeDao.isBkExist(jobGrade.getJob().getId(), jobGrade.getGrade().getId())) {
			throw new Exception("Job Grade already exists!");
		}
	}
	
	public void valBkNotChange(JobGrade jobGrade) throws Exception {
		String job = findById(jobGrade.getId()).getJob().getId();
		String grade = findById(jobGrade.getId()).getGrade().getId();
		
		if(!(jobGrade.getJob().getId().equals(job) && jobGrade.getGrade().getId().equals(grade))) {
			throw new Exception("BK can't be changed!");
		}
	}
	
	public void valNonBk(JobGrade jobGrade) throws Exception {
		if(jobGrade.getCreatedBy() == null) {
			throw new Exception("Created by can't empty!");
		}
	}
	
	public void valCreatedAtNotChange(JobGrade jobGrade) throws Exception {
		Timestamp createdAt = findById(jobGrade.getId()).getCreatedAt();
		if(!jobGrade.getCreatedAt().equals(createdAt)) {
			throw new Exception("Created at can't be changed!");
		}
	}
	
	public void insert(JobGrade jobGrade) throws Exception {
		jobGrade.setCreatedAt(getTime());
		valBkNotNull(jobGrade);
		valBkNotExist(jobGrade);
		valNonBk(jobGrade);
		jobGradeDao.create(jobGrade);
	}
	
	public void update(JobGrade jobGrade) throws Exception {
		jobGrade.setUpdatedAt(getTime());
		valIdNotNull(jobGrade);
		valIdExist(jobGrade.getId());
		valBkNotNull(jobGrade);
		valBkNotChange(jobGrade);
		valNonBk(jobGrade);
		valCreatedAtNotChange(jobGrade);
		jobGradeDao.update(jobGrade);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		jobGradeDao.deleteById(id);
	}

}