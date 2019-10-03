package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.AssignmentReason;

@Repository
public class AssignmentReasonDao extends AbstractJpaDao<AssignmentReason>{
	
	public AssignmentReasonDao() {
        setClazz(AssignmentReason.class);
    }
	
	@SuppressWarnings("unchecked")
	public AssignmentReason findByCode(String code) {
		List<AssignmentReason> list = super.entityManager
                .createQuery("FROM AssignmentReason WHERE code = :code")
                .setParameter("code", code)
                .getResultList();

		if (list.size() == 0) {
			return new AssignmentReason();
		}
		else {
			return list.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	public AssignmentReason findByBk(String code, String company) {
		List<AssignmentReason> list = super.entityManager
                .createQuery("FROM AssignmentReason WHERE code = :code AND company.id = :company")
                .setParameter("code", code)
                .setParameter("company", company)
                .getResultList();

		if (list.size() == 0) {
			return new AssignmentReason();
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
