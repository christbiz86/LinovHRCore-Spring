package com.demo.combo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.EmployeeStatusDao;
import com.demo.model.EmployeeStatus;

@Service
public class EmployeeStatusComboBean {
	
	@Autowired
	private EmployeeStatusDao esDao;
	
	List<EmployeeStatus> list = new ArrayList<EmployeeStatus>();
	
	@PostConstruct
	private List<EmployeeStatus> init() {
		return list = esDao.findAll();
	}

	public List<EmployeeStatus> getList() {
		return list;
	}

}
