package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.DataAccessUniDao;
import com.demo.model.DataAccessUni;

@Service
public class DataAccessUniService {

	@Autowired
	private DataAccessUniDao dataAccessUniDao;

	public void valIdExist(String id) throws Exception {	
		if (!dataAccessUniDao.isIdExist(id)) {
			throw new Exception("Data does not exist");
		}
	}

	public void valIdNotNull(String id) throws Exception {
		if (id.isEmpty()) {
			throw new Exception("Id cannot be emptied");
		}
	}

	public void valNonBk(DataAccessUni dataAccessUni) throws Exception {
		if (dataAccessUni.getCompany() != null) {
			if (dataAccessUni.getCompany().getId().isEmpty()) {
				throw new Exception("company cannot be emptied");
			}
		}
		if (dataAccessUni.getRole() != null) {
			if (dataAccessUni.getRole().getId().isEmpty()) {
				throw new Exception("role cannot be emptied");
			}
		}
		if (dataAccessUni.getMenuCode().isEmpty()) {
			throw new Exception("menu code cannot be emptied");
		}
		if (dataAccessUni.getDataAccValue().isEmpty()) {
			throw new Exception("data access value cannot be emptied");
		}
		if (dataAccessUni.getPrivilege().isEmpty()) {
			throw new Exception("privilege cannot be emptied");
		}
		if (dataAccessUni.getCreatedBy().isEmpty()) {
			throw new Exception("created by cannot be emptied");
		}
		if (dataAccessUni.getCreatedAt() == null) {
			throw new Exception("created at cannot be emptied");
		}
		if (dataAccessUni.getVersion() == null) {
			throw new Exception("version cannot be emptied");
		}
	}
	
	public void valCreatedNotChange(DataAccessUni dataAccessUni) throws Exception {
		DataAccessUni dataAccDB = findById(dataAccessUni.getId());
		
		if (dataAccDB.getCreatedAt() != dataAccessUni.getCreatedAt() || !dataAccDB.getCreatedBy().equals(dataAccessUni.getCreatedBy())) {
			throw new Exception("created at or created by cannot be changed");
		}
	}

	public List<DataAccessUni> findAll() {
		return dataAccessUniDao.findAll();
	}

	public DataAccessUni findById(String id) {
		return dataAccessUniDao.findOne(id);
	}

	public void save(DataAccessUni dataAccessUni) throws Exception {
		dataAccessUni.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(dataAccessUni);

		dataAccessUniDao.create(dataAccessUni);
	}

	public void update(DataAccessUni dataAccessUni) throws Exception {
		valIdNotNull(dataAccessUni.getId());
		valIdExist(dataAccessUni.getId());
		dataAccessUni.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(dataAccessUni);

		dataAccessUniDao.update(dataAccessUni);
	}

	public void delete(String id) throws Exception {
		valIdExist(id);
		
		dataAccessUniDao.deleteById(id);
	}
}
