package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UserSettingDao;
import com.demo.model.UserSetting;

@Service
public class UserSettingService {

	@Autowired
	private UserSettingDao userSettingDao;
	
    public List<UserSetting> findAll(){
        return userSettingDao.findAll();
    }

    public UserSetting findById(String id){
        return userSettingDao.findOne(id);
    }
        
    public void save(UserSetting userSetting) throws Exception {
    	valBkNotNull(userSetting);
		valBkNotExist(userSetting);
		valNonBk(userSetting);
		userSettingDao.create(userSetting);
    }
    
    public void update(UserSetting userSetting) throws Exception {
    	valIdNotNull(userSetting);
		valIdExist(userSetting.getId());
		valBkNotNull(userSetting);
		valBkNotChange(userSetting);
		valNonBk(userSetting);
		userSettingDao.update(userSetting);
    }
    
	public void delete(String id) throws Exception {
		valIdExist(id);
		userSettingDao.deleteById(id);
	}
    
	private void valIdExist(String id)throws Exception{
		if(!userSettingDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(UserSetting userSetting)throws Exception {
		if(userSetting.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(UserSetting userSetting)throws Exception{
		
	}
	
	private void valBkNotExist(UserSetting userSetting)throws Exception{
		if(userSettingDao.isBkExist(userSetting)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(UserSetting userSetting)throws Exception{
		UserSetting tempUserSetting=findById(userSetting.getId());
		if(!tempUserSetting.getUser().getId().equals(userSetting.getUser().getId()) || tempUserSetting.getSettingLov().getId().equals(userSetting.getSettingLov().getId())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(UserSetting userSetting) throws Exception{
		if(userSetting.getUser().getId().isEmpty() || userSetting.getSettingLov().getId().isEmpty()) {
			throw new Exception("Kode tidak boleh kosong");
		}
	}
}
