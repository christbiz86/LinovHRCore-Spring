package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.PositionGradeDao;
import com.demo.exception.ValidationException;
import com.demo.model.Grade;
import com.demo.model.Position;
import com.demo.model.PositionGrade;

@Service
public class PositionGradeService {

	@Autowired
	private PositionGradeDao positionGradeDao;

	public void valIdExist(String id) throws Exception {
		List<String> listErr = new ArrayList<String>();
		if (!positionGradeDao.isIdExist(id)) {
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

	public void valNonBk(PositionGrade positionGrade) throws Exception {
		List<String> listErr = new ArrayList<String>();

		if (positionGrade.getCreatedBy() == null || positionGrade.getCreatedBy().isEmpty()) {
			listErr.add("created by cannot be emptied");
		}
		if (positionGrade.getCreatedAt() == null) {
			listErr.add("created at cannot be emptied");
		}
		if (positionGrade.getVersion() == null || positionGrade.getVersion().toString().isEmpty()) {
			listErr.add("version cannot be emptied");
		}

		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}

	public void valBkNotExist(PositionGrade positionGrade) throws Exception {
		List<String> listErr = new ArrayList<String>();
		if (positionGradeDao.isBkExist(positionGrade.getPosition().getId(), positionGrade.getGrade().getId())) {
			listErr.add("Data already exist");
		}
		
		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}

	public void valBkNotChange(PositionGrade positionGrade) throws Exception {
		Position position = findById(positionGrade.getId()).getPosition();
		Grade grade = findById(positionGrade.getId()).getGrade();
		List<String> listErr = new ArrayList<String>();

		if (positionGrade.getPosition().getId().equals(position.getId()) && !positionGrade.getGrade().getId().equals(grade.getId()) ) {
			listErr.add("position or grade cannot be changed");
		}
		
		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}

	public void valBkNotNull(PositionGrade positionGrade) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if (positionGrade.getPosition() == null) {
			if (positionGrade.getPosition().getId().isEmpty()) {
				listErr.add("position cannot be emptied");
			}	
		}
		if (positionGrade.getGrade() == null) {
			if (positionGrade.getGrade().getId().trim().isEmpty()) {
				listErr.add("grade cannot be emptied");
			}
		}
		
		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valCreatedNotChange(PositionGrade positionGrade) throws Exception {
		PositionGrade posDB = findById(positionGrade.getId());
		List<String> listErr = new ArrayList<String>();
		
		if (posDB.getCreatedAt() != positionGrade.getCreatedAt() && !posDB.getCreatedBy().equals(positionGrade.getCreatedBy())) {
			listErr.add("created at or created by cannot be changed");
		}
		
		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}

	public List<PositionGrade> findAll() {
		return positionGradeDao.findAll();
	}

	public PositionGrade findById(String id) {
		return positionGradeDao.findOne(id);
	}

	@Transactional
	public void save(PositionGrade positionGrade) throws Exception {
//		positionGrade.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		valBkNotNull(positionGrade);
		valBkNotExist(positionGrade);
		valNonBk(positionGrade);

		positionGradeDao.create(positionGrade);
	}

	public void update(PositionGrade positionGrade) throws Exception {
		valIdNotNull(positionGrade.getId());
		valIdExist(positionGrade.getId());
		positionGrade.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valBkNotNull(positionGrade);
		valBkNotChange(positionGrade);
		valCreatedNotChange(positionGrade);
		valNonBk(positionGrade);

		positionGradeDao.update(positionGrade);
	}

	public void delete(String id) throws Exception {
		valIdExist(id);
		positionGradeDao.deleteById(id);
	}
}
