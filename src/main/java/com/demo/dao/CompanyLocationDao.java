package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.CompanyLocation;

@Repository
public class CompanyLocationDao extends AbstractJpaDao<CompanyLocation>{
	public CompanyLocationDao() {
		setClazz(CompanyLocation.class);
	}
	
	@SuppressWarnings("unchecked")
	public CompanyLocation findByBk(String locationId, String companyId) {
		List<CompanyLocation> compLoc = super.entityManager.createQuery("FROM CompanyLocation WHERE location.id=:locationId AND company.id=:companyId")
				.setParameter("locationId", locationId)
				.setParameter("companyId", companyId).getResultList();
		
		if(compLoc.size() == 0) {
			return new CompanyLocation();
		} else {
			return compLoc.get(0);
		}
	}
	
	public boolean isBkExist(String locationId, String companyId) {
		if(findByBk(locationId, companyId).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
