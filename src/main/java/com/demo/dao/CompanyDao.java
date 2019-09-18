package com.demo.dao;

import com.demo.model.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDao extends ParentDao {

    public List<Company> findAll(){
        return super.entityManager.createQuery("FROM Company").getResultList();
    }

    public List<Company> findById(String  id){
        return super.entityManager.createQuery("FROM Company WHERE id=:id").setParameter("id", id)
                .getResultList();
    }

}