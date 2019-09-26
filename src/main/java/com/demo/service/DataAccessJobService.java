package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.DataAccessJobDao;
import com.demo.model.DataAccessJob;

@Service
public class DataAccessJobService {

	@Autowired
	private DataAccessJobDao dataAccessJobDao;

	public void valIdExist(String id) throws Exception {	
		if (!dataAccessJobDao.isIdExist(id)) {
			throw new Exception("Data does not exist");
		}
	}

	public void valIdNotNull(String id) throws Exception {
		if (id.isEmpty()) {
			throw new Exception("Id cannot be emptied");
		}
	}

	public void valNonBk(DataAccessJob dataAccessJob) throws Exception {
		if (dataAccessJob.getCompany() != null) {
			if (dataAccessJob.getCompany().getId().isEmpty()) {
				throw new Exception("company cannot be emptied");
			}
		}
		if (dataAccessJob.getRole() != null) {
			if (dataAccessJob.getRole().getId().isEmpty()) {
				throw new Exception("role cannot be emptied");
			}
		}
		if (dataAccessJob.getMenuCode().isEmpty()) {
			throw new Exception("menu code cannot be emptied");
		}
		if (dataAccessJob.getDataAccValue().isEmpty()) {
			throw new Exception("data access value cannot be emptied");
		}
		if (dataAccessJob.getPrivilege().isEmpty()) {
			throw new Exception("privilege cannot be emptied");
		}
		if (dataAccessJob.getCreatedBy().isEmpty()) {
			throw new Exception("created by cannot be emptied");
		}
		if (dataAccessJob.getCreatedAt() == null) {
			throw new Exception("created at cannot be emptied");
		}
		if (dataAccessJob.getVersion() == null) {
			throw new Exception("version cannot be emptied");
		}
	}
	
	public void valCreatedNotChange(DataAccessJob dataAccessJob) throws Exception {
		DataAccessJob dataAccDB = findById(dataAccessJob.getId());
		
		if (dataAccDB.getCreatedAt() != dataAccessJob.getCreatedAt() || !dataAccDB.getCreatedBy().equals(dataAccessJob.getCreatedBy())) {
			throw new Exception("created at or created by cannot be changed");
		}
	}

	public List<DataAccessJob> findAll() {
		return dataAccessJobDao.findAll();
	}

	public DataAccessJob findById(String id) {
		return dataAccessJobDao.findOne(id);
	}

	public void save(DataAccessJob dataAccessJob) throws Exception {
		dataAccessJob.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(dataAccessJob);

		dataAccessJobDao.create(dataAccessJob);
	}

	public void update(DataAccessJob dataAccessJob) throws Exception {
		valIdNotNull(dataAccessJob.getId());
		valIdExist(dataAccessJob.getId());
		dataAccessJob.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(dataAccessJob);

		dataAccessJobDao.update(dataAccessJob);
	}

	public void delete(String id) throws Exception {
		valIdExist(id);
		
		dataAccessJobDao.deleteById(id);
	}
}
