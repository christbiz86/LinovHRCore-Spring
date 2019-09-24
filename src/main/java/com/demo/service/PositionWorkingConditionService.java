package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.PositionWorkingConditionDao;
import com.demo.exception.ValidationException;
import com.demo.model.PositionWorkingCondition;

@Service
public class PositionWorkingConditionService {

	@Autowired
	private PositionWorkingConditionDao positionWorkingConditionDao;

	public void valIdExist(String id) throws Exception {
		List<String> listErr = new ArrayList<String>();
		if (!positionWorkingConditionDao.isIdExist(id)) {
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

	public void valNonBk(PositionWorkingCondition positionWorkingCondition) throws Exception {
		List<String> listErr = new ArrayList<String>();

		if (positionWorkingCondition.getDescription().isEmpty()) {
			listErr.add("description cannot be emptied");
		}
		if (positionWorkingCondition.getCreatedBy().isEmpty()) {
			listErr.add("created by cannot be emptied");
		}
		if (positionWorkingCondition.getCreatedAt() == null) {
			listErr.add("created at cannot be emptied");
		}
		if (positionWorkingCondition.getIsEssential() == null) {
			listErr.add("is essential cannot be emptied");
		}
		if (positionWorkingCondition.getVersion() == null) {
			listErr.add("version cannot be emptied");
		}

		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valCreatedNotChange(PositionWorkingCondition positionWorkingCondition) throws Exception {
		PositionWorkingCondition posDB = findById(positionWorkingCondition.getId());
		List<String> listErr = new ArrayList<String>();
		
		if (posDB.getCreatedAt() != positionWorkingCondition.getCreatedAt() && posDB.getCreatedBy() != positionWorkingCondition.getCreatedBy()) {
			listErr.add("created at or created by cannot be changed");
		}
		
		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}

	public List<PositionWorkingCondition> findAll() {
		return positionWorkingConditionDao.findAll();
	}

	public PositionWorkingCondition findById(String id) {
		return positionWorkingConditionDao.findOne(id);
	}

	public void save(PositionWorkingCondition positionWorkingCondition) throws Exception {
//		positionWorkingCondition.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(positionWorkingCondition);

		positionWorkingConditionDao.create(positionWorkingCondition);
	}

	public void update(PositionWorkingCondition positionWorkingCondition) throws Exception {
		valIdNotNull(positionWorkingCondition.getId());
		valIdExist(positionWorkingCondition.getId());
		positionWorkingCondition.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(positionWorkingCondition);

		positionWorkingConditionDao.update(positionWorkingCondition);
	}

	public void delete(String id) throws Exception {
		valIdExist(id);
		
		positionWorkingConditionDao.deleteById(id);
	}
}
