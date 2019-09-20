package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Lov;

@Repository
public class LovDao extends ParentDao{

	@SuppressWarnings("unchecked")
    public List<Lov> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM Lov")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
	
	@SuppressWarnings("unchecked")
    public Lov findById(String id){
		List<Lov>list= super.entityManager.createQuery("FROM Lov where id=:id")
                .setParameter("id", id)
                .getResultList();
        
        if (list.size() == 0) {
			return new Lov();
		}
		else {
			return (Lov)list.get(0);
		}
    }
	
	@SuppressWarnings("unchecked")
    public Lov findByBk(Lov lov){
		List<Lov>list= super.entityManager.createQuery("FROM Lov WHERE lovType.id.=:loveTypeId AND keyData=:keydata ")
                .setParameter("loveTypeId", lov.getLovType().getId())
                .setParameter("keyData", lov.getKeyData())
                .getResultList();
        
        if (list.size() == 0) {
			return new Lov();
		}
		else {
			return (Lov)list.get(0);
		}
    }
	
	public void save(Lov lov) {
		entityManager.merge(lov);
	}
	
	public void delete(Lov lov) {
		entityManager.remove(lov);
	}
	
	public boolean isExist(String id) {
		if (findById(id).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isBkExist(Lov lov) {
		
		if(findByBk(lov).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}
}