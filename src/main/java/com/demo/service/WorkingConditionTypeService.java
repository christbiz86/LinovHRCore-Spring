package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.WorkingConditionTypeDao;
import com.demo.model.WorkingConditionType;

@Service
public class WorkingConditionTypeService {
	
	@Autowired
	private WorkingConditionTypeDao wctDao;
	
	private Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void valIdExist(String id) throws Exception {
		if(!wctDao.isIdExist(id)) {
			throw new Exception("Working Condition Type not found!");
		}
	}
	
	public void valIdNotNull(WorkingConditionType wct) throws Exception {
		if(wct.getId().isEmpty()) {
			throw new Exception("Working Condition Type ID can't empty!");
		}
	}
	
	public List<WorkingConditionType> findAll() {
		return wctDao.findAll();
	}
	
	public WorkingConditionType findById(String id) {
		return wctDao.findOne(id);
	}
	
	public WorkingConditionType findByCode(String code) {
		return wctDao.findByCode(code);
	}
	
	public void valBkNotNull(WorkingConditionType wct) throws Exception {
		if(wct.getCompany().getId().isEmpty()) {
			throw new Exception("Company can't empty!");
		}
		if(wct.getCode().isEmpty()) {
			throw new Exception("Code can't empty!");
		}
	}
	
	public void valBkNotExist(WorkingConditionType wct) throws Exception {
		if(wctDao.isBkExist(wct.getCode(), wct.getCompany().getId())) {
			throw new Exception("Working Condition Type already exists!");
		}
	}
	
	public void valBkNotChange(WorkingConditionType wct) throws Exception {
		String code = findById(wct.getId()).getCode();
		String company = findById(wct.getId()).getCompany().getId();
		
		if(!(wct.getCode().equals(code) && wct.getCompany().getId().equals(company))) {
			throw new Exception("BK can't be changed!");
		}
	}
	
	public void valNonBk(WorkingConditionType wct) throws Exception {
		if(wct.getName().isEmpty()) {
			throw new Exception("Working Condition Type name can't empty!");
		}
		if(wct.getCreatedBy().isEmpty()) {
			throw new Exception("Created by can't empty!");
		}
	}
	
	public void valCreatedAtNotChange(WorkingConditionType wct) throws Exception {
		Timestamp createdAt = findById(wct.getId()).getCreatedAt();
		if(!wct.getCreatedAt().equals(createdAt)) {
			throw new Exception("Created at can't be changed!");
		}
	}
	
	public void insert(WorkingConditionType wct) throws Exception {
		wct.setCreatedAt(getTime());
		valBkNotNull(wct);
		valBkNotExist(wct);
		valNonBk(wct);
		wctDao.create(wct);
	}
	
	public void update(WorkingConditionType wct) throws Exception {
		wct.setUpdatedAt(getTime());
		valIdNotNull(wct);
		valIdExist(wct.getId());
		valBkNotNull(wct);
		valBkNotChange(wct);
		valNonBk(wct);
		valCreatedAtNotChange(wct);
		wctDao.update(wct);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		wctDao.deleteById(id);
	}

}
