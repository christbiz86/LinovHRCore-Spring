package com.demo.dao;

import org.springframework.stereotype.Repository;

import com.demo.model.DataAccessPos;

@Repository
public class DataAccessPosDao extends AbstractJpaDao<DataAccessPos> {

	public DataAccessPosDao () {
		super.setClazz(DataAccessPos.class);
	}
}
