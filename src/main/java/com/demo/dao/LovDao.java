package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Lov;

@Repository
public class LovDao extends AbstractJpaDao<Lov>{

	public LovDao() {
        setClazz(Lov.class);
    }
	
	@SuppressWarnings("unchecked")
    public List<Lov> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM Lov")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
		
	@SuppressWarnings("unchecked")
    public Lov findByBk(Lov lov){
		List<Lov>list= super.entityManager.createQuery("FROM Lov WHERE lovType.id=:loveTypeId AND keyData=:keydata ")
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
		
	public boolean isBkExist(Lov lov) {
		
		if(findByBk(lov).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}
	
	@SuppressWarnings("unchecked")
	public List<Lov> findByType(String code){
		List<Lov> list = super.entityManager.createQuery("FROM Lov WHERE lovType.code=:code")
				.setParameter("code", code).getResultList();
		
		return list;
	}

}