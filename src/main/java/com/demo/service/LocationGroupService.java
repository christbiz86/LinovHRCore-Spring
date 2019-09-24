package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.LocationGroupDao;
import com.demo.exception.ValidationException;
import com.demo.model.LocationGroup;

@Service
public class LocationGroupService {
	@Autowired
	private LocationGroupDao locationGroupDao;
	
	public List<LocationGroup> findAll(){
		return locationGroupDao.findAll();
	}

	public LocationGroup findById(String id) {
		return locationGroupDao.findOne(id);
	}
	
	public LocationGroup findByBk(String code, String companyId) {
		return locationGroupDao.findByBk(code, companyId);
	}
	
	private void validExist(String id) throws Exception {
		if(!locationGroupDao.isIdExist(id)) {
			throw new Exception ("Data is Not Found");
		}
	}
	
	private void valIdNotNull(LocationGroup locationGroup) throws Exception {
		if(locationGroup.getId() == null) {
			throw new Exception("Id Cannot be empty");
		}
	}
	
	private void valNonBk(LocationGroup locationGroup) throws ValidationException {
		List<String> listErr = new ArrayList<String>();
		
		if(locationGroup.getName().isEmpty()) {
			listErr.add("Location Group Name cannot be empty");
		}
		if(locationGroup.getCreatedAt() == null) {
			listErr.add("Time Created Data cannot be empty");
		}
		if(locationGroup.getCreatedBy().isEmpty()) {
			listErr.add("Creator Data cannot be empty");
		}
		if(locationGroup.getVersion() == null) {
			listErr.add("Version Cannot be empty");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valBkNotExist(LocationGroup locationGroup) throws Exception {
		if(locationGroupDao.isBkExist(locationGroup.getCode(), locationGroup.getCompany().getId())) {
			throw new Exception ("Data already exist");
		}
	}
	
	private void valBkNotChange(LocationGroup locationGroup) throws Exception {
		String company = findById(locationGroup.getId()).getCompany().getId();
		String code = findById(locationGroup.getId()).getCode();
		
		if(!locationGroup.getCompany().getId().equals(company)) {
			throw new Exception("Company Location Group cannot be changed");
		}
		if(!locationGroup.getCode().equals(code)) {
			throw new Exception("Code Location Group cannot be changed");
		}
	}
	
	private void valBkNotNull(LocationGroup locationGroup) throws Exception {
		if(locationGroup.getCompany() == null || locationGroup.getCompany().getId().isEmpty()) {
			throw new Exception("Company Location Group cannot be empty");
		}
		if(locationGroup.getCode().isEmpty()) {
			throw new Exception("Location Group Code cannot be empty");
		}
	}
	
	private void valCreatedNotChange(LocationGroup locationGroup) throws Exception {
		LocationGroup temp = findById(locationGroup.getId());
		
		if(temp.getCreatedAt() != locationGroup.getCreatedAt() && !temp.getCreatedBy().equals(locationGroup.getCreatedBy())) {
			throw new Exception("Creator cannot be changed");
		}
	}

	public void save(LocationGroup locationGroup) throws Exception {
		locationGroup.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		
		valBkNotNull(locationGroup);
		valNonBk(locationGroup);
		valBkNotExist(locationGroup);
		locationGroupDao.create(locationGroup);
	}

	public void update(LocationGroup locationGroup) throws Exception {
		locationGroup.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		
		valCreatedNotChange(locationGroup);
		
		valIdNotNull(locationGroup);
		validExist(locationGroup.getId());
		valBkNotNull(locationGroup);
		valBkNotChange(locationGroup);
		valNonBk(locationGroup);
		locationGroupDao.update(locationGroup);
	}

	public void delete(String id) throws Exception {
		locationGroupDao.deleteById(id);
	}
}
