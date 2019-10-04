package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.EmployeeProjectDao;
import com.demo.model.EmployeeProject;

@Service
public class EmployeeProjectService {
	@Autowired
	private EmployeeProjectDao employeeProjectDao;
	
	public List<EmployeeProject> findAll() {
		return employeeProjectDao.findAll();
	}
	
	public EmployeeProject findById(String id) {
		return employeeProjectDao.findOne(id);
	}
	
	public EmployeeProject findByBk(String projectId, String employeeId) {
		return employeeProjectDao.findByBk(projectId, employeeId);
	}
	
	public List<EmployeeProject> findProjectByEmpId(String employeeId) {
		return employeeProjectDao.findByEmpId(employeeId);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!employeeProjectDao.isIdExist(id)) {
			throw new Exception("Data is Not Found");
		}
	}
	
	private void valIdNotNull(EmployeeProject employeeProject)throws Exception {
		
		if(employeeProject.getId() == null || employeeProject.getId().isEmpty()) {
			throw new Exception("Id Cannot be empty");
		}
	}
	
	private void valBkNotExist(EmployeeProject employeeProject)throws Exception{
		if(employeeProjectDao.isBkExist(employeeProject.getProject().getId(), employeeProject.getEmployee().getId())) {
			throw new Exception("Data already exist");
		}
	}
	
	private void valBkNotChange(EmployeeProject employeeProject)throws Exception{
		String project = findById(employeeProject.getId()).getProject().getId();
		String employee = findById(employeeProject.getId()).getEmployee().getId();
		if(!employeeProject.getProject().getId().equals(project)) {
			throw new Exception("Project is cannot be changed");
		}
		if(!employeeProject.getEmployee().getId().equals(employee)) {
			throw new Exception("Employee Project Code is cannot be changed");
		}
	}
	
	private void valBkNotNull(EmployeeProject employeeProject) throws Exception{
		
		if(employeeProject.getProject() == null || employeeProject.getProject().getId().isEmpty()) {
			throw new Exception("Project cannot be empty");
		}
		
		if(employeeProject.getEmployee().getId().isEmpty()) {
			throw new Exception("Employee Project cannot be empty");
		}
	}
	
	public void save(EmployeeProject employeeProject) throws Exception {
		employeeProject.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		
		valBkNotNull(employeeProject);
		valBkNotExist(employeeProject);
		employeeProjectDao.create(employeeProject);
	}
	
	public void update(EmployeeProject employeeProject) throws Exception {
		employeeProject.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		
		valIdNotNull(employeeProject);
		valIdExist(employeeProject.getId());
		valBkNotNull(employeeProject);
		valBkNotChange(employeeProject);
		employeeProjectDao.update(employeeProject);
	}
	
	public void delete(String id) throws Exception {
		employeeProjectDao.deleteById(id);
	}
}
