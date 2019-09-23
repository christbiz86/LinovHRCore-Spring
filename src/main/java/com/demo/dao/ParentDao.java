package com.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public abstract class ParentDao {

    @PersistenceContext
    protected EntityManager entityManager;
}
