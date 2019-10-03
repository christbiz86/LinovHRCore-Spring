package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.DataAccessPosDao;
import com.demo.model.DataAccessPos;

@Service
public class DataAccessPosService {

	@Autowired
	private DataAccessPosDao dataAccessPosDao;

	public void valIdExist(String id) throws Exception {	
		if (!dataAccessPosDao.isIdExist(id)) {
			throw new Exception("Data does not exist");
		}
	}

	public void valIdNotNull(String id) throws Exception {
		if (id.isEmpty()) {
			throw new Exception("Id cannot be emptied");
		}
	}

	public void valNonBk(DataAccessPos dataAccessPos) throws Exception {
		if (dataAccessPos.getCompany() != null) {
			if (dataAccessPos.getCompany().getId().isEmpty()) {
				throw new Exception("company cannot be emptied");
			}
		}
		if (dataAccessPos.getRole() != null) {
			if (dataAccessPos.getRole().getId().isEmpty()) {
				throw new Exception("role cannot be emptied");
			}
		}
		if (dataAccessPos.getMenuCode().isEmpty()) {
			throw new Exception("menu code cannot be emptied");
		}
		if (dataAccessPos.getDataAccValue().isEmpty()) {
			throw new Exception("data access value cannot be emptied");
		}
		if (dataAccessPos.getPrivilege().isEmpty()) {
			throw new Exception("privilege cannot be emptied");
		}
		if (dataAccessPos.getCreatedBy().isEmpty()) {
			throw new Exception("created by cannot be emptied");
		}
		if (dataAccessPos.getCreatedAt() == null) {
			throw new Exception("created at cannot be emptied");
		}
		if (dataAccessPos.getVersion() == null) {
			throw new Exception("version cannot be emptied");
		}
	}
	
	public void valCreatedNotChange(DataAccessPos dataAccessPos) throws Exception {
		DataAccessPos dataAccDB = findById(dataAccessPos.getId());
		
		if (dataAccDB.getCreatedAt() != dataAccessPos.getCreatedAt() || !dataAccDB.getCreatedBy().equals(dataAccessPos.getCreatedBy())) {
			throw new Exception("created at or created by cannot be changed");
		}
	}

	public List<DataAccessPos> findAll() {
		return dataAccessPosDao.findAll();
	}

	public DataAccessPos findById(String id) {
		return dataAccessPosDao.findOne(id);
	}

	public void save(DataAccessPos dataAccessPos) throws Exception {
		dataAccessPos.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(dataAccessPos);

		dataAccessPosDao.create(dataAccessPos);
	}

	public void update(DataAccessPos dataAccessPos) throws Exception {
		valIdNotNull(dataAccessPos.getId());
		valIdExist(dataAccessPos.getId());
		dataAccessPos.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(dataAccessPos);

		dataAccessPosDao.update(dataAccessPos);
	}

	public void delete(String id) throws Exception {
		valIdExist(id);
		
		dataAccessPosDao.deleteById(id);
	}
}
