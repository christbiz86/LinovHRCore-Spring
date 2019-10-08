package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.SettingType;

@Repository
public class SettingTypeDao extends AbstractJpaDao<SettingType>{

	public SettingTypeDao() {
		setClazz(SettingType.class);
	}
	
	@SuppressWarnings("unchecked")
	public SettingType findByBk(String code) {
		List<SettingType> list= super.entityManager.createQuery("FROM SettingType WHERE code=:code ")
				.setParameter("code", code)
				.getResultList();
		
		if (list.size() == 0) {
			return new SettingType();
		}
		else {
			return (SettingType)list.get(0);
		}
	}
	
	public boolean isBkExist(SettingType settingType) {
		if(findByBk(settingType.getCode()).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
