package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.PositionResponsibilityDao;
import com.demo.model.PositionResponsibility;

@Service
public class PositionResponsibilityService {

	@Autowired
	private PositionResponsibilityDao positionResponsibilityDao;

	public void valIdExist(String id) throws Exception {
		if (!positionResponsibilityDao.isIdExist(id)) {
			throw new Exception("Data does not exist");
		}

	}

	public void valIdNotNull(String id) throws Exception {
		if (id.isEmpty()) {
			throw new Exception("Id cannot be emptied");
		}
	}

	public void valNonBk(PositionResponsibility positionResponsibility) throws Exception {
		if (positionResponsibility.getDescription().isEmpty()) {
			throw new Exception("description cannot be emptied");
		}
		if (positionResponsibility.getCreatedBy().isEmpty()) {
			throw new Exception("created by cannot be emptied");
		}
		if (positionResponsibility.getCreatedAt() == null) {
			throw new Exception("created at cannot be emptied");
		}
		if (positionResponsibility.getIsAppraisal() == null) {
			throw new Exception("is appraisal cannot be emptied");
		}
		if (positionResponsibility.getVersion() == null) {
			throw new Exception("version cannot be emptied");
		}
	}
	
	public void valCreatedNotChange(PositionResponsibility positionResponsibility) throws Exception {
		PositionResponsibility posDB = findById(positionResponsibility.getId());
		
		if (posDB.getCreatedAt() != positionResponsibility.getCreatedAt() || !posDB.getCreatedBy().equals(positionResponsibility.getCreatedBy())) {
			throw new Exception("created at or created by cannot be changed");
		}
	}

	public List<PositionResponsibility> findAll() {
		return positionResponsibilityDao.findAll();
	}

	public PositionResponsibility findById(String id) {
		return positionResponsibilityDao.findOne(id);
	}

	public void save(PositionResponsibility positionResponsibility) throws Exception {
		positionResponsibility.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(positionResponsibility);

		positionResponsibilityDao.create(positionResponsibility);
	}

	public void update(PositionResponsibility positionResponsibility) throws Exception {
		valIdNotNull(positionResponsibility.getId());
		valIdExist(positionResponsibility.getId());
		positionResponsibility.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(positionResponsibility);

		positionResponsibilityDao.update(positionResponsibility);
	}

	public void delete(String id) throws Exception {
		valIdExist(id);
		
		positionResponsibilityDao.deleteById(id);
	}
}
