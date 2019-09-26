package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.DutyDao;
import com.demo.model.Duty;

@Service
public class DutyService {
	
	@Autowired
	private DutyDao dutyDao;
	
	private Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void valIdNotNull(Duty duty) throws Exception {
		if(duty.getId().isEmpty()) {
			throw new Exception("Duty ID can't empty!");
		}
	}
	
	public void valIdExist(String id) throws Exception {
		if(!dutyDao.isIdExist(id)) {
			throw new Exception("Duty not found!");
		}
	}
	
	public List<Duty> findAll() {
		return dutyDao.findAll();
	}
	
	public Duty findById(String id) {
		return dutyDao.findOne(id);
	}
	
	public void valBkNotNull(Duty duty) throws Exception {
		if(duty.getCompany().getId().isEmpty()) {
			throw new Exception("Company can't empty!");
		}
		if(duty.getResponsibility().getId().isEmpty()) {
			throw new Exception("Responsibility can't empty!");
		}
	}
	
	public void valBkNotExist(Duty duty) throws Exception {
		if(dutyDao.isBkExist(duty.getCompany().getId(), duty.getResponsibility().getId())) {
			throw new Exception("Duty already exists!");
		}
	}
	
	public void valBkNotChange(Duty duty) throws Exception {
		String company = findById(duty.getId()).getCompany().getId();
		String responsibility = findById(duty.getId()).getResponsibility().getId();
		
		if(!(duty.getCompany().getId().equals(company) && duty.getResponsibility().getId().equals(responsibility))) {
			throw new Exception("BK can't be changed!");
		}
	}
	
	public void valNonBk(Duty duty) throws Exception {
		if(duty.getCreatedBy().isEmpty()) {
			throw new Exception("Created by can't empty!");
		}
	}
	
	public void valCreatedAtNotChange(Duty duty) throws Exception {
		Timestamp createdAt = findById(duty.getId()).getCreatedAt();
		if(!duty.getCreatedAt().equals(createdAt)) {
			throw new Exception("Created at can't be changed!");
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
