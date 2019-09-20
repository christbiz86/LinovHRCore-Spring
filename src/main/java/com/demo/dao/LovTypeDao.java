package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.LovType;

@Repository
public class LovTypeDao extends ParentDao{

	@SuppressWarnings("unchecked")
    public List<LovType> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM LovType ")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
	
	@SuppressWarnings("unchecked")
	public LovType findById(String id){
		List<LovType>list= super.entityManager.createQuery("FROM LovType WHERE id=:id").setParameter("id", id)
                .getResultList();
		
		if (list.size() == 0) {
			return new LovType();
		}
		else {
			return (LovType)list.get(0);
		}
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
	
	public void save(LovType lovType) {
		entityManager.merge(lovType);
	}
	
	public void delete(LovType lovType) {
		entityManager.remove(lovType);
	}
	
	public boolean isExist(String id) {
		if (findById(id).getId().isEmpty()) {
			return false;
		} else {
			return true;
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