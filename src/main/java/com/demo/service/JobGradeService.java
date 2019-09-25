package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.JobGradeDao;
import com.demo.exception.ValidationException;
import com.demo.model.JobGrade;

@Service
public class JobGradeService {
	
	@Autowired
	private JobGradeDao jobGradeDao;
	
	private Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void valIdExist(String id) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if (!jobGradeDao.isIdExist(id)) {
			listErr.add("Job Grade not found!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valIdNotNull(JobGrade jobGrade) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if (jobGrade.getId().isEmpty()) {
			listErr.add("Job Grade ID can't empty!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public List<JobGrade> findAll() {
		return jobGradeDao.findAll();
	}
	
	public JobGrade findById(String id) {
		return jobGradeDao.findOne(id);
	}
	
	public void valBkNotNull(JobGrade jobGrade) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(jobGrade.getJob().getId().isEmpty()) {
			listErr.add("Job can't empty!");
		}
		if(jobGrade.getGrade().getId().isEmpty()) {
			listErr.add("Grade can't empty!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valBkNotExist(JobGrade jobGrade) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(jobGradeDao.isBkExist(jobGrade.getJob().getId(), jobGrade.getGrade().getId())) {
			listErr.add("Job already exists!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valBkNotChange(JobGrade jobGrade) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		String job = findById(jobGrade.getId()).getJob().getId();
		String grade = findById(jobGrade.getId()).getGrade().getId();
		
		if(!(jobGrade.getJob().getId().equals(job.toString()) && jobGrade.getGrade().getId().equals(grade.toString()))) {
			listErr.add("BK can't be changed!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valNonBk(JobGrade jobGrade) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(jobGrade.getCreatedBy() == null) {
			listErr.add("Created by can't empty!\n");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}	
	}
	
	public void valCreatedAtNotChange(JobGrade jobGrade) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		Timestamp createdAt = findById(jobGrade.getId()).getCreatedAt();
		if(!jobGrade.getCreatedAt().equals(createdAt)) {
			listErr.add("Created at can't be changed!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
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
