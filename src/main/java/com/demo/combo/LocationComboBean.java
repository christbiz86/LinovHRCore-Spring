package com.demo.combo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.LocationDao;
import com.demo.model.Location;

@Service
public class LocationComboBean {
	
	@Autowired
	private LocationDao locationDao;
	
	List<Location> list = new ArrayList<Location>();
	
	@PostConstruct
	private List<Location> init() {
		return list = locationDao.findAll();
	}

	public List<Location> getList() {
		return list;
	}

}
