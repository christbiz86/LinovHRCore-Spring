package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Location;

@Repository
public class LocationDao extends AbstractJpaDao<Location>{
	public LocationDao() {
		setClazz(Location.class);
	}
	
	@SuppressWarnings("unchecked")
	public Location findByCode(String code, String companyId) {
		List<Location> list = super.entityManager.createQuery("FROM Location WHERE code=:code AND company.id =: companyId")
				.setParameter("code", code)
				.setParameter("companyId", companyId)
				.getResultList();
		
		if(list.size() == 0) {
			return new Location();
		} else {
			return list.get(0);
		}
	}
	
	public boolean isBkExist(String code, String companyId) {
		if (findByCode(code, companyId).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
