package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.RatingScaleDao;
import com.demo.dao.RatingScaleDetailDao;
import com.demo.model.RatingScale;
import com.demo.model.RatingScaleDetail;

@Service
public class RatingScaleService {

	@Autowired
	private RatingScaleDao ratingScaleDao;

	@Autowired
	private RatingScaleDetailDao ratingScaleDetailDao;

	public void valIdExist(String id) throws Exception {
		if (!ratingScaleDao.isIdExist(id)) {
			throw new Exception("Data does not exist");
		}
	}

	public void valIdNotNull(String id) throws Exception {
		if (id.isEmpty()) {
			throw new Exception("Id cannot be emptied");
		}
	}

	public void valNonBk(RatingScale ratingScale) throws Exception {
		if (ratingScale.getName() == null || ratingScale.getName().isEmpty()) {
			throw new Exception("name cannot be emptied");
		}
	}

	public void valBkNotExist(RatingScale ratingScale) throws Exception {
		if (ratingScaleDao.isBkExist(ratingScale.getCompany().getId(), ratingScale.getCode())) {
			throw new Exception("Data already exist");
		}
	}

	public void valBkNotChange(RatingScale ratingScale) throws Exception {
		String company = findById(ratingScale.getId()).getCompany().getId();
		String code = findById(ratingScale.getId()).getCode();

		if (!ratingScale.getCompany().getId().equals(company) || !ratingScale.getCode().equals(code)) {
			throw new Exception("company or code cannot be changed");
		}
	}

	public void valBkNotNull(RatingScale ratingScale) throws Exception {
		if (ratingScale.getCompany() == null) {
			if (ratingScale.getCompany().getId().isEmpty()) {
				throw new Exception("position cannot be emptied");
			}
		}
		if (ratingScale.getCode().trim().isEmpty()) {
			throw new Exception("code cannot be emptied");
		}
	}

	public List<RatingScale> findAll() {
		return ratingScaleDao.findAll();
	}

	public RatingScale findById(String id) {
		return ratingScaleDao.findOne(id);
	}

	public void saveHeader(RatingScale ratingScale) throws Exception {
//		ratingScale.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		valBkNotNull(ratingScale);
		valBkNotExist(ratingScale);
		valNonBk(ratingScale);

		ratingScaleDao.create(ratingScale);
	}

	public void update(RatingScale ratingScale) throws Exception {
		valIdNotNull(ratingScale.getId());
		valIdExist(ratingScale.getId());
		ratingScale.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valBkNotNull(ratingScale);
		valBkNotChange(ratingScale);
		valNonBk(ratingScale);

		ratingScaleDao.update(ratingScale);
	}

	public void delete(String id) throws Exception {
		valIdExist(id);
		ratingScaleDao.deleteById(id);
	}

	public void valDetIdExist(String id) throws Exception {
		if (!ratingScaleDetailDao.isIdExist(id)) {
			throw new Exception("Data does not exist");
		}
	}

	public void valDetIdNotNull(String id) throws Exception {
		if (id.isEmpty()) {
			throw new Exception("Id cannot be emptied");
		}
	}

	public void valDetNonBk(RatingScaleDetail ratingScaleDetail) throws Exception {
		if (ratingScaleDetail.getLevel() == null) {
			throw new Exception("level cannot be emptied");
		}
	}

	public void valDetBkNotExist(RatingScaleDetail ratingScaleDetail) throws Exception {
		if (ratingScaleDetailDao.isBkExist(ratingScaleDetail.getRatingScale().getId(), ratingScaleDetail.getCode())) {
			throw new Exception("Data already exist");
		}
	}

	public void valDetBkNotChange(RatingScaleDetail ratingScaleDetail) throws Exception {
		String ratingScale = findDetById(ratingScaleDetail.getId()).getRatingScale().getId();
		String code = findDetById(ratingScaleDetail.getId()).getCode();

		if (!ratingScaleDetail.getRatingScale().getId().equals(ratingScale)
				|| !ratingScaleDetail.getCode().equals(code)) {
			throw new Exception("rating scale or code cannot be changed");
		}
	}

	public void valDetBkNotNull(RatingScaleDetail ratingScaleDetail) throws Exception {
		if (ratingScaleDetail.getRatingScale() == null) {
			if (ratingScaleDetail.getRatingScale().getId().isEmpty()) {
				throw new Exception("rating scale cannot be emptied");
			}
		}
		if (ratingScaleDetail.getCode().trim().isEmpty()) {
			throw new Exception("code cannot be emptied");
		}
	}

	public List<RatingScaleDetail> findAllDet() {
		return ratingScaleDetailDao.findAll();
	}

	public RatingScaleDetail findDetById(String id) {
		return ratingScaleDetailDao.findOne(id);
	}

	public void saveDet(RatingScaleDetail ratingScaleDetail) throws Exception {
//		ratingScaleDetail.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		valDetBkNotNull(ratingScaleDetail);
		valDetBkNotExist(ratingScaleDetail);
		valDetNonBk(ratingScaleDetail);

		ratingScaleDetailDao.create(ratingScaleDetail);
	}

	public void updateDet(RatingScaleDetail ratingScaleDetail) throws Exception {
		valDetIdNotNull(ratingScaleDetail.getId());
		valDetIdExist(ratingScaleDetail.getId());
		ratingScaleDetail.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valDetBkNotNull(ratingScaleDetail);
		valDetBkNotChange(ratingScaleDetail);
		valDetNonBk(ratingScaleDetail);

		ratingScaleDetailDao.update(ratingScaleDetail);
	}

	public void deleteDet(String id) throws Exception {
		valDetIdExist(id);
		ratingScaleDetailDao.deleteById(id);
	}

	public void saveHeaderDetail(RatingScale ratingScale) throws Exception {
//		ratingScale.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		valBkNotNull(ratingScale);
		valBkNotExist(ratingScale);
		valNonBk(ratingScale);
		ratingScaleDao.create(ratingScale);

		RatingScale rScaleDB = ratingScaleDao.findByBk(ratingScale.getCompany().getId(), ratingScale.getCode());
		List<RatingScaleDetail> rScaleDet = ratingScale.getRatingScDet();

		for (RatingScaleDetail ratingScaleDetail : rScaleDet) {
			ratingScaleDetail.setRatingScale(rScaleDB);
			saveDet(ratingScaleDetail);
		}
	}
}
