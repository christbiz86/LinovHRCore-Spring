package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.SettingLov;

@Repository
public class SettingLovDao extends AbstractJpaDao<SettingLov>{
	
	public SettingLovDao() {
		setClazz(SettingLov.class);
	}

	@SuppressWarnings("unchecked")
	public SettingLov findByBk(String settingTypeId,String keyData) {
		List<SettingLov> list= super.entityManager.createQuery("FROM SettingLov WHERE settingType.id=:settingTypeId AND keyData=:keyData ")
				.setParameter("settingTypeId", settingTypeId)
				.setParameter("keyData", keyData)
				.getResultList();
		
		if (list.size() == 0) {
			return new SettingLov();
		}
		else {
			return (SettingLov)list.get(0);
		}
	}
	
	public boolean isBkExist(SettingLov settingLov) {
		if(findByBk(settingLov.getSettingType().getId(),settingLov.getKeyData()).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}