package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.PositionSlotDao;
import com.demo.exception.ValidationException;
import com.demo.model.Company;
import com.demo.model.Position;
import com.demo.model.PositionSlot;

@Service
public class PositionSlotService {

	@Autowired
	private PositionSlotDao positionSlotDao;

	public void valIdExist(String id) throws Exception {
		if (!positionSlotDao.isIdExist(id)) {
			throw new Exception("Data does not exist");
		}
	}

	public void valIdNotNull(String id) throws Exception {
		if (id.isEmpty()) {
			throw new Exception("Id cannot be emptied");
		}
	}

	public void valNonBk(PositionSlot positionSlot) throws Exception {
		if (positionSlot.getCreatedBy() == null || positionSlot.getCreatedBy().isEmpty()) {
			throw new Exception("created by cannot be emptied");
		}
		if (positionSlot.getCreatedAt() == null) {
			throw new Exception("created at cannot be emptied");
		}
		if (positionSlot.getVersion() == null || positionSlot.getVersion().toString().isEmpty()) {
			throw new Exception("version cannot be emptied");
		}
	}

	public void valBkNotExist(PositionSlot positionSlot) throws Exception {
		if (positionSlotDao.isBkExist(positionSlot.getCompany().getId(), positionSlot.getPosition().getId(), positionSlot.getCode())) {
			throw new Exception("Data already exist");
		}
	}

	public void valBkNotChange(PositionSlot positionSlot) throws Exception {
		Company company = findById(positionSlot.getId()).getCompany();
		Position position = findById(positionSlot.getId()).getPosition();
		String code = findById(positionSlot.getId()).getCode();

		if (!(positionSlot.getCompany() == company) && positionSlot.getPosition() == position && !positionSlot.getCode().equals(code)) {
			throw new Exception("company, position, or code cannot be changed");
		}
	}

	public void valBkNotNull(PositionSlot positionSlot) throws Exception {
		if (positionSlot.getCompany().getId() == null || positionSlot.getCompany().getId().isEmpty()) {
			throw new Exception("company cannot be emptied");
		}
		if (positionSlot.getPosition().getId() == null || positionSlot.getPosition().getId().isEmpty()) {
			throw new Exception("position cannot be emptied");
		}
		if (positionSlot.getCode() == null || positionSlot.getCode().trim().isEmpty()) {
			throw new Exception("code cannot be emptied");
		}
	}
	
	public void valCreatedNotChange(PositionSlot positionSlot) throws Exception {
		PositionSlot posDB = findById(positionSlot.getId());

		if (posDB.getCreatedAt() != positionSlot.getCreatedAt() && !posDB.getCreatedBy().equals(positionSlot.getCreatedBy())) {
			throw new Exception("created at or created by cannot be changed");
		}
	}

	public List<PositionSlot> findAll() {
		return positionSlotDao.findAll();
	}

	public PositionSlot findById(String id) {
		return positionSlotDao.findOne(id);
	}

	public void save(PositionSlot positionSlot) throws Exception {
		positionSlot.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		valBkNotNull(positionSlot);
		valBkNotExist(positionSlot);
		valNonBk(positionSlot);

		positionSlotDao.create(positionSlot);
	}

	public void update(PositionSlot positionSlot) throws Exception {
		valIdNotNull(positionSlot.getId());
		valIdExist(positionSlot.getId());
		positionSlot.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valBkNotNull(positionSlot);
		valBkNotChange(positionSlot);
		valCreatedNotChange(positionSlot);
		valNonBk(positionSlot);

		positionSlotDao.update(positionSlot);
	}

	public void delete(String id) throws Exception {
		valIdExist(id);
		positionSlotDao.deleteById(id);
	}
}
