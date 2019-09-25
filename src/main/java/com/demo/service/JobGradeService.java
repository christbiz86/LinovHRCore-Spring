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
			throw new Exception("Job already exists!");
		}
	}
	
	public void valBkNotChange(JobGrade jobGrade) throws Exception {
		String job = findById(jobGrade.getId()).getJob().getId();
		String grade = findById(jobGrade.getId()).getGrade().getId();
		if(!(jobGrade.getJob().getId().equals(job.toString()) && jobGrade.getGrade().getId().equals(grade.toString()))) {
			throw new Exception("BK can't be changed!");
		}
	}
	
	public void valNonBk(JobGrade jobGrade) throws Exception {
		if(jobGrade.getMidRate() == null) {
			throw new Exception("Mid rate can't empty!\n");
		}
		if(jobGrade.getBottomRate() == null) {
			throw new Exception("Bottom rate can't empty!\n");
		}
		if(jobGrade.getTopRate() == null) {
			throw new Exception("Top rate can't empty!\n");
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
