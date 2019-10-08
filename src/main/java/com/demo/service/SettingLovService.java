package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.SettingLovDao;
import com.demo.model.SettingLov;

@Service
public class SettingLovService {

	@Autowired
	private SettingLovDao settingLovDao;
	
    public List<SettingLov> findAll(){
        return settingLovDao.findAll();
    }

    public SettingLov findById(String id){
        return settingLovDao.findOne(id);
    }
        
    public void save(SettingLov settingLov) throws Exception {
    	valBkNotNull(settingLov);
		valBkNotExist(settingLov);
		valNonBk(settingLov);
		settingLovDao.create(settingLov);
    }
    
    public void update(SettingLov settingLov) throws Exception {
    	valIdNotNull(settingLov);
		valIdExist(settingLov.getId());
		valBkNotNull(settingLov);
		valBkNotChange(settingLov);
		valNonBk(settingLov);
		settingLovDao.update(settingLov);
    }
    
	public void delete(String id) throws Exception {
		valIdExist(id);
		settingLovDao.deleteById(id);
	}
    
	private void valIdExist(String id)throws Exception{
		if(!settingLovDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(SettingLov settingLov)throws Exception {
		if(settingLov.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(SettingLov settingLov)throws Exception{
		if(settingLov.getValData().isEmpty()) {
			throw new Exception("Value Data cannot be null !");
		}
	}
	
	private void valBkNotExist(SettingLov settingLov)throws Exception{
		if(settingLovDao.isBkExist(settingLov)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(SettingLov settingLov)throws Exception{
		SettingLov tempSettingLov=findById(settingLov.getId());
		if(!tempSettingLov.getSettingType().getId().equals(settingLov.getSettingType().getId()) || tempSettingLov.getKeyData().equals(settingLov.getKeyData())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(SettingLov settingLov) throws Exception{
		if(settingLov.getSettingType().getId().isEmpty() || settingLov.getKeyData().isEmpty()) {
			throw new Exception("Kode tidak boleh kosong");
		}
	}
}
