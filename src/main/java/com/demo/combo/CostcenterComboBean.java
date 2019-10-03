package com.demo.combo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.CostcenterDao;
import com.demo.model.Costcenter;

@Service
public class CostcenterComboBean {
	
	@Autowired
	private CostcenterDao ccDao;
	
	List<Costcenter> list = new ArrayList<Costcenter>();
	
	@PostConstruct
	private List<Costcenter> init() {
		return list = ccDao.findAll();
	}

	public List<Costcenter> getList() {
		return list;
	}

}
