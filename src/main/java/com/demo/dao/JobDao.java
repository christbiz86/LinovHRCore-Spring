package com.demo.dao;

import com.demo.model.Job;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class JobDao extends ParentDao {

    @Transactional
    public List<Job> findAll(){
        return super.entityManager.createQuery("From Job").getResultList();
    }

}
