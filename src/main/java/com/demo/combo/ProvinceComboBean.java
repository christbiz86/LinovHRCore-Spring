package com.demo.combo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ProvinceDao;
import com.demo.model.Province;

@Service
public class ProvinceComboBean {
	
	@Autowired
	private ProvinceDao provinceDao;
	
	List<Province> list = new ArrayList<Province>();
	
	@PostConstruct
	private List<Province> init() {
		return list = provinceDao.findAll();
	}

	public List<Province> getList() {
		return list;
	}

}
