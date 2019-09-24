package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.JobGradeDao;
import com.demo.exception.ValidationException;
import com.demo.model.JobGrade;

@Service
public class JobGradeService {
	
	@Autowired
	private JobGradeDao jobGradeDao;
	
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
		
		if(jobGrade.getMidRate() == null) {
			listErr.add("Mid rate can't empty!\n");
		}
		if(jobGrade.getBottomRate() == null) {
			listErr.add("Bottom rate can't empty!\n");
		}
		if(jobGrade.getTopRate() == null) {
			listErr.add("Top rate can't empty!\n");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}	
	}
	
	@Transactional
	public void insert(JobGrade jobGrade) throws Exception {
		valBkNotNull(jobGrade);
		valBkNotExist(jobGrade);
		valNonBk(jobGrade);
		jobGradeDao.create(jobGrade);
	}
	
	@Transactional
	public void update(JobGrade jobGrade) throws Exception {
		valIdNotNull(jobGrade);
		valIdExist(jobGrade.getId());
		valBkNotNull(jobGrade);
		valBkNotChange(jobGrade);
		valNonBk(jobGrade);
		jobGradeDao.update(jobGrade);
	}
	
	@Transactional
	public void delete(String id) throws Exception {
		valIdExist(id);
		jobGradeDao.deleteById(id);
	}

}
