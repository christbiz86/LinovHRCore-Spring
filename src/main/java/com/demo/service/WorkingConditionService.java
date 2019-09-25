package com.demo.service;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.WorkingConditionDao;
import com.demo.exception.ValidationException;
import com.demo.model.WorkingCondition;

@Service
public class WorkingConditionService {
	
	@Autowired
	private WorkingConditionDao wcDao;
	
	private Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void valIdExist(String id) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(!wcDao.isIdExist(id)) {
			listErr.add("Working Condition not found!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valIdNotNull(WorkingCondition wc) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(wc.getId().isEmpty()) {
			listErr.add("Working Condition ID can't empty!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public List<WorkingCondition> findAll() {
		return wcDao.findAll();
	}
	
	public WorkingCondition findById(String id) {
		return wcDao.findOne(id);
	}
	
	public WorkingCondition findByCode(String code) {
		return wcDao.findByCode(code);
	}
	
	public void valBkNotNull(WorkingCondition wc) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(wc.getWorkingConditionType().getId().isEmpty()) {
			listErr.add("Working Condition Type can't empty!");
		}
		if(wc.getCode().isEmpty()) {
			listErr.add("Code can't empty!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valBkNotExist(WorkingCondition wc) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(wcDao.isBkExist(wc.getCode(), wc.getWorkingConditionType().getId())) {
			listErr.add("Working Condition already exists!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valBkNotChange(WorkingCondition wc) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		String code = findById(wc.getId()).getCode();
		String wct = findById(wc.getId()).getWorkingConditionType().getId();
		
		if(!(wc.getCode().equals(code) && wc.getWorkingConditionType().getId().equals(wct))) {
			listErr.add("BK can't be changed!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valNonBk(WorkingCondition wc) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(wc.getName().isEmpty()) {
			listErr.add("Working Condition name can't empty!");
		}
		if(wc.getCreatedBy().isEmpty()) {
			listErr.add("Created by can't empty!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valCreatedAtNotChange(WorkingCondition wc) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		Timestamp createdAt = findById(wc.getId()).getCreatedAt();
		if(!wc.getCreatedAt().equals(createdAt)) {
			listErr.add("Created at can't be changed!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void insert(WorkingCondition wc) throws Exception {
		wc.setCreatedAt(getTime());
		valBkNotNull(wc);
		valBkNotExist(wc);
		valNonBk(wc);
		wcDao.create(wc);
	}
	
	public void update(WorkingCondition wc) throws Exception {
		wc.setUpdatedAt(getTime());
		valIdNotNull(wc);
		valIdExist(wc.getId());
		valBkNotNull(wc);
		valBkNotChange(wc);
		valNonBk(wc);
		valCreatedAtNotChange(wc);
		wcDao.update(wc);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		wcDao.deleteById(id);
	}

}
