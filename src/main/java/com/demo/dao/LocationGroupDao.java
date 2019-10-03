package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.LocationGroup;

@Repository
public class LocationGroupDao extends AbstractJpaDao<LocationGroup> {
	public LocationGroupDao() {
		setClazz(LocationGroup.class);
	}
	
	@SuppressWarnings("unchecked")
	public LocationGroup findByBk(String code, String companyId) {
		List<LocationGroup> list = super.entityManager.createQuery("FROM LocationGroup WHERE code=:code AND company.id=:companyId")
				.setParameter("code", code)
				.setParameter("companyId", companyId)
				.getResultList();
		
		if(list.size() == 0) {
			return new LocationGroup();
		} else {
			return list.get(0);
		}
	}
	
	public boolean isBkExist(String code, String companyId) {
		if(findByBk(code, companyId).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
