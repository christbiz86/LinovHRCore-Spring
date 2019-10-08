package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.RoleDataAccessDao;
import com.demo.model.RoleDataAccess;

@Service
public class RoleDataAccessService {

	@Autowired
	private RoleDataAccessDao roleDataAccessDao;

	public void valIdExist(String id) throws Exception {
		if (!roleDataAccessDao.isIdExist(id)) {
			throw new Exception("Data does not exist");
		}
	}

	public void valIdNotNull(String id) throws Exception {	
		if (id.isEmpty()) {
			throw new Exception("Id cannot be emptied");
		}
	}

	public void valNonBk(RoleDataAccess roleDataAccess) throws Exception {
		if (roleDataAccess.getTenant() != null) {
			if (roleDataAccess.getTenant().getId().isEmpty()) {
				throw new Exception("tenant cannot be emptied");
			}
		}
		if (roleDataAccess.getRole() != null) {
			if (roleDataAccess.getRole().getId().isEmpty()) {
				throw new Exception("role cannot be emptied");
			}
		}
		if (roleDataAccess.getDataAccess() != null) {
			if (roleDataAccess.getDataAccess().getId().isEmpty()) {
				throw new Exception("data access cannot be emptied");
			}
		}
		if (roleDataAccess.getDataAccValue().isEmpty()) {
			throw new Exception("data access value cannot be emptied");
		}
		if (roleDataAccess.getPrivilege().isEmpty()) {
			throw new Exception("privilege cannot be emptied");
		}
		if (roleDataAccess.getCreatedBy().isEmpty()) {
			throw new Exception("created by cannot be emptied");
		}
		if (roleDataAccess.getCreatedAt() == null) {
			throw new Exception("created at cannot be emptied");
		}
		if (roleDataAccess.getVersion() == null) {
			throw new Exception("version cannot be emptied");
		}
	}
	
	public void valCreatedNotChange(RoleDataAccess roleDataAccess) throws Exception {
		RoleDataAccess roleDataAccDB = findById(roleDataAccess.getId());
		
		if (roleDataAccDB.getCreatedAt() != roleDataAccess.getCreatedAt() || !roleDataAccDB.getCreatedBy().equals(roleDataAccess.getCreatedBy())) {
			throw new Exception("created at or created by cannot be changed");
		}
	}

	public List<RoleDataAccess> findAll() {
		return roleDataAccessDao.findAll();
	}

	public RoleDataAccess findById(String id) {
		return roleDataAccessDao.findOne(id);
	}

	public void save(RoleDataAccess roleDataAccess) throws Exception {
		roleDataAccess.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(roleDataAccess);

		roleDataAccessDao.create(roleDataAccess);
	}

	public void update(RoleDataAccess roleDataAccess) throws Exception {
		valIdNotNull(roleDataAccess.getId());
		valIdExist(roleDataAccess.getId());
		roleDataAccess.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(roleDataAccess);

		roleDataAccessDao.update(roleDataAccess);
	}

	public void delete(String id) throws Exception {
		valIdExist(id);
		
		roleDataAccessDao.deleteById(id);
	}
}
