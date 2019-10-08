package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Duty;

@Repository
public class DutyDao extends AbstractJpaDao<Duty>{
	
	public DutyDao() {
        setClazz(Duty.class);
    }
	
	@SuppressWarnings("unchecked")
	public Duty findByBk(String company, String responsibility) {
		List<Duty> list = super.entityManager
                .createQuery("FROM Duty WHERE company.id = :company AND responsibility.id = :responsibility")
                .setParameter("company", company)
                .setParameter("responsibility", responsibility)
                .getResultList();

		if (list.size() == 0) {
			return new Duty();
		}
		else {
			return list.get(0);
		}
	}
	
	public boolean isBkExist(String company, String responsibility) {
		if(findByBk(company, responsibility).getId() == null) {
			return false;
		}else {
			return true;
		}
	}

}
