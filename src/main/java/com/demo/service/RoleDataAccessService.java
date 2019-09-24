package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.RoleDataAccessDao;
import com.demo.exception.ValidationException;
import com.demo.model.RoleDataAccess;

@Service
public class RoleDataAccessService {

	@Autowired
	private RoleDataAccessDao roleDataAccessDao;

	public void valIdExist(String id) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if (!roleDataAccessDao.isIdExist(id)) {
			listErr.add("Data does not exist");
		}
		
		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}

	public void valIdNotNull(String id) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if (id.isEmpty()) {
			listErr.add("Id cannot be emptied");
		}
		
		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}

	public void valNonBk(RoleDataAccess roleDataAccess) throws Exception {
		List<String> listErr = new ArrayList<String>();

		if (roleDataAccess.getTenant() != null) {
			if (roleDataAccess.getTenant().getId().isEmpty()) {
				listErr.add("tenant cannot be emptied");
			}
		}
		if (roleDataAccess.getRole() != null) {
			if (roleDataAccess.getRole().getId().isEmpty()) {
				listErr.add("role cannot be emptied");
			}
		}
		if (roleDataAccess.getDataAccess() != null) {
			if (roleDataAccess.getDataAccess().getId().isEmpty()) {
				listErr.add("data access cannot be emptied");
			}
		}
		if (roleDataAccess.getDataAccValue().isEmpty()) {
			listErr.add("data access value cannot be emptied");
		}
		if (roleDataAccess.getPrivilege().isEmpty()) {
			listErr.add("privilege cannot be emptied");
		}
		if (roleDataAccess.getCreatedBy().isEmpty()) {
			listErr.add("created by cannot be emptied");
		}
		if (roleDataAccess.getCreatedAt() == null) {
			listErr.add("created at cannot be emptied");
		}
		if (roleDataAccess.getVersion() == null) {
			listErr.add("version cannot be emptied");
		}

		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valCreatedNotChange(RoleDataAccess roleDataAccess) throws Exception {
		RoleDataAccess posDB = findById(roleDataAccess.getId());
		List<String> listErr = new ArrayList<String>();
		
		if (posDB.getCreatedAt() != roleDataAccess.getCreatedAt() || !posDB.getCreatedBy().equals(roleDataAccess.getCreatedBy())) {
			listErr.add("created at or created by cannot be changed");
		}
		
		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
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
