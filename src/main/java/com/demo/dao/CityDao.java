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

}
