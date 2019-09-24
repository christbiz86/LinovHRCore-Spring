package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.PositionSlot;

@Repository
public class PositionSlotDao extends AbstractJpaDao<PositionSlot> {

	public PositionSlotDao() {
		super.setClazz(PositionSlot.class);
	}

	@SuppressWarnings("unchecked")
	public PositionSlot findByBk(String comId, String posId, String code) {
		List<PositionSlot> posSlot = super.entityManager
				.createQuery("from PositionSlot where company.id=:comId and position.id=:posId and code=:code ")
				.setParameter("comId", comId).setParameter("posId", posId).setParameter("code", code).getResultList();
		
		if (posSlot.size() == 0) {
			return new PositionSlot();
		} else {
			return posSlot.get(0);	
		}
	}

	public boolean isBkExist(String comId, String posId, String code) {
		if (findByBk(comId, posId, code).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
