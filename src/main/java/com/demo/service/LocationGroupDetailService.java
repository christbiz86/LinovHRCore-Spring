package com.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.LocationGroupDetailDao;
import com.demo.model.LocationGroupDetail;

@Service
public class LocationGroupDetailService {
	@Autowired
	private LocationGroupDetailDao locationGroupDetailDao;

	public List<LocationGroupDetail> findAll() {
		return locationGroupDetailDao.findAll();
	}

	public LocationGroupDetail findById(String id) {
		return locationGroupDetailDao.findOne(id);
	}
	
	public LocationGroupDetail findByCode(String location, String locationGroup) {
		return locationGroupDetailDao.findByBk(location, locationGroup);
	}
	
	private void valIdExist(String id) throws Exception {
		if(!locationGroupDetailDao.isIdExist(id)) {
			throw new Exception("Data is Not Found");
		}
	}
	
	public void valIdNotNull(LocationGroupDetail locationGroupDetail) throws Exception {
		if(locationGroupDetail.getId() == null) {
			throw new Exception("Data is Not Found");
		}
	}
	
	public void valNonBk(LocationGroupDetail locationGroupDetail) throws Exception {
		if(locationGroupDetail.getVersion() == null) {
			throw new Exception("Version cannot be empty");
		}
	}
	
	private void valBkNotExist(LocationGroupDetail locationGroupDetail) throws Exception {
		if(locationGroupDetailDao.isBkExist(locationGroupDetail.getLocation().getId(), locationGroupDetail.getLocationGroup().getId())) {
			throw new Exception("Data already Exist");
		}
	}
	
	private void valBkNotChange(LocationGroupDetail locationGroupDetail) throws Exception {
		String location = findById(locationGroupDetail.getId()).getLocation().getId();
		String locationGroup = findById(locationGroupDetail.getId()).getLocationGroup().getId();
		if(!locationGroupDetail.getLocation().getId().equals(location)) {
			throw new Exception("Location cannot Be changed");
		}
		if(!locationGroupDetail.getLocationGroup().getId().equals(locationGroup)) {
			throw new Exception("Location Group cannot be changed");
		}
	}
	
	private void valBkNotNull(LocationGroupDetail locationGroupDetail) throws Exception {
		if(locationGroupDetail.getLocation() == null || locationGroupDetail.getLocation().getId().isEmpty()) {
			throw new Exception("Location cannot be empty");
		}
		if(locationGroupDetail.getLocationGroup() == null || locationGroupDetail.getLocationGroup().getId().isEmpty()) {
			throw new Exception("Location Group cannot be empty");
		}
	}

	public void save(LocationGroupDetail locationGroupDetail) throws Exception {
		valBkNotNull(locationGroupDetail);
		valNonBk(locationGroupDetail);
		valBkNotExist(locationGroupDetail);
		locationGroupDetailDao.create(locationGroupDetail);
	}

	public void update(LocationGroupDetail locationGroupDetail) throws Exception {
		valIdNotNull(locationGroupDetail);
		valIdExist(locationGroupDetail.getId());
		valBkNotNull(locationGroupDetail);
		valBkNotChange(locationGroupDetail);
		valNonBk(locationGroupDetail);
		locationGroupDetailDao.update(locationGroupDetail);
	}

	public void delete(String id) throws Exception {
		locationGroupDetailDao.deleteById(id);
	}
}
