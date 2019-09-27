package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Tenant;

@Repository
public class TenantDao extends AbstractJpaDao<Tenant>{
	
	@SuppressWarnings("unchecked")
    public Tenant findById(String id){
		List<Tenant>list= super.entityManager.createQuery("FROM Tenant WHERE id=:tenantId")
                .setParameter("tenantId", id)
                .getResultList();
        
        if (list.size() == 0) {
			return null;
		}
		else {
			return (Tenant)list.get(0);
		}
    }

}
