package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ProjectDao;
import com.demo.model.Project;

@Service
public class ProjectService {
	@Autowired
	private ProjectDao projectDao;
	
	public List<Project> findAll(){
		return projectDao.findAll();
	}
	
	public Project findById(String id) {
		return projectDao.findOne(id);
	}
	
	public Project findByBk(String code, String proManId, String locId, String supId) {
		return projectDao.findByBk(code, proManId, locId, supId);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!projectDao.isIdExist(id)) {
			throw new Exception("Data is Not Found");
		}
	}
	
	private void valIdNotNull(Project project)throws Exception {
		
		if(project.getId() == null || project.getId().isEmpty()) {
			throw new Exception("Id Cannot be empty");
		}
	}
	
	private void valNonBk(Project project) throws Exception {
		
	}
	
	private void valBkNotExist(Project project)throws Exception{
		if(projectDao.isBkExist(project.getCode(), project.getProjectManager().getId(), project.getLocation().getId(), project.getSupervisor().getId())) {
			throw new Exception("Data already exist");
		}
	}
	
	private void valBkNotChange(Project project)throws Exception{
		String company = findById(project.getId()).getCompany().getId();
		String code = findById(project.getId()).getCode();
		String location = findById(project.getId()).getLocation().getId();
		String supervisor = findById(project.getId()).getSupervisor().getId();
		if(!project.getCompany().getId().equals(company)) {
			throw new Exception("Company Project is cannot be changed");
		}
		if(!project.getCode().equals(code)) {
			throw new Exception("Project Code is cannot be changed");
		}
		if(!project.getLocation().getId().equals(location)) {
			throw new Exception("Project Location is cannot be changed");
		}
		if(!project.getSupervisor().getId().equals(supervisor)) {
			throw new Exception("Project Supervisor is cannot be changed");
		}
	}
	
	private void valBkNotNull(Project project) throws Exception{
		if(project.getCompany() == null || project.getCompany().getId().isEmpty()) {
			throw new Exception("Company Project cannot be empty");
		}
		
		if(project.getCode().isEmpty()) {
			throw new Exception("Project Code cannot be empty");
		}
		
		if(project.getProjectManager() == null || project.getProjectManager().getId().isEmpty()) {
			throw new Exception("Project Manager cannot be empty");
		}
		
		if(project.getSupervisor() == null || project.getSupervisor().getId().isEmpty()) {
			throw new Exception("Supervisor Project cannot be empty");
		}
	}
	
	public void save(Project project) throws Exception {
		project.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		
		valBkNotNull(project);
		valNonBk(project);
		valBkNotExist(project);
		projectDao.create(project);
	}
	
	public void update(Project project) throws Exception {
		project.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		
		valIdNotNull(project);
		valIdExist(project.getId());
		valBkNotNull(project);
		valBkNotChange(project);
		valNonBk(project);
		projectDao.update(project);
	}
	
	public void delete(String id) throws Exception {
		projectDao.deleteById(id);
	}
}
