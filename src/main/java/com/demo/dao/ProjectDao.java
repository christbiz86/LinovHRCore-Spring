package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Project;

@Repository
public class ProjectDao extends AbstractJpaDao<Project>{
	public ProjectDao() {
		setClazz(Project.class);
	}
	
	@SuppressWarnings("unchecked")
	public Project findByBk(String code, String proManId, String locId, String supId) {
		List<Project> list = super.entityManager.createQuery("FROM Project WHERE code=:code"
				+ " AND supervisor.id=:supId AND projectManager.id=:proManId AND location.id=:locId")
				.setParameter("code", code).setParameter("supId", supId).setParameter("proManId", proManId)
				.setParameter("locId", locId).getResultList();
		if(list.size() == 0) {
			return new Project();
		} else {
			return list.get(0);
		}
	}
	
	public boolean isBkExist(String code, String proManId, String locId, String supId) {
		if(findByBk(code, proManId, locId, supId).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
