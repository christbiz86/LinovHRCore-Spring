package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.RatingScale;

@Repository
public class RatingScaleDao extends AbstractJpaDao<RatingScale> {

	public RatingScaleDao() {
		super.setClazz(RatingScale.class);
	}

	@SuppressWarnings("unchecked")
	public RatingScale findByBk(String comId, String code) {
		List<RatingScale> ratingScale = super.entityManager
				.createQuery("from RatingScale where company.id=:comId and code=:code ").setParameter("comId", comId)
				.setParameter("code", code).getResultList();

		if (ratingScale.size() == 0) {
			return new RatingScale();
		} else {
			return (RatingScale) ratingScale.get(0);
		}
	}

	public boolean isBkExist(String comId, String code) {
		if (findByBk(comId, code).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
