package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Unit;

@Repository
public class UnitDao extends AbstractJpaDao<Unit>{
	public UnitDao() {
		setClazz(Unit.class);
	}
	
	@SuppressWarnings("unchecked")
	public Unit findByCode(String code, String companyId) {
		List<Unit> list = super.entityManager.createQuery("FROM Unit WHERE code=:code AND company.id =: companyId")
				.setParameter("code", code)
				.setParameter("companyId", companyId)
				.getResultList();
		
		if(list.size() == 0) {
			return new Unit();
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
