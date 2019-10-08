package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.RatingScaleDetail;

@Repository
public class RatingScaleDetailDao extends AbstractJpaDao<RatingScaleDetail> {

	public RatingScaleDetailDao() {
		super.setClazz(RatingScaleDetail.class);
	}

	@SuppressWarnings("unchecked")
	public RatingScaleDetail findByBk(String rsId, String code) {
		List<RatingScaleDetail> ratingScale = super.entityManager
				.createQuery("from RatingScaleDetail where ratingScale.id=:rsId and code=:code ")
				.setParameter("rsId", rsId).setParameter("code", code).getResultList();

		if (ratingScale.size() == 0) {
			return new RatingScaleDetail();
		} else {
			return (RatingScaleDetail) ratingScale.get(0);
		}
	}

	public boolean isBkExist(String rsId, String code) {
		if (findByBk(rsId, code).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
