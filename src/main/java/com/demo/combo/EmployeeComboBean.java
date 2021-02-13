package com.demo.combo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.EmployeeDao;
import com.demo.model.Employee;

@Service
public class EmployeeComboBean {

	@Autowired
	private EmployeeDao employeeDao;
	
	List<Employee> list = new ArrayList<Employee>();
	
	@PostConstruct
	public List<Employee> init() {
		return list = employeeDao.findAll();
	}

	public List<Employee> getList() {
		return list;
	}
}
