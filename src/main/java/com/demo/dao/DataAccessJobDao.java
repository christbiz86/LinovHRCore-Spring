package com.demo.dao;

import org.springframework.stereotype.Repository;

import com.demo.model.DataAccessJob;

@Repository
public class DataAccessJobDao extends AbstractJpaDao<DataAccessJob> {

	public DataAccessJobDao () {
		super.setClazz(DataAccessJob.class);
	}
}
