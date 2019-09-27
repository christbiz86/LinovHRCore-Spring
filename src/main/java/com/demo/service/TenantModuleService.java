package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.TenantModuleDao;
import com.demo.model.TenantModule;

@Service
public class TenantModuleService {
	
	@Autowired
	private TenantModuleDao tmDao;
	
	private Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void valIdExist(String id) throws Exception {
		if(!tmDao.isIdExist(id)) {
			throw new Exception("Tenant Module not found!");
		}
	}
	
	public void valIdNotNull(TenantModule tm) throws Exception {
		if(tm.getId().isEmpty()) {
			throw new Exception("Tenant Module ID can't empty!");
		}
	}
	
	public List<TenantModule> findAll() {
		return tmDao.findAll();
	}
	
	public TenantModule findById(String id) {
		return tmDao.findOne(id);
	}
	
	public void valBkNotNull(TenantModule tm) throws Exception {
		if(tm.getTenant().getId().isEmpty()) {
			throw new Exception("Tenant can't empty!");
		}
		if(tm.getModule().getId().isEmpty()) {
			throw new Exception("Module can't empty!");
		}
	}
	
	public void valBkNotExist(TenantModule tm) throws Exception {
		if(tmDao.isBkExist(tm.getTenant().getId(), tm.getModule().getId())) {
			throw new Exception("Tenant Module already exists!");
		}
	}
	
	public void valBkNotChange(TenantModule tm) throws Exception {
		String tenant = findById(tm.getId()).getTenant().getId();
		String module = findById(tm.getId()).getModule().getId();
		
		if(!(tm.getTenant().getId().equals(tenant) && tm.getModule().getId().equals(module))) {
			throw new Exception("BK can't be changed!");
		}
	}
	
//	public void valNonBk(TenantModule tm) throws Exception {
//		if(tm.getCreatedBy().isEmpty()) {
//			throw new Exception("Created by can't empty!");
//		}
//	}
	
	public void valCreatedAtNotChange(TenantModule tm) throws Exception {
		Timestamp createdAt = findById(tm.getId()).getCreatedAt();
		if(!tm.getCreatedAt().equals(createdAt)) {
			throw new Exception("Created at can't be changed!");
		}
	}
	
	public void insert(TenantModule tm) throws Exception {
		tm.setCreatedAt(getTime());
		valBkNotNull(tm);
		valBkNotExist(tm);
//		valNonBk(tm);
		tmDao.create(tm);
	}
	
	public void update(TenantModule tm) throws Exception {
		tm.setUpdatedAt(getTime());
		valIdNotNull(tm);
		valIdExist(tm.getId());
		valBkNotNull(tm);
		valBkNotChange(tm);
//		valNonBk(tm);
		valCreatedAtNotChange(tm);
		tmDao.update(tm);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		tmDao.deleteById(id);
	}

}
