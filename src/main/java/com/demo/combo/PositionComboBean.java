package com.demo.combo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.PositionDao;
import com.demo.model.Position;

@Service
public class PositionComboBean {

	@Autowired
	private PositionDao positionDao;
	
	List<Position> list = new ArrayList<Position>();
	
	@PostConstruct
	private List<Position> init() {
		return list = positionDao.findAll();
	}

	public List<Position> getList() {
		return list;
	}
}
