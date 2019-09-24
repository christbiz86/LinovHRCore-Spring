package com.demo.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		List<String> listErr = new ArrayList<String>();
		if (!positionSlotDao.isIdExist(id)) {
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

	public void valNonBk(PositionSlot positionSlot) throws Exception {
		List<String> listErr = new ArrayList<String>();

		if (positionSlot.getCreatedBy() == null || positionSlot.getCreatedBy().isEmpty()) {
			listErr.add("created by cannot be emptied");
		}
		if (positionSlot.getCreatedAt() == null) {
			listErr.add("created at cannot be emptied");
		}
		if (positionSlot.getVersion() == null || positionSlot.getVersion().toString().isEmpty()) {
			listErr.add("version cannot be emptied");
		}

		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}

	public void valBkNotExist(PositionSlot positionSlot) throws Exception {
		List<String> listErr = new ArrayList<String>();
		if (positionSlotDao.isBkExist(positionSlot.getCompany().getId(), positionSlot.getPosition().getId(), positionSlot.getCode())) {
			listErr.add("Data already exist");
		}
		
		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}

	public void valBkNotChange(PositionSlot positionSlot) throws Exception {
		Company company = findById(positionSlot.getId()).getCompany();
		Position position = findById(positionSlot.getId()).getPosition();
		String code = findById(positionSlot.getId()).getCode();
		List<String> listErr = new ArrayList<String>();

		if (!(positionSlot.getCompany() == company) && positionSlot.getPosition() == position && !positionSlot.getCode().equals(code)) {
			listErr.add("company, position, or code cannot be changed");
		}
		
		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}

	public void valBkNotNull(PositionSlot positionSlot) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if (positionSlot.getCompany().getId() == null || positionSlot.getCompany().getId().isEmpty()) {
			listErr.add("company cannot be emptied");
		}
		if (positionSlot.getPosition().getId() == null || positionSlot.getPosition().getId().isEmpty()) {
			listErr.add("position cannot be emptied");
		}
		if (positionSlot.getCode() == null || positionSlot.getCode().trim().isEmpty()) {
			listErr.add("code cannot be emptied");
		}
		
		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valCreatedNotChange(PositionSlot positionSlot) throws Exception {
		PositionSlot posDB = findById(positionSlot.getId());
		List<String> listErr = new ArrayList<String>();
		
		if (posDB.getCreatedAt() != positionSlot.getCreatedAt() && !posDB.getCreatedBy().equals(positionSlot.getCreatedBy())) {
			listErr.add("created at or created by cannot be changed");
		}
		
		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}

	public List<PositionSlot> findAll() {
		return positionSlotDao.findAll();
	}

	public PositionSlot findById(String id) {
		return positionSlotDao.findOne(id);
	}

	@Transactional
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
