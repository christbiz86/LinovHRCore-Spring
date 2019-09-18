package com.demo.dao;

import com.demo.model.Company;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CompanyDao extends ParentDao {

    @Transactional
    public List<Company> findAll(){
        return super.entityManager.createQuery("FROM Company").getResultList();
    }

    @Transactional
    public List<Company> findById(String  id){
        return super.entityManager.createQuery("FROM Company WHERE id=:id").setParameter("id", id)
                .getResultList();
    }

}