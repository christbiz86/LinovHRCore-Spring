package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.CostcenterDao;
import com.demo.model.Costcenter;

@Service
public class CostcenterService {

    @Autowired
    private CostcenterDao costCenterDao;
    
    private Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void valIdExist(String id) throws Exception {
		if(!costCenterDao.isIdExist(id)) {
			throw new Exception("Cost Center not found!");
		}
	}
	
	public void valIdNotNull(Costcenter cc) throws Exception {
		if(cc.getId().isEmpty()) {
			throw new Exception("Cost Center ID can't empty!");
		}
	}
	
	public List<Costcenter> findAll() {
		return costCenterDao.findAll();
	}
	
	public Costcenter findById(String id) {
		return costCenterDao.findOne(id);
	}
	
	public Costcenter findByCode(String code) {
		return costCenterDao.findByCode(code);
	}
	
	public void valBkNotNull(Costcenter cc) throws Exception {
		if(cc.getCompany().getId().isEmpty()) {
			throw new Exception("Company can't empty!");
		}
		if(cc.getCode().isEmpty()) {
			throw new Exception("Code can't empty!");
		}
	}
	
	public void valBkNotExist(Costcenter cc) throws Exception {
		if(costCenterDao.isBkExist(cc.getCode(), cc.getCompany().getId())) {
			throw new Exception("Cost Center already exists!");
		}
	}
	
	public void valBkNotChange(Costcenter cc) throws Exception {
		String code = findById(cc.getId()).getCode();
		String company = findById(cc.getId()).getCompany().getId();
		
		if(!(cc.getCode().equals(code) && cc.getCompany().getId().equals(company))) {
			throw new Exception("BK can't be changed!");
		}
	}
	
	public void valNonBk(Costcenter cc) throws Exception {
		if(cc.getName().isEmpty()) {
			throw new Exception("Job name can't empty!");
		}
	}
	
	public void valCreatedAtNotChange(Costcenter cc) throws Exception {
		Timestamp createdAt = findById(cc.getId()).getCreatedAt();
		if(!cc.getCreatedAt().equals(createdAt)) {
			throw new Exception("Created at can't be changed!");
		}
	}
	
	public void insert(Costcenter cc) throws Exception {
		cc.setCreatedAt(getTime());
		valBkNotNull(cc);
		valBkNotExist(cc);
		valNonBk(cc);
		costCenterDao.create(cc);
	}
	
	public void update(Costcenter cc) throws Exception {
		cc.setUpdatedAt(getTime());
		valIdNotNull(cc);
		valIdExist(cc.getId());
		valBkNotNull(cc);
		valBkNotChange(cc);
		valNonBk(cc);
		valCreatedAtNotChange(cc);
		costCenterDao.update(cc);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		costCenterDao.deleteById(id);
	}

}
