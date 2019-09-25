package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.UserSetting;

@Repository
public class UserSettingDao extends AbstractJpaDao<UserSetting>{

	public UserSettingDao() {
		setClazz(UserSetting.class);
	}
	
	@SuppressWarnings("unchecked")
	public UserSetting findByBk(String userId,String settingLovId) {
		List<UserSetting> list= super.entityManager.createQuery("FROM UserSetting WHERE user.id=:userId AND settingLov.id=:settingLovId ")
				.setParameter("userId", userId)
				.setParameter("settingLovId", settingLovId)
				.getResultList();
		
		if (list.size() == 0) {
			return new UserSetting();
		}
		else {
			return (UserSetting)list.get(0);
		}
	}
	
	public boolean isBkExist(UserSetting userSetting) {
		if(findByBk(userSetting.getUser().getId(),userSetting.getSettingLov().getId()).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
