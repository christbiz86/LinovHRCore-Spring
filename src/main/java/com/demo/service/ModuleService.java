package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ModuleDao;
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
	
	public void save(Module module) throws Exception {
		module.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(module);
		valBkNotExist(module);
		valNonBk(module);
		moduleDao.create(module);
	}
	
	public void update(Module module) throws Exception {
		module.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valIdNotNull(module);
		valIdExist(module.getId());
		valBkNotNull(module);
		valBkNotChange(module);
		valNonBk(module);
		valCreatedNotChange(module);
		moduleDao.update(module);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		moduleDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!moduleDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(Module module)throws Exception {
		if(module.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(Module module)throws Exception{
		if(module.getName().isEmpty()) {
		    throw new Exception("Name tidak boleh kosong");
		}
		if(module.getApplication().getId().isEmpty()) {
            throw new Exception("Application tidak boleh kosong");
		}
		if(module.getSortOrder()==null) {
            throw new Exception("Sort order tidak boleh kosong");
		}
	}
	
	private void valBkNotExist(Module module)throws Exception{
		if(moduleDao.isBkExist(module)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(Module module)throws Exception{
		Module tempModule=findById(module.getId());

		if(!tempModule.getCode().equals(module.getCode())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(Module module) throws Exception{
		if(module.getCode().isEmpty()) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(Module module)throws Exception {
		Module tempModule=findById(module.getId());
		
		if(!tempModule.getCreatedAt().equals(module.getCreatedAt()) || !tempModule.getCreatedBy().equals(module.getCreatedBy())) {
			throw new Exception("created tidak boleh berubah");
		}
	}
}
