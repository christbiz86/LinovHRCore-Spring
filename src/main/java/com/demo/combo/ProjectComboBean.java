package com.demo.combo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ProjectDao;
import com.demo.model.Project;

@Service
public class ProjectComboBean {
	
	@Autowired
	private ProjectDao projectDao;
	
	List<Project> list = new ArrayList<Project>();
	
	@PostConstruct
	private List<Project> init() {
		return list = projectDao.findAll();
	}

	public List<Project> getList() {
		return list;
	}

}
