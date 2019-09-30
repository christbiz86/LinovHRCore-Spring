package com.demo.combo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.GradeDao;
import com.demo.model.Grade;

@Service
public class GradeComboBean {
	
	@Autowired
	private GradeDao gradeDao;
	
	List<Grade> list = new ArrayList<Grade>();
	
	@PostConstruct
	private List<Grade> init() {
		return list = gradeDao.findAll();
	}

	public List<Grade> getList() {
		return list;
	}

}
