package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.Responsibility;

@Repository
public class ResponsibilityDao extends ParentDao  {

	@SuppressWarnings("unchecked")
	@Transactional
    public List<Responsibility> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM Responsibility")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
}
