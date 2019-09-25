package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.DutyDao;
import com.demo.exception.ValidationException;
import com.demo.model.Duty;

@Service
public class DutyService {
	
	@Autowired
	private DutyDao dutyDao;
	
	private Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void valIdNotNull(Duty duty) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(duty.getId().isEmpty()) {
			listErr.add("Duty ID can't empty!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valIdExist(String id) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(!dutyDao.isIdExist(id)) {
			listErr.add("Duty not found!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public List<Duty> findAll() {
		return dutyDao.findAll();
	}
	
	public Duty findById(String id) {
		return dutyDao.findOne(id);
	}
	
	public void valBkNotNull(Duty duty) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(duty.getCompany().getId().isEmpty()) {
			listErr.add("Company can't empty!");
		}
		if(duty.getResponsibility().getId().isEmpty()) {
			listErr.add("Responsibility can't empty!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valBkNotExist(Duty duty) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(dutyDao.isBkExist(duty.getCompany().getId(), duty.getResponsibility().getId())) {
			listErr.add("Duty already exists!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valBkNotChange(Duty duty) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		String company = findById(duty.getId()).getCompany().getId();
		String responsibility = findById(duty.getId()).getResponsibility().getId();
		
		if(!(duty.getCompany().getId().equals(company) && duty.getResponsibility().getId().equals(responsibility))) {
			listErr.add("BK can't be changed!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valNonBk(Duty duty) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(duty.getCreatedBy().isEmpty()) {
			listErr.add("Created by can't empty!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valCreatedAtNotChange(Duty duty) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		Timestamp createdAt = findById(duty.getId()).getCreatedAt();
		if(!duty.getCreatedAt().equals(createdAt)) {
			listErr.add("Created at can't be changed!");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void insert(Duty duty) throws Exception {
		duty.setCreatedAt(getTime());
		valBkNotNull(duty);
		valBkNotExist(duty);
		valNonBk(duty);
		dutyDao.create(duty);
	}
	
	public void update(Duty duty) throws Exception {
		duty.setUpdatedAt(getTime());
		valIdNotNull(duty);
		valIdExist(duty.getId());
		valBkNotNull(duty);
		valBkNotChange(duty);
		valNonBk(duty);
		valCreatedAtNotChange(duty);
		dutyDao.update(duty);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		dutyDao.deleteById(id);
	}

}
