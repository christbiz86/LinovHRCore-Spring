package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ResponsibilityDao;
import com.demo.exception.ValidationException;
import com.demo.model.Company;
import com.demo.model.Responsibility;

@Service
public class ResponsibilityService {

	@Autowired
	private ResponsibilityDao responsibilityDao;
	
	public List<Responsibility> findAll(){
        return responsibilityDao.findAll();
	}
	
	public List<Responsibility> findAll(Integer offset, Integer limit){
	        return responsibilityDao.findAll(offset,limit);
    }
	
	public Responsibility findById(String id){
        return responsibilityDao.findOne(id);
    }
	
	public Responsibility findByBk(String companyId,String code){
		Responsibility responsibility=new Responsibility();
		Company company=new Company();
		company.setId(companyId);
		responsibility.setCompany(company);
		responsibility.setCode(code);
		return responsibilityDao.findByBk(responsibility);
    }
	
	public void save(Responsibility responsibility) throws ValidationException {
		responsibility.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(responsibility);
		valBkNotExist(responsibility);
		valNonBk(responsibility);
		responsibilityDao.create(responsibility);
	}
	
	public void update(Responsibility responsibility) throws ValidationException {
		responsibility.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valIdNotNull(responsibility);
		valIdExist(responsibility.getId());
		valBkNotNull(responsibility);
		valBkNotChange(responsibility);
		valNonBk(responsibility);
		valCreatedNotChange(responsibility);
		responsibilityDao.update(responsibility);
	}
	
	public void delete(String id) throws ValidationException {
		valIdExist(id);
		responsibilityDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws ValidationException{
		if(!responsibilityDao.isIdExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	private void valIdNotNull(Responsibility responsibility)throws ValidationException {
		if(responsibility.getId().isEmpty()) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(Responsibility responsibility)throws ValidationException{
		List<String> listErr = new ArrayList<String>();
		if(responsibility.getName().isEmpty()) {
			listErr.add("Nama tidak boleh kosong");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valBkNotExist(Responsibility responsibility)throws ValidationException{
		if(responsibilityDao.isBkExist(responsibility)) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(Responsibility responsibility)throws ValidationException{
		Responsibility tempResponsibility=findById(responsibility.getId());

		if(!tempResponsibility.getCompany().getId().equals(responsibility.getCompany().getId()) || !tempResponsibility.getCode().equals(responsibility.getCode())) {
			throw new ValidationException("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(Responsibility responsibility) throws ValidationException{
		if(responsibility.getCompany().getId().isEmpty() || responsibility.getCode().isEmpty()) {
			throw new ValidationException("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(Responsibility responsibility)throws ValidationException {
		Responsibility tempResponsibility=findById(responsibility.getId());
		
		if(!tempResponsibility.getCreatedAt().equals(responsibility.getCreatedAt()) || !tempResponsibility.getCreatedBy().equals(responsibility.getCreatedBy())) {
			throw new ValidationException("created tidak boleh berubah");
		}
	}
}
