package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.WorkingConditionType;

@Repository
public class WorkingConditionTypeDao extends AbstractJpaDao<WorkingConditionType>{
	
	public WorkingConditionTypeDao() {
        setClazz(WorkingConditionType.class);
    }
	
	@SuppressWarnings("unchecked")
	public WorkingConditionType findByCode(String code) {
		List<WorkingConditionType> list = super.entityManager
                .createQuery("FROM WorkingConditionType WHERE code = :code")
                .setParameter("code", code)
                .getResultList();

		if (list.size() == 0) {
			return new WorkingConditionType();
		}
		else {
			return list.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	public WorkingConditionType findByBk(String code, String company) {
		List<WorkingConditionType> list = super.entityManager
                .createQuery("FROM Job WHERE code = :code AND company.id = :company")
                .setParameter("code", code)
                .setParameter("company", company)
                .getResultList();

		if (list.size() == 0) {
			return new WorkingConditionType();
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
	
	public boolean isBkExist(String code, String company) {
		if(findByBk(code, company).getId() == null) {
			return false;
		}else {
			return true;
		}
	}

}
