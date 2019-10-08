package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Menu;

@Repository
public class MenuDao extends AbstractJpaDao<Menu>{
	
	public MenuDao() {
		setClazz(Menu.class);
	}
	
	@SuppressWarnings("unchecked")
    public List<Menu> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM Menu")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
	
	@SuppressWarnings("unchecked")
    public Menu findByBk(Menu menu){
		List<Menu>list= super.entityManager.createQuery("FROM Menu WHERE code=:code ")
                .setParameter("code", menu.getCode())
                .getResultList();
        
        if (list.size() == 0) {
			return new Menu();
		}
		else {
			return (Menu)list.get(0);
		}
    }
	
	public boolean isBkExist(Menu menu) {
		
		if(findByBk(menu).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}

}
