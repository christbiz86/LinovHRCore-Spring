package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.WorkingCondition;

@Repository
public class WorkingConditionDao extends AbstractJpaDao<WorkingCondition> {
	
	public WorkingConditionDao() {
        setClazz(WorkingCondition.class);
    }
	
	@SuppressWarnings("unchecked")
	public WorkingCondition findByCode(String code) {
		List<WorkingCondition> list = super.entityManager
                .createQuery("FROM WorkingCondition WHERE code = :code")
                .setParameter("code", code)
                .getResultList();

		if (list.size() == 0) {
			return new WorkingCondition();
		}
		else {
			return list.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	public WorkingCondition findByBk(String code, String wct) {
		List<WorkingCondition> list = super.entityManager
                .createQuery("FROM WorkingCondition WHERE code = :code AND workingConditionType.id = :wct")
                .setParameter("code", code)
                .setParameter("wct", wct)
                .getResultList();

		if (list.size() == 0) {
			return new WorkingCondition();
		}
		else {
			return list.get(0);
		}
	}
	
	public boolean isCodeExist(String code) {
		if(findByCode(code).getId() == null) {
			return false;
		}else {
			return true;
		}	 
	}
	
	public boolean isBkExist(String code, String wct) {
		if(findByBk(code, wct).getId() == null) {
			return false;
		}else {
			return true;
		}
	}

}
