package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.TenantModule;

@Repository
public class TenantModuleDao extends AbstractJpaDao<TenantModule> {
	
	public TenantModuleDao() {
        setClazz(TenantModule.class);
    }
	
	@SuppressWarnings("unchecked")
	public TenantModule findByBk(String tenant, String module) {
		List<TenantModule> list = super.entityManager
                .createQuery("FROM TenantModule WHERE tenant.id = :tenant AND module.id = :module")
                .setParameter("tenant", tenant)
                .setParameter("module", module)
                .getResultList();

		if (list.size() == 0) {
			return new TenantModule();
		}
		else {
			return list.get(0);
		}
	}
	
	public boolean isBkExist(String tenant, String module) {
		if(findByBk(tenant, module).getId() == null) {
			return false;
		}else {
			return true;
		}
	}

}
