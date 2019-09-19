package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.UnitType;

@Repository
public class UnitTypeDao extends ParentDao {
	public List<UnitType> findAll(){
		return super.entityManager.createQuery("FROM UnitType").getResultList();
	}
}
