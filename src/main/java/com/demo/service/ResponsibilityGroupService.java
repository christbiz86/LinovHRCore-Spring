package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ResponsibilityGroupDao;
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
	
	public void save(ResponsibilityGroup responsibilityGroup) throws Exception {
		responsibilityGroup.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(responsibilityGroup);
		valBkNotExist(responsibilityGroup);
		valNonBk(responsibilityGroup);
		responsibilityGroupDao.create(responsibilityGroup);
	}
	
	public void update(ResponsibilityGroup responsibilityGroup) throws Exception {
		responsibilityGroup.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valIdNotNull(responsibilityGroup);
		valIdExist(responsibilityGroup.getId());
		valBkNotNull(responsibilityGroup);
		valBkNotChange(responsibilityGroup);
		valNonBk(responsibilityGroup);
		valCreatedNotChange(responsibilityGroup);
		responsibilityGroupDao.update(responsibilityGroup);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		responsibilityGroupDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!responsibilityGroupDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(ResponsibilityGroup responsibilityGroup)throws Exception {
		if(responsibilityGroup.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(ResponsibilityGroup responsibilityGroup)throws Exception{
		if(responsibilityGroup.getName().isEmpty()) {
			throw new Exception("Nama tidak boleh kosong");
		}
	}
	
	private void valBkNotExist(ResponsibilityGroup responsibilityGroup)throws Exception{
		if(responsibilityGroupDao.isBkExist(responsibilityGroup)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(ResponsibilityGroup responsibilityGroup)throws Exception{
		ResponsibilityGroup tempResponsibilityGroup=findById(responsibilityGroup.getId());

		if(!tempResponsibilityGroup.getCompany().getId().equals(responsibilityGroup.getCompany().getId()) || !tempResponsibilityGroup.getCode().equals(responsibilityGroup.getCode())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(ResponsibilityGroup responsibilityGroup) throws Exception{
		if(responsibilityGroup.getCompany().getId().isEmpty() || responsibilityGroup.getCode().isEmpty()) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(ResponsibilityGroup responsibilityGroup)throws Exception {
		ResponsibilityGroup tempResponsibilityGroup=findById(responsibilityGroup.getId());
		
		if(!tempResponsibilityGroup.getCreatedAt().equals(responsibilityGroup.getCreatedAt()) || !tempResponsibilityGroup.getCreatedBy().equals(responsibilityGroup.getCreatedBy())) {
			throw new Exception("created tidak boleh berubah");
		}
	}
}