package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ResponsibilityDao;
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
	
	public void save(Responsibility responsibility) throws Exception {
		responsibility.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(responsibility);
		valBkNotExist(responsibility);
		valNonBk(responsibility);
		responsibilityDao.create(responsibility);
	}
	
	public void update(Responsibility responsibility) throws Exception {
		responsibility.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valIdNotNull(responsibility);
		valIdExist(responsibility.getId());
		valBkNotNull(responsibility);
		valBkNotChange(responsibility);
		valNonBk(responsibility);
		valCreatedNotChange(responsibility);
		responsibilityDao.update(responsibility);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		responsibilityDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!responsibilityDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(Responsibility responsibility)throws Exception {
		if(responsibility.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(Responsibility responsibility)throws Exception{
		if(responsibility.getName().isEmpty()) {
			throw new Exception("Nama tidak boleh kosong");
		}
	}
	
	private void valBkNotExist(Responsibility responsibility)throws Exception{
		if(responsibilityDao.isBkExist(responsibility)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(Responsibility responsibility)throws Exception{
		Responsibility tempResponsibility=findById(responsibility.getId());

		if(!tempResponsibility.getCompany().getId().equals(responsibility.getCompany().getId()) || !tempResponsibility.getCode().equals(responsibility.getCode())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(Responsibility responsibility) throws Exception{
		if(responsibility.getCompany().getId().isEmpty() || responsibility.getCode().isEmpty()) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(Responsibility responsibility)throws Exception {
		Responsibility tempResponsibility=findById(responsibility.getId());
		
		if(!tempResponsibility.getCreatedAt().equals(responsibility.getCreatedAt()) || !tempResponsibility.getCreatedBy().equals(responsibility.getCreatedBy())) {
			throw new Exception("created tidak boleh berubah");
		}
	}
}
