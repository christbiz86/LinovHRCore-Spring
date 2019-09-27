package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.DataAccessDao;
import com.demo.model.DataAccess;

@Service
public class DataAccessService {

	@Autowired
	private DataAccessDao dataAccessDao;

	public void valIdExist(String id) throws Exception {	
		if (!dataAccessDao.isIdExist(id)) {
			throw new Exception("Data does not exist");
		}
	}

	public void valIdNotNull(String id) throws Exception {
		if (id.isEmpty()) {
			throw new Exception("Id cannot be emptied");
		}
	}

	public void valNonBk(DataAccess dataAccess) throws Exception {
		if (dataAccess.getName().isEmpty()) {
			throw new Exception("name cannot be emptied");
		}
		if (dataAccess.getCode().isEmpty()) {
			throw new Exception("code cannot be emptied");
		}
		if (dataAccess.getCreatedBy().isEmpty()) {
			throw new Exception("created by cannot be emptied");
		}
		if (dataAccess.getCreatedAt() == null) {
			throw new Exception("created at cannot be emptied");
		}
		if (dataAccess.getVersion() == null) {
			throw new Exception("version cannot be emptied");
		}
	}
	
	public void valCreatedNotChange(DataAccess dataAccess) throws Exception {
		DataAccess dataAccDB = findById(dataAccess.getId());
		
		if (dataAccDB.getCreatedAt() != dataAccess.getCreatedAt() || !dataAccDB.getCreatedBy().equals(dataAccess.getCreatedBy())) {
			throw new Exception("created at or created by cannot be changed");
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
