package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.DataAccessDao;
import com.demo.exception.ValidationException;
import com.demo.model.DataAccess;

@Service
public class DataAccessService {

	@Autowired
	private DataAccessDao dataAccessDao;

	public void valIdExist(String id) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if (!dataAccessDao.isIdExist(id)) {
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

	public void valNonBk(DataAccess dataAccess) throws Exception {
		List<String> listErr = new ArrayList<String>();

		if (dataAccess.getName().isEmpty()) {
			listErr.add("name cannot be emptied");
		}
		if (dataAccess.getCode().isEmpty()) {
			listErr.add("code cannot be emptied");
		}
		if (dataAccess.getCreatedBy().isEmpty()) {
			listErr.add("created by cannot be emptied");
		}
		if (dataAccess.getCreatedAt() == null) {
			listErr.add("created at cannot be emptied");
		}
		if (dataAccess.getVersion() == null) {
			listErr.add("version cannot be emptied");
		}

		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valCreatedNotChange(DataAccess dataAccess) throws Exception {
		DataAccess posDB = findById(dataAccess.getId());
		List<String> listErr = new ArrayList<String>();
		
		if (posDB.getCreatedAt() != dataAccess.getCreatedAt() || !posDB.getCreatedBy().equals(dataAccess.getCreatedBy())) {
			listErr.add("created at or created by cannot be changed");
		}
		
		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}

	public List<DataAccess> findAll() {
		return dataAccessDao.findAll();
	}

	public DataAccess findById(String id) {
		return dataAccessDao.findOne(id);
	}

	public void save(DataAccess dataAccess) throws Exception {
		dataAccess.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(dataAccess);

		dataAccessDao.create(dataAccess);
	}

	public void update(DataAccess dataAccess) throws Exception {
		valIdNotNull(dataAccess.getId());
		valIdExist(dataAccess.getId());
		dataAccess.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(dataAccess);

		dataAccessDao.update(dataAccess);
	}

	public void delete(String id) throws Exception {
		valIdExist(id);
		
		dataAccessDao.deleteById(id);
	}
}
