package com.demo.service;

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
		List<String> listErr = new ArrayList<String>();
		
		if(!unitDao.isIdExist(id)) {
			listErr.add("Data is Not Found");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valIdNotNull(Unit unit)throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if(unit.getId() == null) {
			listErr.add("Id Cannot be empty");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
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
		List<String> listErr = new ArrayList<String>();
		
		if(unitDao.isBkExist(unit.getCode(), unit.getCompany().getId())) {
			listErr.add("Data already exist");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valBkNotChange(Unit unit)throws Exception{
		String company = findById(unit.getId()).getCompany().getId();
		String code = findById(unit.getId()).getCode();
		List<String> listErr = new ArrayList<String>();
		
		if(!unit.getCompany().getId().equals(company)) {
			listErr.add("Company is cannot be changed");
		}
		if(!unit.getCode().equals(code)) {
			listErr.add("Unit Code is cannot be changed");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valBkNotNull(Unit unit) throws Exception{
		List<String> listErr = new ArrayList<String>();
		
		if(unit.getCompany() == null || unit.getCompany().getId().isEmpty()) {

			listErr.add("Unit Company cannot be empty");
		}
		if(unit.getCode() == null) {
			listErr.add("Unit Code cannot be empty");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valCreatedNotChange(Unit unit)throws Exception {
		Unit tempUnit=findById(unit.getId());
		List<String> listErr = new ArrayList<String>();
			
		if(tempUnit.getCreatedAt() != unit.getCreatedAt() && !tempUnit.getCreatedBy().equals(unit.getCreatedBy())) {
			listErr.add("Created cannot be change");
		}
	
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void save(Unit unit) throws Exception {
		valBkNotNull(unit);
		valBkNotExist(unit);
		valNonBk(unit);
		unitDao.create(unit);
	}
	
	public void update(Unit unit) throws Exception {
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
