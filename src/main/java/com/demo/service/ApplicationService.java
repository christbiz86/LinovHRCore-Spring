package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ApplicationDao;
import com.demo.exception.ValidationException;
import com.demo.model.Application;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationDao applicationDao;
	
	public List<Application> findAll(){
        return applicationDao.findAll();
    }
	
	public List<Application> findAll(Integer offset,Integer limit){
        return applicationDao.findAll(offset, limit);
    }
	
	public Application findById(String id){
        return applicationDao.findOne(id);
    }
	
	public Application findByBk(String code){
		Application application=new Application();
		application.setCode(code);
		return applicationDao.findByBk(application);
    }
	
	public void save(Application application) throws Exception {
		valBkNotNull(application);
		valBkNotExist(application);
		valNonBk(application);
		applicationDao.create(application);
	}
	
	public void update(Application application) throws Exception {
		valIdNotNull(application);
		valIdExist(application.getId());
		valBkNotNull(application);
		valBkNotChange(application);
		valNonBk(application);
		applicationDao.update(application);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		applicationDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!applicationDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(Application application)throws Exception {
		if(application.getId()==null) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(Application application)throws Exception{
		List<String> listErr = new ArrayList<String>();
	
		if(application.getName() == null) {
			listErr.add("Nama tidak boleh kosong");
		}
		if(application.getUserAccess() == null) {
			listErr.add("User access tidak boleh kosong");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valBkNotExist(Application application)throws Exception{
		if(applicationDao.isBkExist(application)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(Application application)throws Exception{
		Application tempApplication=findById(application.getId());

		if(!tempApplication.getCode().equals(application.getCode())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(Application application) throws Exception{
		if(application.getCode() == null ) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
}
