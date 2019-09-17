package com.demo.dao;


import com.demo.model.City;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class CityDao extends ParentDao {

    @Transactional
    public List<City> findAll(Integer offset, Integer limit){
//        List<City> list = super.entityManager.createQuery("select id FROM City").getResultList();
        return super.entityManager.createQuery("FROM City")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }

    @Transactional
    public List<City> findById(UUID id){
        return super.entityManager.createQuery("FROM City WHERE id=:id").setParameter("id", id)
                .getResultList();
    }

}
