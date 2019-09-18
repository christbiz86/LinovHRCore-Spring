package com.demo.dao;

import com.demo.model.Grade;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class GradeDao extends ParentDao {

    @Transactional
    public List<Grade> findAll(){
        return super.entityManager.createQuery("From Grade").getResultList();
    }

}
