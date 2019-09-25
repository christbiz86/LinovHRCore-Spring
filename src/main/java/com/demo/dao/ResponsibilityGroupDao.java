package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.ResponsibilityGroup;

@Repository
public class ResponsibilityGroupDao extends AbstractJpaDao<ResponsibilityGroup>{

	public ResponsibilityGroupDao() {
		setClazz(ResponsibilityGroup.class);
	}
	
	@SuppressWarnings("unchecked")
    public List<ResponsibilityGroup> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM ResponsibilityGroup")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
	
	@SuppressWarnings("unchecked")
    public ResponsibilityGroup findByBk(ResponsibilityGroup responsibilityGroup){
		List<ResponsibilityGroup>list= super.entityManager.createQuery("FROM ResponsibilityGroup WHERE company.id=:companyId AND code=:code ")
                .setParameter("companyId", responsibilityGroup.getCompany().getId())
                .setParameter("code", responsibilityGroup.getCode())
                .getResultList();
        
        if (list.size() == 0) {
			return new ResponsibilityGroup();
		}
		else {
			return (ResponsibilityGroup)list.get(0);
		}
    }
	
	public boolean isBkExist(ResponsibilityGroup responsibilityGroup) {
		
		if(findByBk(responsibilityGroup).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}
}
