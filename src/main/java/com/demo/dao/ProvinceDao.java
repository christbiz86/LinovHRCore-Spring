package com.demo.dao;

import com.demo.model.Province;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProvinceDao extends ParentDao {

    public List<Province> findAll(){
        return super.entityManager.createQuery("FROM Province").getResultList();
    }

}
