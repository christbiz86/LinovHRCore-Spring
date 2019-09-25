package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Application;

@Repository
public class ApplicationDao extends AbstractJpaDao<Application>{

	public ApplicationDao() {
		setClazz(Application.class);
	}
	
	@SuppressWarnings("unchecked")
    public List<Application> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM Application")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
	
	@SuppressWarnings("unchecked")
    public Application findByBk(Application application){
		List<Application>list= super.entityManager.createQuery("FROM Application WHERE code=:code ")
                .setParameter("code", application.getCode())
                .getResultList();
        
        if (list.size() == 0) {
			return new Application();
		}
		else {
			return (Application)list.get(0);
		}
    }
	
	public boolean isBkExist(Application application) {
		
		if(findByBk(application).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}
}
