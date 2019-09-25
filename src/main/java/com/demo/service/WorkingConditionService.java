package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.WorkingConditionDao;
import com.demo.model.WorkingCondition;

@Service
public class WorkingConditionService {
	
	@Autowired
	private WorkingConditionDao wcDao;
	
	private Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void valIdExist(String id) throws Exception {
		if(!wcDao.isIdExist(id)) {
			throw new Exception("Working Condition not found!");
		}
	}
	
	public void valIdNotNull(WorkingCondition wc) throws Exception {
		if(wc.getId().isEmpty()) {
			throw new Exception("Working Condition ID can't empty!");
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
		if(wc.getWorkingConditionType().getId().isEmpty()) {
			throw new Exception("Working Condition Type can't empty!");
		}
		if(wc.getCode().isEmpty()) {
			throw new Exception("Code can't empty!");
		}
	}
	
	public void valBkNotExist(WorkingCondition wc) throws Exception {
		if(wcDao.isBkExist(wc.getCode(), wc.getWorkingConditionType().getId())) {
			throw new Exception("Working Condition already exists!");
		}
	}
	
	public void valBkNotChange(WorkingCondition wc) throws Exception {
		String code = findById(wc.getId()).getCode();
		String wct = findById(wc.getId()).getWorkingConditionType().getId();
		
		if(!(wc.getCode().equals(code) && wc.getWorkingConditionType().getId().equals(wct))) {
			throw new Exception("BK can't be changed!");
		}
	}
	
	public void valNonBk(WorkingCondition wc) throws Exception {
		if(wc.getName().isEmpty()) {
			throw new Exception("Working Condition name can't empty!");
		}
		if(wc.getCreatedBy().isEmpty()) {
			throw new Exception("Created by can't empty!");
		}
	}
	
	public void valCreatedAtNotChange(WorkingCondition wc) throws Exception {
		Timestamp createdAt = findById(wc.getId()).getCreatedAt();
		if(!wc.getCreatedAt().equals(createdAt)) {
			throw new Exception("Created at can't be changed!");
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
