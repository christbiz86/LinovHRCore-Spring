package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.TenantDao;
import com.demo.model.Tenant;

@Service
public class TenantService {
	
	@Autowired
	private TenantDao tenantDao;
	
	private Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void valIdExist(String id) throws Exception {
		if(!tenantDao.isIdExist(id)) {
			throw new Exception("Tenant not found!");
		}
	}
	
	public void valIdNotNull(Tenant tenant) throws Exception {
		if(tenant.getId().isEmpty()) {
			throw new Exception("Tenant ID can't empty!");
		}
	}
	
	public List<Tenant> findAll() {
		return tenantDao.findAll();
	}
	
	public Tenant findById(String id) {
		return tenantDao.findOne(id);
	}
	
	public Tenant findByCode(String code) {
		return tenantDao.findByCode(code);
	}
	
	public void valBkNotNull(Tenant tenant) throws Exception {
		if(tenant.getName().isEmpty()) {
			throw new Exception("Tenant name can't empty!");
		}
		if(tenant.getCode().isEmpty()) {
			throw new Exception("Tenant code can't empty!");
		}
	}
	
	public void valBkNotExist(Tenant tenant) throws Exception {
		if(tenantDao.isBkExist(tenant.getName(), tenant.getCode())) {
			throw new Exception("Tenant already exists!");
		}
	}
	
	public void valBkNotChange(Tenant tenant) throws Exception {
		String name = findById(tenant.getId()).getName();
		String code = findById(tenant.getId()).getCode();
		
		if(!(tenant.getName().equals(name) && tenant.getCode().equals(code))) {
			throw new Exception("BK can't be changed!");
		}
	}
	
//	public void valNonBk(Tenant tenant) throws Exception {
//		if(tenant.getCreatedBy().isEmpty()) {
//			throw new Exception("Created by can't empty!");
//		}
//	}
	
	public void valCreatedAtNotChange(Tenant tenant) throws Exception {
		Timestamp createdAt = findById(tenant.getId()).getCreatedAt();
		if(!tenant.getCreatedAt().equals(createdAt)) {
			throw new Exception("Created at can't be changed!");
		}
	}
	
	public void insert(Tenant tenant) throws Exception {
		tenant.setCreatedAt(getTime());
		valBkNotNull(tenant);
		valBkNotExist(tenant);
		System.out.println(tenant.getCreatedBy());
//		valNonBk(tenant);
		System.out.println(tenant.getCreatedBy());
		tenantDao.create(tenant);
	}
	
	public void update(Tenant tenant) throws Exception {
		tenant.setUpdatedAt(getTime());
		valIdNotNull(tenant);
		valIdExist(tenant.getId());
		valBkNotNull(tenant);
		valBkNotChange(tenant);
//		valNonBk(tenant);
		valCreatedAtNotChange(tenant);
		tenantDao.update(tenant);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		tenantDao.deleteById(id);
	}

}
