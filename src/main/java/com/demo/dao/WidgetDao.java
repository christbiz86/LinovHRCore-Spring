package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Widget;

@Repository
public class WidgetDao extends AbstractJpaDao<Widget>{
	
	public WidgetDao() {
		setClazz(Widget.class);
	}
	
	@SuppressWarnings("unchecked")
    public List<Widget> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM Widget")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
	
	@SuppressWarnings("unchecked")
    public Widget findByBk(Widget widget){
		List<Widget>list= super.entityManager.createQuery("FROM Widget WHERE name=:name ")
                .setParameter("name", widget.getName())
                .getResultList();
        
        if (list.size() == 0) {
			return new Widget();
		}
		else {
			return (Widget)list.get(0);
		}
    }
	
	public boolean isBkExist(Widget widget) {
		
		if(findByBk(widget).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}
}