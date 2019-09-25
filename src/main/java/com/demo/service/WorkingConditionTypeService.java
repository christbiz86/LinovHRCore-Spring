package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.WorkingConditionTypeDao;
import com.demo.exception.ValidationException;
import com.demo.model.WorkingConditionType;

@Service
public class WorkingConditionTypeService {
	
	@Autowired
	private WorkingConditionTypeDao wctDao;
	
	private Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void valIdExist(String id) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(!wctDao.isIdExist(id)) {
			listErr.add("Working Condition Type not found!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valIdNotNull(WorkingConditionType wct) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(wct.getId().isEmpty()) {
			listErr.add("Working Condition Type ID can't empty!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
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
		List<String> listErr = new ArrayList<String>();
		
		if(wct.getCompany().getId().isEmpty()) {
			listErr.add("Company can't empty!");
		}
		if(wct.getCode().isEmpty()) {
			listErr.add("Code can't empty!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valBkNotExist(WorkingConditionType wct) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(wctDao.isBkExist(wct.getCode(), wct.getCompany().getId())) {
			listErr.add("Working Condition Type already exists!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valBkNotChange(WorkingConditionType wct) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		String code = findById(wct.getId()).getCode();
		String company = findById(wct.getId()).getCompany().getId();
		
		if(!(wct.getCode().equals(code) && wct.getCompany().getId().equals(company))) {
			listErr.add("BK can't be changed!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valNonBk(WorkingConditionType wct) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(wct.getName().isEmpty()) {
			listErr.add("Working Condition Type name can't empty!");
		}
		if(wct.getCreatedBy().isEmpty()) {
			listErr.add("Created by can't empty!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valCreatedAtNotChange(WorkingConditionType wct) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		Timestamp createdAt = findById(wct.getId()).getCreatedAt();
		if(!wct.getCreatedAt().equals(createdAt)) {
			listErr.add("Created at can't be changed!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
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
