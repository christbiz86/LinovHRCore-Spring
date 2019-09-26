package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.LocationGroupDetail;

@Repository
public class LocationGroupDetailDao extends AbstractJpaDao<LocationGroupDetail>{
	public LocationGroupDetailDao() { 
		setClazz(LocationGroupDetail.class);
	}
	
	@SuppressWarnings("unchecked")
	public LocationGroupDetail findByBk(String location, String locationGroup) {
		List<LocationGroupDetail> list = super.entityManager.createQuery("FROM LocationGroupDetail WHERE location.id=:location AND locationGroup.id=:locationGroup")
				.setParameter("location", location)
				.setParameter("locationGroup", locationGroup)
				.getResultList();
		
		if(list.size() == 0) {
			return new LocationGroupDetail();
		} else {
			return list.get(0);
		}
	}
	
	public boolean isBkExist(String location, String locationGroup) {
		if(findByBk(location, locationGroup).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
