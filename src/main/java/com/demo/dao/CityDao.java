package com.demo.dao;


import com.demo.model.City;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CityDao extends AbstractJpaDao<City> {

    public List<City> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM City")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }

    public List<City> findById(String id){
        return super.entityManager.createQuery("FROM City WHERE id=:id").setParameter("id", id)
                .getResultList();
    }

	public CityDao() {
		setClazz(City.class);
	}
	
	@SuppressWarnings("unchecked")
	public City findByBk(String code, String provinceId) {
		List<City> city = super.entityManager.createQuery("FROM City WHERE code=:code AND province.id=:provinceId")
				.setParameter("code", code)
				.setParameter("provinceId", provinceId)
				.getResultList();
		if(city.size() == 0) {
			return new City();
		} else {
			return city.get(0);
		}
	}
	
	public boolean isBkExist(String code, String provinceId) {
		if(findByBk(code, provinceId).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
