package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.MenuAction;

@Repository
public class MenuActionDao extends AbstractJpaDao<MenuAction>{

	public MenuActionDao() {
		setClazz(MenuAction.class);
	}
	
	@SuppressWarnings("unchecked")
    public List<MenuAction> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM MenuAction")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
	
	@SuppressWarnings("unchecked")
    public MenuAction findByBk(MenuAction menuAction){
		List<MenuAction>list= super.entityManager.createQuery("FROM MenuAction WHERE code=:code ")
                .setParameter("code", menuAction.getCode())
                .getResultList();
        
        if (list.size() == 0) {
			return new MenuAction();
		}
		else {
			return (MenuAction)list.get(0);
		}
    }
	
	public boolean isBkExist(MenuAction menuAction) {
		
		if(findByBk(menuAction).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}
}
