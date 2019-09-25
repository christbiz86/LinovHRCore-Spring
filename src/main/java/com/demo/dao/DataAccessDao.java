package com.demo.dao;

import org.springframework.stereotype.Repository;

import com.demo.model.DataAccess;

@Repository
public class DataAccessDao extends AbstractJpaDao<DataAccess> {

	public DataAccessDao () {
		super.setClazz(DataAccess.class);
	}
}
