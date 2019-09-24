package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.Responsibility;

@Repository
public class ResponsibilityDao extends AbstractJpaDao<Responsibility>  {

	public ResponsibilityDao() {
		setClazz(Responsibility.class);;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
    public List<Responsibility> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM Responsibility")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
	
	@SuppressWarnings("unchecked")
    public Responsibility findByBk(Responsibility responsibility){
		List<Responsibility>list= super.entityManager.createQuery("FROM Responsibility WHERE company.id=:companyId AND code=:code ")
                .setParameter("companyId", responsibility.getCompany().getId())
                .setParameter("code",responsibility.getCode())
                .getResultList();
        
        if (list.size() == 0) {
			return new Responsibility();
		}
		else {
			return (Responsibility)list.get(0);
		}
    }
	
	public boolean isBkExist(Responsibility responsibility) {
		
		if(findByBk(responsibility).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}
}
