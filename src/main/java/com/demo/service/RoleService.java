package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.RoleDao;
import com.demo.exception.ValidationException;
import com.demo.model.Role;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	public List<Role> findAll(){
        return roleDao.findAll();
    }
	
	public List<Role> findAll(Integer offset,Integer limit){
        return roleDao.findAll(offset, limit);
    }
	
	public Role findById(String id){
        return roleDao.findOne(id);
    }
	
	public Role findByBk(String companyId,String name){
		
		return roleDao.findByBk(companyId, name);
    }
	
	public void save(Role role) throws Exception {
		role.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(role);
		valBkNotExist(role);
		valNonBk(role);
		roleDao.create(role);
	}
	
	public void update(Role role) throws Exception {
		role.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valIdNotNull(role);
		valIdExist(role.getId());
		valBkNotNull(role);
		valBkNotChange(role);
		valNonBk(role);
		valCreatedNotChange(role);
		roleDao.update(role);
	}
		
	public void delete(String id) throws Exception {
		valIdExist(id);
		roleDao.deleteById(id);
	}

	private void valIdExist(String id)throws Exception{
		if(!roleDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(Role role)throws Exception {
		if(role.getId()==null) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(Role role)throws Exception{
		List<String> listErr = new ArrayList<String>();
		if(role.getIsDeleted() == null) {
			listErr.add("IsDeleted tidak boleh kosong");
		}
		if(role.getVersion() == null) {
			listErr.add("Version tidak boleh kosong");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	private void valBkNotExist(Role role)throws Exception{
		if(roleDao.isBkExist(role)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(Role role)throws Exception{
		Role tempRole=findById(role.getId());

		if(!tempRole.getCompany().getId().equals(role.getCompany().getId()) || !tempRole.getName().equals(role.getName())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(Role role) throws Exception{
		if(role.getCompany().getId() == null || role.getName() == null) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(Role role)throws Exception {
		Role tempRole=findById(role.getId());
		
		if(!tempRole.getCreatedAt().equals(role.getCreatedAt()) || !tempRole.getCreatedBy().equals(role.getCreatedBy())) {
			throw new Exception("created tidak boleh berubah");
		}
	}
}
