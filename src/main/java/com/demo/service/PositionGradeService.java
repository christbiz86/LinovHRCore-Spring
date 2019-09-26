package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.PositionGradeDao;
import com.demo.exception.ValidationException;
import com.demo.model.PositionGrade;

@Service
public class PositionGradeService {

	@Autowired
	private PositionGradeDao positionGradeDao;

	public void valIdExist(String id) throws Exception {
		if (!positionGradeDao.isIdExist(id)) {
			throw new Exception("Data does not exist");
		}
	}

	public void valIdNotNull(String id) throws Exception {
		if (id.isEmpty()) {
			throw new Exception("Id cannot be emptied");
		}
	}

	public void valNonBk(PositionGrade positionGrade) throws Exception {
		List<String> listErr = new ArrayList<String>();

		if (positionGrade.getCreatedBy() == null || positionGrade.getCreatedBy().isEmpty()) {
			throw new Exception("created by cannot be emptied");
		}
		if (positionGrade.getCreatedAt() == null) {
			throw new Exception("created at cannot be emptied");
		}
		if (positionGrade.getVersion() == null || positionGrade.getVersion().toString().isEmpty()) {
			throw new Exception("version cannot be emptied");
		}
	}

	public void valBkNotExist(PositionGrade positionGrade) throws Exception {
		if (positionGradeDao.isBkExist(positionGrade.getPosition().getId(), positionGrade.getGrade().getId())) {
			throw new Exception("Data already exist");
		}
	}

	public void valBkNotChange(PositionGrade positionGrade) throws Exception {
		String position = findById(positionGrade.getId()).getPosition().getId();
		String grade = findById(positionGrade.getId()).getGrade().getId();

		if (!positionGrade.getPosition().getId().equals(position) || !positionGrade.getGrade().getId().equals(grade)) {
			throw new Exception("position or grade cannot be changed");
		}
	}

	public void valBkNotNull(PositionGrade positionGrade) throws Exception {
		if (positionGrade.getPosition() == null) {
			if (positionGrade.getPosition().getId().isEmpty()) {
				throw new Exception("position cannot be emptied");
			}
		}
		if (positionGrade.getGrade() == null) {
			if (positionGrade.getGrade().getId().trim().isEmpty()) {
				throw new Exception("grade cannot be emptied");
			}
		}
	}
	
	public void valCreatedNotChange(PositionGrade positionGrade) throws Exception {
		PositionGrade posDB = findById(positionGrade.getId());
		
		if (posDB.getCreatedAt() != positionGrade.getCreatedAt() || !posDB.getCreatedBy().equals(positionGrade.getCreatedBy())) {
			throw new Exception("created at or created by cannot be changed");
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
