package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ResponsibilityDao;
import com.demo.model.Responsibility;

@Service
public class ResponsibilityService {

	@Autowired
	private ResponsibilityDao responsibilityDao;
	
	 public List<Responsibility> findAll(Integer offset, Integer limit){
	        return responsibilityDao.findAll(offset,limit);
    }
}
