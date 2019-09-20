package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UserDao;
import com.demo.model.Tenant;
import com.demo.model.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public List<User> findAll(Integer offset,Integer limit){
        return userDao.findAll(offset, limit);
    }
	
	public User findById(String id){
        return userDao.findById(id);
    }
	
	public User findByBk(String tenantId,String username){
		User user=new User();
		Tenant tenant=new Tenant();
		tenant.setId(tenantId);
		user.setTenant(tenant);
		user.setUsername(username);
		return userDao.findByBk(user);
    }
	
	public void save(User user) throws Exception {
		user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(user);
		valBkNotExist(user);
		valNonBk(user);
		userDao.save(user);
	}
	
	public void update(User user) throws Exception {
		user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valIdNotNull(user);
		valIdExist(user.getId());
		valBkNotNull(user);
		valBkNotChange(user);
		valNonBk(user);
		valCreatedNotChange(user);
		userDao.save(user);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!userDao.isExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(User user)throws Exception {
		if(user.getId()==null) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(User user)throws Exception{
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(user.getEmail()==null) {
			sb.append("Email cannot be null !");
			error++;
		}
		if(user.getPassword()==null) {
			sb.append("Password cannot be null !");
			error++;
		}
		if(user.getIsDeleted() == null) {
			sb.append("IsDeleted cannot be null !");
			error++;
		}
		if(user.getIsSa() == null) {
			sb.append("IsSa cannot be null !");
			error++;
		}
				
		if(error>0) {
			throw new Exception(sb.toString());
		}
	}
	
	private void valBkNotExist(User user)throws Exception{
		if(userDao.isBkExist(user)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(User user)throws Exception{
		User tempUser=findById(user.getId());

		if(!tempUser.getTenant().getId().equals(user.getTenant().getId()) || !tempUser.getUsername().equals(user.getUsername())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(User user) throws Exception{
		if(user.getTenant().getId() == null || user.getUsername() == null) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(User user)throws Exception {
		User tempUser=findById(user.getId());
		
		if(!tempUser.getCreatedAt().equals(user.getCreatedAt()) || !tempUser.getCreatedBy().equals(user.getCreatedBy())) {
			throw new Exception("created tidak boleh berubah");
		}
	}
}
