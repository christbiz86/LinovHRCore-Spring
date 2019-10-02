package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.CountryDao;
import com.demo.model.Country;

@Service
public class CountryService {

    @Autowired
    private CountryDao countryDao;

    private Timestamp getTime() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public void valIdExist(String id) throws Exception {
		if (!countryDao.isIdExist(id)) {
			throw new Exception("Country not found!");
		}
	}
	
	public void valIdNotNull(Country country) throws Exception {
		if (country.getId().isEmpty()) {
			throw new Exception("Country ID can't empty!");
		}
	}
	
	public List<Country> findAll() {
		return countryDao.findAll();
	}
	
	public Country findById(String id) {
		return countryDao.findOne(id);
	}
	
	public Country findByBk(String code) {
		return countryDao.findByBk(code);
	}
	
	public void valBkNotNull(Country country) throws Exception {
		if(country.getCode().isEmpty()) {
			throw new Exception("Country code can't empty!");
		}
	}
	
	public void valBkNotExist(Country country) throws Exception {
		if(countryDao.isBkExist(country.getCode())) {
			throw new Exception("Country already exists!");
		}
	}
	
	public void valBkNotChange(Country country) throws Exception {
		String code = findById(country.getId()).getCode();
		
		if(!country.getCode().equals(code)) {
			throw new Exception("BK can't be changed!");
		}
	}
	
	public void valNonBk(Country country) throws Exception {
		if(country.getName().isEmpty()) {
			throw new Exception("Country name can't empty!");
		}
		if(country.getDialCode().isEmpty()) {
			throw new Exception("Country dial code can't empty!");
		}
		if(country.getNationality().isEmpty()) {
			throw new Exception("Country nationality can't empty!");
		}
	}
	
	public void valCreatedAtNotChange(Country country) throws Exception {
		Timestamp createdAt = findById(country.getId()).getCreatedAt();
		if(!country.getCreatedAt().equals(createdAt)) {
			throw new Exception("Created at can't be changed!");
		}
	}
	
	public void insert(Country country) throws Exception {
		country.setCreatedAt(getTime());
		valBkNotNull(country);
		valBkNotExist(country);
		valNonBk(country);
		countryDao.create(country);
	}
	
	public void update(Country country) throws Exception {
		country.setUpdatedAt(getTime());
		valIdNotNull(country);
		valIdExist(country.getId());
		valBkNotNull(country);
		valBkNotChange(country);
		valNonBk(country);
		valCreatedAtNotChange(country);
		countryDao.update(country);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		countryDao.deleteById(id);
	}

}
