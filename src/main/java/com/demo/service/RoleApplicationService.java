package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.RoleApplicationDao;
import com.demo.model.RoleApplication;

@Service
public class RoleApplicationService {

	@Autowired
	private RoleApplicationDao roleApplicationDao;
	
	public List<RoleApplication> findAll(){
        return roleApplicationDao.findAll();
    }

    public List<RoleApplication> findAll(Integer offset, Integer limit){
        return roleApplicationDao.findAll(offset,limit);
    }
    
    public RoleApplication findById(String id){
        return roleApplicationDao.findOne(id);
    }
        
    public void save(RoleApplication roleApplication) throws Exception {
    	roleApplication.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    	valBkNotNull(roleApplication);
		valBkNotExist(roleApplication);
		valNonBk(roleApplication);
		roleApplicationDao.create(roleApplication);
    }
    
    public void update(RoleApplication roleApplication) throws Exception {
    	roleApplication.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
    	valIdNotNull(roleApplication);
		valIdExist(roleApplication.getId());
		valBkNotNull(roleApplication);
		valBkNotChange(roleApplication);
		valNonBk(roleApplication);
		valCreatedNotChange(roleApplication);
		roleApplicationDao.update(roleApplication);
    }
    
	public void delete(String id) throws Exception {
		valIdExist(id);
		roleApplicationDao.deleteById(id);
	}
    
	private void valIdExist(String id)throws Exception{
		if(!roleApplicationDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(RoleApplication roleApplication)throws Exception {
		if(roleApplication.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(RoleApplication roleApplication)throws Exception{
		
	}
	
	private void valBkNotExist(RoleApplication roleApplication)throws Exception{
		if(roleApplicationDao.isBkExist(roleApplication)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(RoleApplication roleApplication)throws Exception{
		RoleApplication tempRoleApplication=findById(roleApplication.getId());
		if(!tempRoleApplication.getRole().getId().equals(roleApplication.getRole().getId()) || !tempRoleApplication.getApplication().getId().equals(roleApplication.getApplication().getId())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(RoleApplication roleApplication) throws Exception{
		if(roleApplication.getRole().getId().isEmpty() || roleApplication.getApplication().getId().isEmpty()) {
			throw new Exception("BK tidak boleh kosong");
		}
	}
	
	private void valCreatedNotChange(RoleApplication roleApplication)throws Exception {
		RoleApplication tempRoleApplication=findById(roleApplication.getId());
		if(!tempRoleApplication.getCreatedAt().equals(roleApplication.getCreatedAt()) || !tempRoleApplication.getCreatedBy().equals(roleApplication.getCreatedBy())) {
			throw new Exception("created tidak boleh berubah");
		}
	}
}
