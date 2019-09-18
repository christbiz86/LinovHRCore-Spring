package com.demo.dao;

import com.demo.model.Grade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GradeDao extends ParentDao {

    public List<Grade> findAll(){
        return super.entityManager.createQuery("FROM Grade").getResultList();
    }

    public List<Grade> findById(String id){
        return super.entityManager.createQuery("FROM Grade where id=:id").setParameter("id",id)
            .getResultList();
    }

    public List<Grade> findByCode(String code){
        return super.entityManager.createQuery("FROM Grade where code=:code").setParameter("code",code)
                .getResultList();
    }

}
