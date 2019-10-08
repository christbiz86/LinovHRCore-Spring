package com.demo.dao;

import org.springframework.stereotype.Repository;

import com.demo.model.PositionResponsibility;

@Repository
public class PositionResponsibilityDao extends AbstractJpaDao<PositionResponsibility> {

	public PositionResponsibilityDao () {
		super.setClazz(PositionResponsibility.class);
	}
}
