package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ModuleDao;
import com.demo.exception.ValidationException;
import com.demo.model.Module;

@Service
public class ModuleService {

	@Autowired
	private ModuleDao moduleDao;
	
	public List<Module> findAll(){
        return moduleDao.findAll();
    }
	
	public List<Module> findAll(Integer offset,Integer limit){
        return moduleDao.findAll(offset, limit);
    }
	
	public Module findById(String id){
        return moduleDao.findOne(id);
    }
	
	public Module findByBk(String code){
		Module  module=new Module();
		module.setCode(code);
		return moduleDao.findByBk(module);
    }
	
	public void save(Module module) throws ValidationException {
		module.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(module);
		valBkNotExist(module);
		valNonBk(module);
		moduleDao.create(module);
	}
	
	public void update(Module module) throws ValidationException {
		module.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valIdNotNull(module);
		valIdExist(module.getId());
		valBkNotNull(module);
		valBkNotChange(module);
		valNonBk(module);
		valCreatedNotChange(module);
		moduleDao.update(module);
	}
	
	public void delete(String id) throws ValidationException {
		valIdExist(id);
		moduleDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws ValidationException{
		if(!moduleDao.isIdExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	private void valIdNotNull(Module module)throws ValidationException {
		if(module.getId().isEmpty()) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(Module module)throws ValidationException{
		List<String> listErr = new ArrayList<String>();

		if(module.getName().isEmpty()) {
			listErr.add("Name tidak boleh kosong");
		}
		if(module.getApplication().getId().isEmpty()) {
			listErr.add("Application tidak boleh kosong");
		}
		if(module.getSortOrder()==null) {
			listErr.add("Sort order tidak boleh kosong");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valBkNotExist(Module module)throws ValidationException{
		if(moduleDao.isBkExist(module)) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(Module module)throws ValidationException{
		Module tempModule=findById(module.getId());

		if(!tempModule.getCode().equals(module.getCode())) {
			throw new ValidationException("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(Module module) throws ValidationException{
		if(module.getCode().isEmpty()) {
			throw new ValidationException("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(Module module)throws ValidationException {
		Module tempModule=findById(module.getId());
		
		if(!tempModule.getCreatedAt().equals(module.getCreatedAt()) || !tempModule.getCreatedBy().equals(module.getCreatedBy())) {
			throw new ValidationException("created tidak boleh berubah");
		}
	}
}
