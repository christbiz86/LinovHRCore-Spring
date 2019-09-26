package com.demo.dao;

import org.springframework.stereotype.Repository;

import com.demo.model.RoleDataAccess;

@Repository
public class RoleDataAccessDao extends AbstractJpaDao<RoleDataAccess>{

	public RoleDataAccessDao () {
		super.setClazz(RoleDataAccess.class);
	}
}
