package com.demo.dao;

import com.demo.model.Costcenter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CostcenterDao extends ParentDao {

    public List<Costcenter> findAll(){
        return super.entityManager.createQuery("FROM Costcenter").getResultList();
    }

    public List<Costcenter> findById(String id){
        return super.entityManager.createQuery("FROM Costcenter where id=:id").setParameter("id",id).getResultList();
    }

}
