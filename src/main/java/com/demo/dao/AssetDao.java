package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Asset;

@Repository
public class AssetDao extends AbstractJpaDao<Asset>{

	public AssetDao() {
		setClazz(Asset.class);
	}
	
	@SuppressWarnings("unchecked")
    public List<Asset> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM Asset")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
		
	@SuppressWarnings("unchecked")
    public Asset findByBk(Asset asset){
		List<Asset>list= super.entityManager.createQuery("FROM Asset WHERE company.id=:companyId AND code=:code ")
                .setParameter("companyId", asset.getCompany().getId())
                .setParameter("code", asset.getCode())
                .getResultList();
        
        if (list.size() == 0) {
			return new Asset();
		}
		else {
			return (Asset)list.get(0);
		}
    }
		
	public boolean isBkExist(Asset asset) {
		
		if(findByBk(asset).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}
}
