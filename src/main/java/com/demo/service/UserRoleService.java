package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UserRoleDao;
import com.demo.exception.ValidationException;
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
	
	public void save(UserRole userRole) throws ValidationException {
		userRole.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(userRole);
		valBkNotExist(userRole);
		valNonBk(userRole);
		userRoleDao.create(userRole);
	}
	
	public void update(UserRole userRole) throws ValidationException {
		valIdNotNull(userRole);
		valIdExist(userRole.getId());
		valBkNotNull(userRole);
		valBkNotChange(userRole);
		valNonBk(userRole);
		valCreatedNotChange(userRole);
		userRoleDao.update(userRole);
	}
	
	public void delete(String id) throws ValidationException {
		valIdExist(id);
		userRoleDao.deleteById(id);
	}

	
	private void valIdExist(String id)throws ValidationException{
		if(!userRoleDao.isIdExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	private void valIdNotNull(UserRole userRole)throws ValidationException {
		if(userRole.getId().isEmpty()) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(UserRole userRole)throws ValidationException{
		List<String> listErr = new ArrayList<String>();
		if(userRole.getIsActive()==null) {
			listErr.add("isActive tidak boleh kosong");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valBkNotExist(UserRole userRole)throws ValidationException{
		if(userRoleDao.isBkExist(userRole)) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(UserRole userRole)throws ValidationException{
		UserRole tempUserRole=findById(userRole.getId());

		if(!tempUserRole.getUser().getId().equals(userRole.getUser().getId()) || !tempUserRole.getRole().getId().equals(userRole.getRole().getId())) {
			throw new ValidationException("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(UserRole userRole) throws ValidationException{
		if(userRole.getUser().getId().isEmpty() || userRole.getRole().getId().isEmpty()) {
			throw new ValidationException("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(UserRole userRole)throws ValidationException {
		UserRole tempUserRole=findById(userRole.getId());
		
		if(!tempUserRole.getCreatedAt().equals(userRole.getCreatedAt()) || !tempUserRole.getCreatedBy().equals(userRole.getCreatedBy())) {
			throw new ValidationException("created tidak boleh berubah");
		}
	}
}
