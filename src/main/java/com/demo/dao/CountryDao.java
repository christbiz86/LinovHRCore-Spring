package com.demo.dao;

import com.demo.model.Country;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryDao extends AbstractJpaDao<Country> {

    public List<Country> findAll(){
        return super.entityManager.createQuery("FROM Country").getResultList();
    }

    public List<Country> findById(String id){
        return super.entityManager.createQuery("FROM Country WHERE id=:id").setParameter("id", id)
                .getResultList();
    }

    public List<Country> findByCode(String code){
        return super.entityManager.createQuery("From Country where code=:code").setParameter("code",code)
                .getResultList();
    }

}
