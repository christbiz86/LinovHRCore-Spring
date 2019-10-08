package com.demo.dao;

import com.demo.model.Costcenter;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CostcenterDao extends AbstractJpaDao<Costcenter> {
	
	public CostcenterDao() {
        setClazz(Costcenter.class);
    }
	
	@SuppressWarnings("unchecked")
	public Costcenter findByCode(String code) {
		List<Costcenter> list = super.entityManager
                .createQuery("FROM Costcenter WHERE code = :code")
                .setParameter("code", code)
                .getResultList();

		if (list.size() == 0) {
			return new Costcenter();
		}
		else {
			return list.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Costcenter findByBk(String code, String company) {
		List<Costcenter> list = super.entityManager
                .createQuery("FROM Job WHERE code = :code AND company.id = :company")
                .setParameter("code", code)
                .setParameter("company", company)
                .getResultList();

		if (list.size() == 0) {
			return new Costcenter();
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
