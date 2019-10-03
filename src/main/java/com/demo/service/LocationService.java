package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.LocationDao;
import com.demo.model.Location;

@Service
public class LocationService {
	@Autowired
	private LocationDao locationDao;
	
	public List<Location> findAll(){
		return locationDao.findAll();
	}
	
	public Location findById(String id) {
		return locationDao.findOne(id);
	}
	
	public Location findByCode(String code, String companyId) {
		return locationDao.findByCode(code, companyId);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!locationDao.isIdExist(id)) {
			throw new Exception("Data is Not Found");
		}
	}
	
	private void valIdNotNull(Location location)throws Exception {
		
		if(location.getId() == null || location.getId().isEmpty()) {
			throw new Exception("Id Cannot be empty");
		}
	}
	
	private void valNonBk(Location location) throws Exception {
		if(location.getCompany() == null) {
			if(location.getCompany().getId().isEmpty()) {
				throw new Exception("Location Company cannot be empty");
			}
		}
		if(location.getName().isEmpty()) {
			throw new Exception("Location Name cannot be empty");
		}
		if(location.getCity() == null) {
			if(location.getCity().getId().isEmpty()) {
				throw new Exception("City cannot be empty");
			}
		}
		if(location.getCode().isEmpty()) {
			throw new Exception("Code cannot be empty");
		}
	}
	
	private void valBkNotExist(Location location)throws Exception{
		if(locationDao.isBkExist(location.getCode(), location.getCompany().getId())) {
			throw new Exception("Data already exist");
		}
	}
	
	private void valBkNotChange(Location location)throws Exception{
		String company = findById(location.getId()).getCompany().getId();
		String code = findById(location.getId()).getCode();
		if(!location.getCompany().getId().equals(company)) {
			throw new Exception("Company is cannot be changed");
		}
		if(!location.getCode().equals(code)) {
			throw new Exception("Location Code is cannot be changed");
		}
	}
	
	private void valBkNotNull(Location location) throws Exception{
		
		if(location.getCompany() == null || location.getCompany().getId().isEmpty()) {
			throw new Exception("Location Company cannot be empty");
		}
		
		if(location.getCode().isEmpty()) {
			throw new Exception("Location Code cannot be empty");
		}
	}
	
	public void save(Location location) throws Exception {
		valBkNotNull(location);
		valNonBk(location);
		valBkNotExist(location);
		locationDao.create(location);
	}
	
	public void update(Location location) throws Exception {
		valIdNotNull(location);
		valIdExist(location.getId());
		valBkNotNull(location);
		valBkNotChange(location);
		valNonBk(location);
		locationDao.update(location);
	}
	
	public void delete(String id) throws Exception {
		locationDao.deleteById(id);
	}
}
