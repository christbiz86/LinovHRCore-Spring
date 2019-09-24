package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UnitDao;
import com.demo.exception.ValidationException;
import com.demo.model.Unit;

@Service
public class UnitService {
	@Autowired
	private UnitDao unitDao;
	
	public List<Unit> findAll(){
		return unitDao.findAll();
	}
	
	public Unit findById(String id) {
		return unitDao.findOne(id);
	}
	
	public Unit findByCode(String code, String companyId) {
		return unitDao.findByCode(code, companyId);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!unitDao.isIdExist(id)) {
			throw new ValidationException("Data is Not Found");
		}
	}
	
	private void valIdNotNull(Unit unit)throws Exception {
		
		if(unit.getId() == null) {
			throw new ValidationException("Id Cannot be empty \n");
		}
	}
	
	private void valNonBk(Unit unit)throws Exception{
		List<String> listErr = new ArrayList<String>();

		if(unit.getCompany() == null || unit.getCompany().getId().isEmpty()) {
			listErr.add("Unit Company cannot be empty \n");
		}
		if(unit.getName() == null || unit.getName().isEmpty()) {
			listErr.add("Unit Name cannot be empty \n");
		}
		if(unit.getCreatedBy() == null || unit.getCreatedBy().isEmpty()) {
			listErr.add("Created By cannot be empty \n");
		}
		if(unit.getCreatedAt() == null) {
			listErr.add("Created At cannot be empty \n");
		}
		if(unit.getVersion() == null) {
			listErr.add("Version cannot be empty");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valBkNotExist(Unit unit)throws Exception{
		if(unitDao.isBkExist(unit.getCode(), unit.getCompany().getId())) {
			throw new ValidationException("Data already exist");
		}
	}
	
	private void valBkNotChange(Unit unit)throws Exception{
		String company = findById(unit.getId()).getCompany().getId();
		String code = findById(unit.getId()).getCode();
		if(!unit.getCompany().getId().equals(company)) {
			throw new ValidationException("Company is cannot be changed");
		}
		if(!unit.getCode().equals(code)) {
			throw new ValidationException("Unit Code is cannot be changed");
		}
	}
	
	private void valBkNotNull(Unit unit) throws Exception{
		
		if(unit.getCompany() == null || unit.getCompany().getId().isEmpty()) {

			throw new ValidationException("Unit Company cannot be empty");
		}
		
		if(unit.getCode() == null) {
			throw new ValidationException("Unit Code cannot be empty");
		}
	}
	
	private void valCreatedNotChange(Unit unit)throws Exception {
		Unit tempUnit=findById(unit.getId());
			
		if(tempUnit.getCreatedAt() != unit.getCreatedAt() && !tempUnit.getCreatedBy().equals(unit.getCreatedBy())) {
			throw new ValidationException("Created cannot be change");
		}
	
	}
	
	public void save(Unit unit) throws Exception {
		unit.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		
		valBkNotNull(unit);
		valBkNotExist(unit);
		valNonBk(unit);
		unitDao.create(unit);
	}
	
	public void update(Unit unit) throws Exception {
		unit.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		
		valCreatedNotChange(unit);
		
		valIdNotNull(unit);
		valIdExist(unit.getId());
		valBkNotNull(unit);
		valBkNotChange(unit);
		valNonBk(unit);
		unitDao.update(unit);
	}
	
	public void delete(String id) throws Exception {
		unitDao.deleteById(id);
	}
}
