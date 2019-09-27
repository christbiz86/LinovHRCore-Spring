package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.PositionWorkingConditionDao;
import com.demo.model.PositionWorkingCondition;

@Service
public class PositionWorkingConditionService {

	@Autowired
	private PositionWorkingConditionDao positionWorkingConditionDao;

	public void valIdExist(String id) throws Exception {
		if (!positionWorkingConditionDao.isIdExist(id)) {
			throw new Exception("Data does not exist");
		}
	}

	public void valIdNotNull(String id) throws Exception {
		if (id.isEmpty()) {
			throw new Exception("Id cannot be emptied");
		}
	}

	public void valNonBk(PositionWorkingCondition positionWorkingCondition) throws Exception {
		if (positionWorkingCondition.getDescription().isEmpty()) {
			throw new Exception("description cannot be emptied");
		}
		if (positionWorkingCondition.getCreatedBy().isEmpty()) {
			throw new Exception("created by cannot be emptied");
		}
		if (positionWorkingCondition.getCreatedAt() == null) {
			throw new Exception("created at cannot be emptied");
		}
		if (positionWorkingCondition.getIsEssential() == null) {
			throw new Exception("is essential cannot be emptied");
		}
		if (positionWorkingCondition.getVersion() == null) {
			throw new Exception("version cannot be emptied");
		}
	}
	
	public void valCreatedNotChange(PositionWorkingCondition positionWorkingCondition) throws Exception {
		PositionWorkingCondition posDB = findById(positionWorkingCondition.getId());
		
		if (posDB.getCreatedAt() != positionWorkingCondition.getCreatedAt() || !posDB.getCreatedBy().equals(positionWorkingCondition.getCreatedBy())) {
			throw new Exception("created at or created by cannot be changed");
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
