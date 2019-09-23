package com.demo.dao;

import com.demo.model.Job;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobDao extends ParentDao {

    public List<Job> findAll(){
        return super.entityManager.createQuery("From Job").getResultList();
    }

}
