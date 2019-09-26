package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.SettingTypeDao;
import com.demo.model.SettingType;

@Service
public class SettingTypeService {

	@Autowired
	private SettingTypeDao settingTypeDao;
	
    public List<SettingType> findAll(){
        return settingTypeDao.findAll();
    }

    public SettingType findById(String id){
        return settingTypeDao.findOne(id);
    }
        
    public void save(SettingType settingType) throws Exception {
    	valBkNotNull(settingType);
		valBkNotExist(settingType);
		valNonBk(settingType);
		settingTypeDao.create(settingType);
    }
    
    public void update(SettingType settingType) throws Exception {
    	valIdNotNull(settingType);
		valIdExist(settingType.getId());
		valBkNotNull(settingType);
		valBkNotChange(settingType);
		valNonBk(settingType);
		settingTypeDao.update(settingType);
    }
    
	public void delete(String id) throws Exception {
		valIdExist(id);
		settingTypeDao.deleteById(id);
	}
    
	private void valIdExist(String id)throws Exception{
		if(!settingTypeDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(SettingType settingType)throws Exception {
		if(settingType.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(SettingType settingType)throws Exception{
		if(settingType.getName().isEmpty()) {
			throw new Exception("Name cannot be null !");
		}
		if(settingType.getType().isEmpty()) {
			throw new Exception("Type cannot be null!");
		}
		if(settingType.getVtype().isEmpty()) {
			throw new Exception("Vtype cannot be null!");
		}
	}
	
	private void valBkNotExist(SettingType settingType)throws Exception{
		if(settingTypeDao.isBkExist(settingType)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(SettingType settingType)throws Exception{
		SettingType tempSettingType=findById(settingType.getId());
		if(!tempSettingType.getCode().equals(settingType.getCode())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(SettingType settingType) throws Exception{
		if(settingType.getCode().isEmpty()) {
			throw new Exception("Kode tidak boleh kosong");
		}
	}
}
