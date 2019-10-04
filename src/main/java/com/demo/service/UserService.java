<<<<<<< HEAD
package com.demo.service;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.dao.UserDao;
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
	
	public void save(User user) throws Exception {
		user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(user);
		valBkNotExist(user);
		valNonBk(user);
		user.setPassword(encryption.encrypt(user.getPassword()));
		userDao.create(user);
	}
	
	public void update(User user) throws Exception {
		user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valIdNotNull(user);
		valIdExist(user.getId());
		valBkNotNull(user);
		valBkNotChange(user);
		valNonBk(user);
		valCreatedNotChange(user);
		userDao.update(user);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		userDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!userDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(User user)throws Exception {
		if(user.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(User user)throws Exception{
		if(user.getEmail().isEmpty()) {
			throw new Exception("Email tidak boleh kosong !");
		}
		if(user.getPassword().isEmpty()) {
			throw new Exception("Password tidak boleh kosong !");
		}
		if(user.getIsDeleted() == null) {
			throw new Exception("IsDeleted tidak boleh kosong !");
		}
		if(user.getIsSa() == null) {
			throw new Exception("IsSa tidak boleh kosong !");
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
		if(user.getTenant().getId().isEmpty() || user.getUsername().isEmpty()) {
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
=======
package com.demo.service;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.dao.UserDao;
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
	
	public User findByLogin(String username,String password){
		return userDao.findByLogin(username, password);
    }

	public void save(User user) throws Exception {
		user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(user);
		valBkNotExist(user);
		valNonBk(user);
		user.setPassword(encryption.encrypt(user.getPassword()));
		userDao.create(user);
	}
	
	public void update(User user) throws Exception {
		user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valIdNotNull(user);
		valIdExist(user.getId());
		valBkNotNull(user);
		valBkNotChange(user);
		valNonBk(user);
		valCreatedNotChange(user);
		userDao.update(user);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		userDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!userDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(User user)throws Exception {
		if(user.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(User user)throws Exception{
		if(user.getEmail().isEmpty()) {
			throw new Exception("Email tidak boleh kosong !");
		}
		if(user.getPassword().isEmpty()) {
			throw new Exception("Password tidak boleh kosong !");
		}
		if(user.getIsDeleted() == null) {
			throw new Exception("IsDeleted tidak boleh kosong !");
		}
		if(user.getIsSa() == null) {
			throw new Exception("IsSa tidak boleh kosong !");
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
		if(user.getTenant().getId().isEmpty() || user.getUsername().isEmpty()) {
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
>>>>>>> 988415568ac77e17e2ccd17755967d8bef6ceb8b
