package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UnitTypeDao;
import com.demo.exception.ValidationException;
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
		List<String> listErr = new ArrayList<String>();
		
		if(unitType.getId() == null) {
			listErr.add("Id Cannot be empty \n");
		}
	}
	
	private void valNonBk(UnitType unitType)throws Exception{
		List<String> listErr = new ArrayList<String>();
		
		if(unitType.getCompany() == null || unitType.getCompany().getId() == null) {
			listErr.add("Unit Type Company cannot be empty \n");
		}
		if(unitType.getName() == null && unitType.getName().isEmpty()) {
			listErr.add("Unit Type Name cannot be empty \n");
		}
		if(unitType.getUnitLevel() == null) {
			listErr.add("Type Unit Level cannot be empty \n");
		}
		if(unitType.getCreatedBy() == null && unitType.getCreatedBy().isEmpty()) {
			listErr.add("Created By Unit Type cannot be empty \n");
		}
		if(unitType.getCreatedAt() == null) {
			listErr.add("Created At Unit Type cannot be empty \n");
		}
		if(unitType.getVersion() == null) {
			listErr.add("Version cannot be empty");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valBkNotExist(UnitType unitType)throws Exception{
		List<String> listErr = new ArrayList<String>();
		
		if(unitTypeDao.isBkExist(unitType.getCode(), unitType.getCompany().getId())) {
			listErr.add("Data already exist");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valBkNotChange(UnitType unitType)throws Exception{
		String company = findById(unitType.getId()).getCompany().getId();
		String code = findById(unitType.getId()).getCode();
		List<String> listErr = new ArrayList<String>();
		
		if(!unitType.getCompany().getId().equals(company)) {
			listErr.add("Company is cannot be changed");
		}
		if(!unitType.getCode().equals(code)) {
			listErr.add("Unit Type Code is cannot be changed");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valBkNotNull(UnitType unitType) throws Exception{
		List<String> listErr = new ArrayList<String>();
		
		if(unitType.getCompany() == null || unitType.getCompany().getId().isEmpty()) {
			listErr.add("Unit Type Company cannot be empty");
		}
		if(unitType.getCode() == null) {
			listErr.add("Unit Type Code cannot be empty");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valCreatedNotChange(UnitType unitType)throws Exception {
		UnitType tempUnitType = findById(unitType.getId());
		List<String> listErr = new ArrayList<String>();
			
		if(tempUnitType.getCreatedAt() != unitType.getCreatedAt() && !tempUnitType.getCreatedBy().equals(unitType.getCreatedBy())) {
			listErr.add("Created cannot be change");
		}
	
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void save(UnitType unitType) throws Exception {
		valBkNotNull(unitType);
		valBkNotExist(unitType);
		valNonBk(unitType);
		unitTypeDao.create(unitType);
	}
	
	public void update(UnitType unitType) throws Exception {
		valCreatedNotChange(unitType);
		
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
