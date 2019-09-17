package com.demo.dao;

import com.demo.model.Country;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class CountryDao extends ParentDao {

    @Transactional
    public List<Country> findAll(){
        return super.entityManager.createQuery("FROM Country").getResultList();
    }

    @Transactional
    public List<Country> findById(UUID id){
        return super.entityManager.createQuery("FROM Country WHERE id=:id").setParameter("id", id)
                .getResultList();
    }

}
