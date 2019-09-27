package com.demo.combo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.CompanyDao;
import com.demo.model.Company;

@Service
public class CompanyComboBean {
	
	@Autowired
	private CompanyDao companyDao;
	
	List<Company> list = new ArrayList<Company>();
	
	@PostConstruct
	private List<Company> init() {
		return list = companyDao.findAll();
	}

	public List<Company> getList() {
		return list;
	}
	
}
