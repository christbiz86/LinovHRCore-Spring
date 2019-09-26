package com.demo.combo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.CountryDao;
import com.demo.model.Country;

@Service
public class CountryComboBean {
	
	@Autowired
	private CountryDao countryDao;
	
	List<Country> list = new ArrayList<Country>();

	public List<Country> getList() {
		return list;
	}

	public void setList(List<Country> list) {
		this.list = list;
	}
	
	@PostConstruct
	private List<Country> init() {
		return list = countryDao.findAll();
	}
	
}
