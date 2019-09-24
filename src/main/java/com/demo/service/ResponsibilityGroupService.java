package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ResponsibilityGroupDao;
import com.demo.exception.ValidationException;
import com.demo.model.Company;
import com.demo.model.ResponsibilityGroup;

@Service
public class ResponsibilityGroupService {

	@Autowired
	private ResponsibilityGroupDao responsibilityGroupDao;
	
	public List<ResponsibilityGroup> findAll(){
        return responsibilityGroupDao.findAll();
	}
	
	public List<ResponsibilityGroup> findAll(Integer offset, Integer limit){
	        return responsibilityGroupDao.findAll(offset,limit);
    }
	
	public ResponsibilityGroup findById(String id){
        return responsibilityGroupDao.findOne(id);
    }
	
	public ResponsibilityGroup findByBk(String companyId,String code){
		ResponsibilityGroup responsibilityGroup=new ResponsibilityGroup();
		Company company=new Company();
		company.setId(companyId);
		responsibilityGroup.setCompany(company);
		responsibilityGroup.setCode(code);
		return responsibilityGroupDao.findByBk(responsibilityGroup);
    }
	
	public void save(ResponsibilityGroup responsibilityGroup) throws ValidationException {
		responsibilityGroup.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(responsibilityGroup);
		valBkNotExist(responsibilityGroup);
		valNonBk(responsibilityGroup);
		responsibilityGroupDao.create(responsibilityGroup);
	}
	
	public void update(ResponsibilityGroup responsibilityGroup) throws ValidationException {
		responsibilityGroup.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valIdNotNull(responsibilityGroup);
		valIdExist(responsibilityGroup.getId());
		valBkNotNull(responsibilityGroup);
		valBkNotChange(responsibilityGroup);
		valNonBk(responsibilityGroup);
		valCreatedNotChange(responsibilityGroup);
		responsibilityGroupDao.update(responsibilityGroup);
	}
	
	public void delete(String id) throws ValidationException {
		valIdExist(id);
		responsibilityGroupDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws ValidationException{
		if(!responsibilityGroupDao.isIdExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	private void valIdNotNull(ResponsibilityGroup responsibilityGroup)throws ValidationException {
		if(responsibilityGroup.getId().isEmpty()) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(ResponsibilityGroup responsibilityGroup)throws ValidationException{
		List<String> listErr = new ArrayList<String>();
		if(responsibilityGroup.getName().isEmpty()) {
			listErr.add("Nama tidak boleh kosong");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valBkNotExist(ResponsibilityGroup responsibilityGroup)throws ValidationException{
		if(responsibilityGroupDao.isBkExist(responsibilityGroup)) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(ResponsibilityGroup responsibilityGroup)throws ValidationException{
		ResponsibilityGroup tempResponsibilityGroup=findById(responsibilityGroup.getId());

		if(!tempResponsibilityGroup.getCompany().getId().equals(responsibilityGroup.getCompany().getId()) || !tempResponsibilityGroup.getCode().equals(responsibilityGroup.getCode())) {
			throw new ValidationException("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(ResponsibilityGroup responsibilityGroup) throws ValidationException{
		if(responsibilityGroup.getCompany().getId().isEmpty() || responsibilityGroup.getCode().isEmpty()) {
			throw new ValidationException("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(ResponsibilityGroup responsibilityGroup)throws ValidationException {
		ResponsibilityGroup tempResponsibilityGroup=findById(responsibilityGroup.getId());
		
		if(!tempResponsibilityGroup.getCreatedAt().equals(responsibilityGroup.getCreatedAt()) || !tempResponsibilityGroup.getCreatedBy().equals(responsibilityGroup.getCreatedBy())) {
			throw new ValidationException("created tidak boleh berubah");
		}
	}
}