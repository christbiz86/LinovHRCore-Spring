package com.demo.dao;

import com.demo.model.Costcenter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class CostcenterDao extends ParentDao {

    @Transactional
    public List<Costcenter> findAll(){
        return super.entityManager.createQuery("FROM Costcenter").getResultList();
    }

    @Transactional
    public List<Costcenter> findById(UUID id){
        return super.entityManager.createQuery("FROM Costcenter where id=:id").setParameter("id",id).getResultList();
    }

}
