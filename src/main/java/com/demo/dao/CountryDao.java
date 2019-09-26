package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.Country;

@Repository
public class CountryDao extends AbstractJpaDao<Country> {
	
	public CountryDao() {
        setClazz(Country.class);
    }
	
	@SuppressWarnings("unchecked")
	public Country findByBk(String code) {
		List<Country> list = super.entityManager
                .createQuery("FROM Country WHERE code = :code")
                .setParameter("code", code)
                .getResultList();

		if (list.size() == 0) {
			return new Country();
		}
		else {
			return list.get(0);
		}
	}
	
	public boolean isBkExist(String code) {
		if(findByBk(code).getId() == null) {
			return false;
		}else {
			return true;
		}
	}
	
	@Transactional
	public List<Country> findCombo() {
		System.out.println("Combo is running");
		return findAll();
	}

}
