package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.PositionResponsibilityDao;
import com.demo.exception.ValidationException;
import com.demo.model.PositionResponsibility;

@Service
public class PositionResponsibilityService {

	@Autowired
	private PositionResponsibilityDao positionResponsibilityDao;

	public void valIdExist(String id) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if (!positionResponsibilityDao.isIdExist(id)) {
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

	public void valNonBk(PositionResponsibility positionResponsibility) throws Exception {
		List<String> listErr = new ArrayList<String>();

		if (positionResponsibility.getDescription().isEmpty()) {
			listErr.add("description cannot be emptied");
		}
		if (positionResponsibility.getCreatedBy().isEmpty()) {
			listErr.add("created by cannot be emptied");
		}
		if (positionResponsibility.getCreatedAt() == null) {
			listErr.add("created at cannot be emptied");
		}
		if (positionResponsibility.getIsAppraisal() == null) {
			listErr.add("is appraisal cannot be emptied");
		}
		if (positionResponsibility.getVersion() == null) {
			listErr.add("version cannot be emptied");
		}

		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valCreatedNotChange(PositionResponsibility positionResponsibility) throws Exception {
		PositionResponsibility posDB = findById(positionResponsibility.getId());
		List<String> listErr = new ArrayList<String>();
		
		if (posDB.getCreatedAt() != positionResponsibility.getCreatedAt() || !posDB.getCreatedBy().equals(positionResponsibility.getCreatedBy())) {
			listErr.add("created at or created by cannot be changed");
		}
		
		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
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
