package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.LovType;

@Repository
public class LovTypeDao extends AbstractJpaDao<LovType>{

	public LovTypeDao() {
        setClazz(LovType.class);
    }
	
	@SuppressWarnings("unchecked")
    public List<LovType> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM LovType ")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
	
	@SuppressWarnings("unchecked")
	public LovType findByBk(LovType lovType){
		List<LovType>list= super.entityManager.createQuery("FROM LovType WHERE code=:code ")
				.setParameter("code", lovType.getCode())
                .getResultList();
		
		if (list.size() == 0) {
			return new LovType();
		}
		else {
			return (LovType)list.get(0);
		}
    }
		
	public boolean isBkExist(LovType lovType) {
		
		if(findByBk(lovType).getId().isEmpty()) {
			return false;
		}else {
			return true;
		}	 
	}
}