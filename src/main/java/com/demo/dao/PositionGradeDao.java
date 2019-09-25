package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.PositionGrade;

@Repository
public class PositionGradeDao extends AbstractJpaDao<PositionGrade> {

	public PositionGradeDao () {
		super.setClazz(PositionGrade.class);
	}
	
	@SuppressWarnings("unchecked")
	public PositionGrade findByBk(String posId, String grId) {
		List<PositionGrade> posSlot = super.entityManager
				.createQuery("from PositionGrade where position.id=:posId and grade.id=:grId ")
				.setParameter("posId", posId).setParameter("grId", grId).getResultList();
		
		if (posSlot.size() == 0) {
			return new PositionGrade();
		} else {
			return posSlot.get(0);	
		}
	}

	public boolean isBkExist(String posId, String grId) {
		if (findByBk(posId, grId).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
