package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.PositionDao;
import com.demo.model.Position;

@Service
public class PositionService {

	@Autowired
	private PositionDao positionDao;

	public void valIdExist(String id) throws Exception {
		if (!positionDao.isIdExist(id)) {
			throw new Exception("Data does not exist");
		}
	}

	public void valIdNotNull(String id) throws Exception {
		if (id.isEmpty()) {
			throw new Exception("Id cannot be emptied");
		}
	}

	public void valNonBk(Position position) throws Exception {
		if (position.getUnit().getId().isEmpty()) {
			throw new Exception("unit cannot be emptied");
		}
		if (position.getJob().getId().isEmpty()) {
			throw new Exception("job cannot be emptied");
		}
		if (position.getName().isEmpty()) {
			throw new Exception("name cannot be emptied");
		}
		if (position.getIsHead() == null) {
			throw new Exception("is head cannot be emptied");
		}
		if (position.getCreatedBy().isEmpty()) {
			throw new Exception("created by cannot be emptied");
		}
		if (position.getCreatedAt() == null) {
			throw new Exception("created at cannot be emptied");
		}
		if (position.getVersion() == null) {
			throw new Exception("version cannot be emptied");
		}
	}

	public void valBkNotExist(Position position) throws Exception {
		if (positionDao.isBkExist(position.getCompany().getId(), position.getCode())) {
			throw new Exception("Data already exist");
		}
	}

	public void valBkNotChange(Position position) throws Exception {
		String company = findById(position.getId()).getCompany().getId();
		String code = findById(position.getId()).getCode();

		if (!position.getCompany().getId().equals(company) || !position.getCode().equals(code)) {
			throw new Exception("company or code cannot be changed");
		}
	}

	public void valBkNotNull(Position position) throws Exception {
		if (position.getCompany() == null) {
			throw new Exception("company cannot be emptied");
		}
		if (position.getCode().isEmpty()) {
			throw new Exception("code cannot be emptied");
		}
	}
	
	public void valCreatedNotChange(Position position) throws Exception {
		Position posDB = findById(position.getId());
		
		if (posDB.getCreatedAt() != position.getCreatedAt() || !posDB.getCreatedBy().equals(position.getCreatedBy())) {
			throw new Exception("created at or created by cannot be changed");
		}
	}

	public List<Position> findAll() {
		return positionDao.findAll();
	}

	public Position findById(String id) {
		return positionDao.findOne(id);
	}

	public void save(Position position) throws Exception {
		position.setCreatedAt(new Timestamp(System.currentTimeMillis()));
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
		position.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
//		valCreatedNotChange(position);
		valNonBk(position);

		positionDao.update(position);
	}

	public void delete(String id) throws Exception {
		valIdExist(id);
		positionDao.deleteById(id);
	}
}
