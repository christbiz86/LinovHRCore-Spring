package com.demo.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.demo.model.Tenant;

@Repository
public class TenantDao extends AbstractJpaDao<Tenant> {
	
	public TenantDao() {
        setClazz(Tenant.class);
    }
	
	@SuppressWarnings("unchecked")
	public Tenant findByCode(String code) {
		List<Tenant> list = super.entityManager
                .createQuery("FROM Tenant WHERE code = :code")
                .setParameter("code", code)
                .getResultList();

		if (list.size() == 0) {
			return new Tenant();
		}
		else {
			return list.get(0);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Tenant findByBk(String name, String code) {
		List<Tenant> list = super.entityManager
                .createQuery("FROM Tenant WHERE name = :name AND code = :code")
                .setParameter("name", name)
                .setParameter("code", code)
                .getResultList();

		if (list.size() == 0) {
			return new Tenant();
		}
		else {
			return list.get(0);
		}
	}
	
	public boolean isCodeExist(String code) {
		if(findByCode(code).getId() == null) {
			return false;
		}else {
			return true;
		}	 
	}
	
	public boolean isBkExist(String name, String code) {
		if(findByBk(name, code).getId() == null) {
			return false;
		}else {
			return true;
		}
	}

}
