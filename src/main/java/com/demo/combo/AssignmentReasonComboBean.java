package com.demo.combo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.AssignmentReasonDao;
import com.demo.model.AssignmentReason;

@Service
public class AssignmentReasonComboBean {
	
	@Autowired
	private AssignmentReasonDao arDao;
	
	List<AssignmentReason> list = new ArrayList<AssignmentReason>();
	
	@PostConstruct
	private List<AssignmentReason> init() {
		return list = arDao.findAll();
	}

	public List<AssignmentReason> getList() {
		return list;
	}

}
