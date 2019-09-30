package com.demo.dao;

import com.demo.model.Province;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

@Repository
public class ProvinceDao extends AbstractJpaDao<Province> {
	
	public ProvinceDao() {
        setClazz(Province.class);
    }

    @SuppressWarnings("unchecked")
    @Transactional
	public Province findByCode(String code) {
		List<Province> list = super.entityManager
                .createQuery("FROM Province WHERE code = :code")
                .setParameter("code", code)
                .getResultList();
        if (list.size() == 0) {
            return new Province();
        }
        else {
            return list.get(0);
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
	public Province findByBk(String code, String country) {
		List<Province> list = super.entityManager
                .createQuery("FROM Province WHERE code = :code AND country.id = :country")
                .setParameter("code", code)
                .setParameter("country", country)
                .getResultList();

        if (list.size() == 0) {
            return new Province();
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

    public boolean isBkExist(String code, String country) {
        if(findByBk(code, country).getId() == null) {
            return false;
        }else {
            return true;
        }
    }

}
