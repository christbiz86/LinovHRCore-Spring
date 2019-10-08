package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UnitTypeDao;
import com.demo.model.UnitType;

@Service
public class UnitTypeService {
	@Autowired
	private UnitTypeDao unitTypeDao;
	
	public List<UnitType> findAll(){
		return unitTypeDao.findAll();
	}
	
	public UnitType findById(String id) {
		return unitTypeDao.findOne(id);
	}
	
	public UnitType findByCode(String code, String companyId) {
		return unitTypeDao.findByCode(code, companyId);
	}
	
	private void valIdExist(String id)throws Exception{
		List<String> listErr = new ArrayList<String>();
		
		if(!unitTypeDao.isIdExist(id)) {
			listErr.add("Data is Not Found");
		}
	}
	
	private void valIdNotNull(UnitType unitType)throws Exception {
		if(unitType.getId() == null || unitType.getId().isEmpty()) {
			throw new Exception("Id Cannot be empty");
		}
	}
	
	private void valNonBk(UnitType unitType)throws Exception{
		if(unitType.getCompany() == null || unitType.getCompany().getId() == null) {
			throw new Exception("Unit Type Company cannot be empty");
		}
		if(unitType.getName() == null && unitType.getName().isEmpty()) {
			throw new Exception("Unit Type Name cannot be empty");
		}
		if(unitType.getUnitLevel() == null) {
			throw new Exception("Type Unit Level cannot be empty");
		}
	}
	
	private void valBkNotExist(UnitType unitType)throws Exception{
		if(unitTypeDao.isBkExist(unitType.getCode(), unitType.getCompany().getId())) {
			throw new Exception("Data already exist");
		}
	}
	
	private void valBkNotChange(UnitType unitType)throws Exception{
		String company = findById(unitType.getId()).getCompany().getId();
		String code = findById(unitType.getId()).getCode();

		if(!unitType.getCompany().getId().equals(company)) {
			throw new Exception("Company is cannot be changed");
		}
		if(!unitType.getCode().equals(code)) {
			throw new Exception("Unit Type Code is cannot be changed");
		}
	}
	
	private void valBkNotNull(UnitType unitType) throws Exception{
		if(unitType.getCompany() == null || unitType.getCompany().getId().isEmpty()) {
			throw new Exception("Unit Type Company cannot be empty");
		}
		if(unitType.getCode() == null) {
			throw new Exception("Unit Type Code cannot be empty");
		}
	}
	
	public void save(UnitType unitType) throws Exception {
		unitType.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		
		valBkNotNull(unitType);
		valBkNotExist(unitType);
		valNonBk(unitType);
		unitTypeDao.create(unitType);
	}
	
	public void update(UnitType unitType) throws Exception {
		unitType.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		
		valIdNotNull(unitType);
		valIdExist(unitType.getId());
		valBkNotNull(unitType);
		valBkNotChange(unitType);
		valNonBk(unitType);
		unitTypeDao.update(unitType);
	}
	
	public void delete(String id) throws Exception {
		unitTypeDao.deleteById(id);
	}
}
