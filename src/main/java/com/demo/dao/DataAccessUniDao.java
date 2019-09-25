package com.demo.dao;

import org.springframework.stereotype.Repository;

import com.demo.model.DataAccessUni;

@Repository
public class DataAccessUniDao extends AbstractJpaDao<DataAccessUni> {

	public DataAccessUniDao () {
		super.setClazz(DataAccessUni.class);
	}
}
