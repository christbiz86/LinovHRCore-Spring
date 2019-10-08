package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.UnitType;

@Repository
public class UnitTypeDao extends AbstractJpaDao<UnitType>{
	public UnitTypeDao() {
		setClazz(UnitType.class);
	}
	
	@SuppressWarnings("unchecked")
	public UnitType findByCode(String code, String companyId) {
		List<UnitType> list = super.entityManager.createQuery("FROM UnitType WHERE code=:code AND company.id =: companyId")
				.setParameter("code", code)
				.setParameter("companyId", companyId)
				.getResultList();
		
		if(list.size() == 0) {
			return new UnitType();
		} else {
			return list.get(0);
		}
	}
	
	public boolean isBkExist(String code, String companyId) {
		if(findByCode(code, companyId).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
