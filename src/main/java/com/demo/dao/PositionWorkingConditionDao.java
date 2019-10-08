package com.demo.dao;

import org.springframework.stereotype.Repository;

import com.demo.model.PositionWorkingCondition;

@Repository
public class PositionWorkingConditionDao extends AbstractJpaDao<PositionWorkingCondition>{

	public PositionWorkingConditionDao () {
		super.setClazz(PositionWorkingCondition.class);
	}
}
