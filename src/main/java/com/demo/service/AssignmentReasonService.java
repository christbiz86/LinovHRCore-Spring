package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.AssignmentReasonDao;
import com.demo.model.AssignmentReason;

@Service
public class AssignmentReasonService {
	
	@Autowired
	private AssignmentReasonDao arDao;
	
	private Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void valIdExist(String id) throws Exception {
		if(!arDao.isIdExist(id)) {
			throw new Exception("Assignment Reason not found!");
		}
	}
	
	public void valIdNotNull(AssignmentReason ar) throws Exception {
		if(ar.getId().isEmpty()) {
			throw new Exception("Assignment Reason ID can't empty!");
		}
	}
	
	public List<AssignmentReason> findAll() {
		return arDao.findAll();
	}
	
	public AssignmentReason findById(String id) {
		return arDao.findOne(id);
	}
	
	public AssignmentReason findByCode(String code) {
		return arDao.findByCode(code);
	}
	
	public void valBkNotNull(AssignmentReason ar) throws Exception {
		if(ar.getCompany().getId().isEmpty()) {
			throw new Exception("Company can't empty!");
		}
		if(ar.getCode().isEmpty()) {
			throw new Exception("Code can't empty!");
		}
	}
	
	public void valBkNotExist(AssignmentReason ar) throws Exception {
		if(arDao.isBkExist(ar.getCode(), ar.getCompany().getId())) {
			throw new Exception("Assignment Reason already exists!");
		}
	}
	
	public void valBkNotChange(AssignmentReason ar) throws Exception {
		String code = findById(ar.getId()).getCode();
		String company = findById(ar.getId()).getCompany().getId();
		
		if(!(ar.getCode().equals(code) && ar.getCompany().getId().equals(company))) {
			throw new Exception("BK can't be changed!");
		}
	}
	
	public void valNonBk(AssignmentReason ar) throws Exception {
		if(ar.getLov().getId().isEmpty()) {
			throw new Exception("Lov can't empty!");
		}
	}
	
	public void valCreatedAtNotChange(AssignmentReason ar) throws Exception {
		Timestamp createdAt = findById(ar.getId()).getCreatedAt();
		if(!ar.getCreatedAt().equals(createdAt)) {
			throw new Exception("Created at can't be changed!");
		}
	}
	
	public void insert(AssignmentReason ar) throws Exception {
		ar.setCreatedAt(getTime());
		valBkNotNull(ar);
		valBkNotExist(ar);
		valNonBk(ar);
		arDao.create(ar);
	}
	
	public void update(AssignmentReason ar) throws Exception {
		ar.setUpdatedAt(getTime());
		valIdNotNull(ar);
		valIdExist(ar.getId());
		valBkNotNull(ar);
		valBkNotChange(ar);
		valNonBk(ar);
		valCreatedAtNotChange(ar);
		arDao.update(ar);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		arDao.deleteById(id);
	}

}
