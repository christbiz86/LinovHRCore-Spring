package com.demo.dao;

import org.springframework.stereotype.Repository;

import com.demo.model.Position;

@Repository
public class PositionDao extends AbstractJpaDao<Position> {

	public PositionDao() {
		setClazz(Position.class);
	}

	public Position findByBk(String comId, String code) {
		Position position = (Position) super.entityManager
				.createQuery("from Position where company.id=:comId and code=:code ").setParameter("comId", comId)
				.setParameter("code", code).getSingleResult();

		return position;
	}

	public boolean isBkExist(String comId, String code) {
		if (findByBk(comId, code).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
