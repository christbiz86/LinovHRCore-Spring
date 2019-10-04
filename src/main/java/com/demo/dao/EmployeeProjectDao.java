package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.EmployeeProject;

@Repository
public class EmployeeProjectDao extends AbstractJpaDao<EmployeeProject> {
	public EmployeeProjectDao() {
		setClazz(EmployeeProject.class);
	}
	
	@SuppressWarnings("unchecked")
	public EmployeeProject findByBk(String projectId, String employeeId) {
		List<EmployeeProject> empProj = super.entityManager.createQuery("FROM EmployeeProject WHERE project.id=:projectId AND employee.id=:employeeId")
				.setParameter("projectId", projectId)
				.setParameter("employeeId", employeeId).getResultList();
		
		if(empProj.size() == 0) {
			return new EmployeeProject();
		} else {
			return empProj.get(0);
		}
	}
	
	public boolean isBkExist(String projectId, String employeeId) {
		if(findByBk(projectId, employeeId).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<EmployeeProject> findByEmpId(String employeeId) {
		return super.entityManager.createQuery("FROM EmployeeProject WHERE employee.id=:employeeId")
				.setParameter("employeeId", employeeId).getResultList();
	}
}
