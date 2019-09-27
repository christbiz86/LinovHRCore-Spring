package com.demo.service;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.dao.UserDao;
import com.demo.exception.ValidationException;
import com.demo.helper.Encryption;
import com.demo.model.Tenant;
import com.demo.model.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private Encryption encryption;
	
	public List<User> findAll(){
        return userDao.findAll();
    }
	
	public List<User> findAll(Integer offset,Integer limit){
        return userDao.findAll(offset, limit);
    }
	
	public User findById(String id){
        return userDao.findOne(id);
    }
	
	public User findByBk(String tenantId,String username){
		User user=new User();
		Tenant tenant=new Tenant();
		tenant.setId(tenantId);
		user.setTenant(tenant);
		user.setUsername(username);
		return userDao.findByBk(user);
    }
	
	public void save(User user) throws ValidationException {
		user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(user);
		valBkNotExist(user);
		valNonBk(user);
		user.setPassword(encryption.encrypt(user.getPassword()));
		userDao.create(user);
	}
	
	public void update(User user) throws ValidationException {
		user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valIdNotNull(user);
		valIdExist(user.getId());
		valBkNotNull(user);
		valBkNotChange(user);
		valNonBk(user);
		valCreatedNotChange(user);
		userDao.update(user);
	}
	
	public void delete(String id) throws ValidationException {
		valIdExist(id);
		userDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws ValidationException{
		if(!userDao.isIdExist(id)) {
			throw new ValidationException("Data tidak ada");
		}
	}
	
	private void valIdNotNull(User user)throws ValidationException {
		if(user.getId().isEmpty()) {
			throw new ValidationException("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(User user)throws ValidationException{
		StringBuilder sb=new StringBuilder();
		int error=0;

		if(user.getEmail().isEmpty()) {
			sb.append("Email tidak boleh kosong !");
			error++;
		}
		if(user.getPassword().isEmpty()) {
			sb.append("Password tidak boleh kosong !");
			error++;
		}
		if(user.getIsDeleted() == null) {
			sb.append("IsDeleted tidak boleh kosong !");
			error++;
		}
		if(user.getIsSa() == null) {
			sb.append("IsSa tidak boleh kosong !");
			error++;
		}
		
		if(error>0) {
			throw new ValidationException(sb.toString());
		}
	}
	
	private void valBkNotExist(User user)throws ValidationException{
		if(userDao.isBkExist(user)) {
			throw new ValidationException("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(User user)throws ValidationException{
		User tempUser=findById(user.getId());

		if(!tempUser.getTenant().getId().equals(user.getTenant().getId()) || !tempUser.getUsername().equals(user.getUsername())) {
			throw new ValidationException("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(User user) throws ValidationException{
		if(user.getTenant().getId().isEmpty() || user.getUsername().isEmpty()) {
			throw new ValidationException("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(User user)throws ValidationException {
		User tempUser=findById(user.getId());
		
		if(!tempUser.getCreatedAt().equals(user.getCreatedAt()) || !tempUser.getCreatedBy().equals(user.getCreatedBy())) {
			throw new ValidationException("created tidak boleh berubah");
		}
	}
}
