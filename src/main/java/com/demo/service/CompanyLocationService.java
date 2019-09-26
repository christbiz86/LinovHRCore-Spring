package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.CompanyLocationDao;
import com.demo.model.CompanyLocation;

@Service
public class CompanyLocationService {
	@Autowired
	private CompanyLocationDao companyLocationDao;
	@Transactional
	public List<CompanyLocation> findAll(){
		return companyLocationDao.findAll();
	}
	@Transactional
	public CompanyLocation findById(String id) {
		return companyLocationDao.findOne(id);
	}
	@Transactional
	public CompanyLocation findByCode(String locationId, String companyId) {
		return companyLocationDao.findByBk(locationId, companyId);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!companyLocationDao.isIdExist(id)) {
			throw new Exception("Data is Not Found");
		}
	}
	
	private void valIdNotNull(CompanyLocation companyLocation)throws Exception {
		
		if(companyLocation.getId() == null || companyLocation.getId().isEmpty()) {
			throw new Exception("Company Location Id Cannot be empty");
		}
	}
	
	private void valBkNotExist(CompanyLocation companyLocation)throws Exception{
		if(companyLocationDao.isBkExist(companyLocation.getLocation().getId(), companyLocation.getCompany().getId())) {
			throw new Exception("Data already exist");
		}
	}
	
	private void valBkNotChange(CompanyLocation companyLocation)throws Exception{
		String company = findById(companyLocation.getId()).getCompany().getId();
		String location = findById(companyLocation.getId()).getLocation().getId();
		if(!companyLocation.getCompany().getId().equals(company)) {
			throw new Exception("Company is cannot be changed");
		}
		if(!companyLocation.getLocation().getId().equals(location)) {
			throw new Exception("Company Location is cannot be changed");
		}
	}
	
	private void valBkNotNull(CompanyLocation companyLocation) throws Exception{
		
		if(companyLocation.getCompany() == null || companyLocation.getCompany().getId().isEmpty()) {
			throw new Exception("Company cannot be empty");
		}
		
		if(companyLocation.getLocation() == null || companyLocation.getLocation().getId().isEmpty()) {
			throw new Exception("Location Company cannot be empty");
		}
	}
	@Transactional
	public void save(CompanyLocation companyLocation) throws Exception {
		companyLocation.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		
		valBkNotNull(companyLocation);
		valBkNotExist(companyLocation);
		companyLocationDao.create(companyLocation);
	}
	@Transactional
	public void update(CompanyLocation companyLocation) throws Exception {
		companyLocation.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		
		valIdNotNull(companyLocation);
		valIdExist(companyLocation.getId());
		valBkNotNull(companyLocation);
		valBkNotChange(companyLocation);
		companyLocationDao.update(companyLocation);
	}
	@Transactional
	public void delete(String id) throws Exception {
		companyLocationDao.deleteById(id);
	}
}
