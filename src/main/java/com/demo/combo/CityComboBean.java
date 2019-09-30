package com.demo.combo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.CityDao;
import com.demo.model.City;

@Service
public class CityComboBean {
	
	@Autowired
	private CityDao cityDao;
	
	List<City> list = new ArrayList<City>();
	
	@PostConstruct
	private List<City> init() {
		return list = cityDao.findAll();
	}

	public List<City> getList() {
		return list;
	}

}
