package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UserRoleDao;
import com.demo.model.UserRole;

@Service
public class UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;
	
	public List<UserRole> findAll(){
        return userRoleDao.findAll();
    }
	
	public List<UserRole> findAll(Integer offset,Integer limit){
        return userRoleDao.findAll(offset, limit);
    }
	
	public UserRole findById(String id){
        return userRoleDao.findOne(id);
    }
	
	public UserRole findByBk(String userId,String roleId){
		UserRole userRole=new UserRole();
		userRole.getUser().setId(userId);
		userRole.getRole().setId(roleId);
		return userRoleDao.findByBk(userRole);
    }
	
	public void save(UserRole userRole) throws Exception {
		userRole.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(userRole);
		valBkNotExist(userRole);
		valNonBk(userRole);
		userRoleDao.create(userRole);
	}
	
	public void update(UserRole userRole) throws Exception {
		valIdNotNull(userRole);
		valIdExist(userRole.getId());
		valBkNotNull(userRole);
		valBkNotChange(userRole);
		valNonBk(userRole);
		valCreatedNotChange(userRole);
		userRoleDao.update(userRole);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		userRoleDao.deleteById(id);
	}

	
	private void valIdExist(String id)throws Exception{
		if(!userRoleDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(UserRole userRole)throws Exception {
		if(userRole.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(UserRole userRole)throws Exception{
		if(userRole.getIsActive()==null) {
			throw new Exception("isActive tidak boleh kosong");
		}
	}
	
	private void valBkNotExist(UserRole userRole)throws Exception{
		if(userRoleDao.isBkExist(userRole)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(UserRole userRole)throws Exception{
		UserRole tempUserRole=findById(userRole.getId());

		if(!tempUserRole.getUser().getId().equals(userRole.getUser().getId()) || !tempUserRole.getRole().getId().equals(userRole.getRole().getId())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(UserRole userRole) throws Exception{
		if(userRole.getUser().getId().isEmpty() || userRole.getRole().getId().isEmpty()) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(UserRole userRole)throws Exception {
		UserRole tempUserRole=findById(userRole.getId());
		
		if(!tempUserRole.getCreatedAt().equals(userRole.getCreatedAt()) || !tempUserRole.getCreatedBy().equals(userRole.getCreatedBy())) {
			throw new Exception("created tidak boleh berubah");
		}
	}
}
