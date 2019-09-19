package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Person;

@Repository
public class PersonDao extends ParentDao{
	
	@SuppressWarnings("unchecked")
	public List<Person> findAll() throws Exception{
        return super.entityManager.createQuery("FROM Person").getResultList();
    }
}
