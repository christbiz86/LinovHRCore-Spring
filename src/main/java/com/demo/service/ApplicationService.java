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
	
	public void save(Application application) throws ValidationException {
		valBkNotNull(application);
		valBkNotExist(application);
		valNonBk(application);
		applicationDao.create(application);
	}
	
	public void update(Application application) throws ValidationException {
		valIdNotNull(application);
		valIdExist(application.getId());
		valBkNotNull(application);
		valBkNotChange(application);
		valNonBk(application);
		valCreatedNotChange(application);
		applicationDao.update(application);
	}
	
	public void delete(String id) throws ValidationException {
		valIdExist(id);
		applicationDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws ValidationException{
		if(!applicationDao.isIdExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	private void valIdNotNull(Application application)throws ValidationException {
		if(application.getId().isEmpty()) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(Application application)throws ValidationException{
		List<String> listErr = new ArrayList<String>();
	
		if(application.getName().isEmpty()) {
			listErr.add("Nama tidak boleh kosong");
		}
		if(application.getUserAccess() == null) {
			listErr.add("User access tidak boleh kosong");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valBkNotExist(Application application)throws ValidationException{
		if(applicationDao.isBkExist(application)) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(Application application)throws ValidationException{
		Application tempApplication=findById(application.getId());

		if(!tempApplication.getCode().equals(application.getCode())) {
			throw new ValidationException("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(Application application) throws ValidationException{
		if(application.getCode().isEmpty() ) {
			throw new ValidationException("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(Application application)throws ValidationException {
		Application tempApplication=findById(application.getId());
		
		if(!tempApplication.getCreatedAt().equals(application.getCreatedAt()) || !tempApplication.getCreatedBy().equals(application.getCreatedBy())) {
			throw new ValidationException("created tidak boleh berubah");
		}
	}
}
