package com.demo.combo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UnitDao;
import com.demo.model.Unit;

@Service
public class UnitComboBean {

	@Autowired
	private UnitDao unitDao;
	
	List<Unit> list = new ArrayList<Unit>();
	
	@PostConstruct
	private List<Unit> init() {
		return list = unitDao.findAll();
	}

	public List<Unit> getList() {
		return list;
	}
}
