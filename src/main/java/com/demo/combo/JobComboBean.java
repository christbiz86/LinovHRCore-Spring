package com.demo.combo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.JobDao;
import com.demo.model.Job;

@Service
public class JobComboBean {

	@Autowired
	private JobDao jobDao;
	
	List<Job> list = new ArrayList<Job>();
	
	@PostConstruct
	private List<Job> init() {
		return list = jobDao.findAll();
	}

	public List<Job> getList() {
		return list;
	}
}
