package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.UnitTypeDao;
import com.demo.model.UnitType;

@Service
public class UnitTypeService {
	@Autowired
	private UnitTypeDao unitTypeDao;
	
	public List<UnitType> findAll(){
		return unitTypeDao.findAll();
	}
}
