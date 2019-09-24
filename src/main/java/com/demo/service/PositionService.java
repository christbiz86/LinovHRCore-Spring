package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.PositionDao;
import com.demo.exception.ValidationException;
import com.demo.model.Position;

@Service
public class PositionService {

	@Autowired
	private PositionDao positionDao;

	public void valIdExist(String id) throws Exception {
		List<String> listErr = new ArrayList<String>();
		if (!positionDao.isIdExist(id)) {
			listErr.add("Data does not exist");
		}

		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}

	public void valIdNotNull(String id) throws Exception {
		List<String> listErr = new ArrayList<String>();
		if (id.isEmpty()) {
			listErr.add("Id cannot be emptied");
		}

		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}

	public void valNonBk(Position position) throws Exception {
		List<String> listErr = new ArrayList<String>();

		if (position.getUnit().getId().isEmpty()) {
			listErr.add("unit cannot be emptied");
		}
		if (position.getJob().getId().isEmpty()) {
			listErr.add("job cannot be emptied");
		}
		if (position.getName().isEmpty()) {
			listErr.add("name cannot be emptied");
		}
		if (position.getIsHead() == null) {
			listErr.add("is head cannot be emptied");
		}
		if (position.getCreatedBy().isEmpty()) {
			listErr.add("created by cannot be emptied");
		}
		if (position.getCreatedAt() == null) {
			listErr.add("created at cannot be emptied");
		}
		if (position.getVersion() == null) {
			listErr.add("version cannot be emptied");
		}

		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}

	public void valBkNotExist(Position position) throws Exception {
		List<String> listErr = new ArrayList<String>();
		if (positionDao.isBkExist(position.getCompany().getId(), position.getCode())) {
			listErr.add("Data already exist");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}

	public void valBkNotChange(Position position) throws Exception {
		String company = findById(position.getId()).getCompany().getId();
		String code = findById(position.getId()).getCode();
		List<String> listErr = new ArrayList<String>();

		if (!position.getCompany().getId().equals(company) || !position.getCode().equals(code)) {
			listErr.add("company or code cannot be changed");
		}
		
		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}

	public void valBkNotNull(Position position) throws Exception {
		List<String> listErr = new ArrayList<String>();
		
		if (position.getCompany() == null) {
			listErr.add("company cannot be emptied");
		}
		if (position.getCode().isEmpty()) {
			listErr.add("code cannot be emptied");
		}
		
		if (!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}
	
	public void valCreatedNotChange(Position position) throws Exception {
		Position posDB = findById(position.getId());
		List<String> listErr = new ArrayList<String>();
		
		if (posDB.getCreatedAt() != position.getCreatedAt() || !posDB.getCreatedBy().equals(position.getCreatedBy())) {
			listErr.add("created at or created by cannot be changed");
		}

		if(!listErr.isEmpty()) {
			throw new ValidationException(listErr);
		}
	}

	public List<Position> findAll() {
		return positionDao.findAll();
	}

	public Position findById(String id) {
		return positionDao.findOne(id);
	}

	public void save(Position position) throws Exception {
		valBkNotNull(position);
		valBkNotExist(position);
		valNonBk(position);

		positionDao.create(position);
	}

	public void update(Position position) throws Exception {
		valIdNotNull(position.getId());
		valIdExist(position.getId());
		valBkNotNull(position);
		valBkNotChange(position);
		valCreatedNotChange(position);
		valNonBk(position);

		positionDao.update(position);
	}

	public void delete(String id) throws Exception {
		valIdExist(id);
		positionDao.deleteById(id);
	}
}
