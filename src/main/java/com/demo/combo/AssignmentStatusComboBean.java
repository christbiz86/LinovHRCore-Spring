package com.demo.combo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.LovDao;
import com.demo.model.Lov;

@Service
public class AssignmentStatusComboBean {
	
	@Autowired
	private LovDao lovDao;
	
	List<Lov> listAssignmentStatus = new ArrayList<Lov>();
	
	@PostConstruct
	private List<Lov> init() {
		return listAssignmentStatus = lovDao.findByType("ASTA");
	}

	public List<Lov> getList() {
		return listAssignmentStatus;
	}
	
}
