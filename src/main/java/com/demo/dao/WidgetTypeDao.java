package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.WidgetType;

@Repository
public class WidgetTypeDao extends AbstractJpaDao<WidgetType>{

	public WidgetTypeDao() {
		setClazz(WidgetType.class);
	}
	
	@SuppressWarnings("unchecked")
	public WidgetType findByBk(String name) {
		List<WidgetType> list= super.entityManager.createQuery("FROM WidgetType WHERE name=:name ")
				.setParameter("name", name)
				.getResultList();
		
		if (list.size() == 0) {
			return new WidgetType();
		}
		else {
			return (WidgetType)list.get(0);
		}
	}
	
	public boolean isBkExist(WidgetType widgetType) {
		if(findByBk(widgetType.getName()).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
